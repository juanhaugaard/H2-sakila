package org.tayrona.sakila.data.generators;

import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.springframework.stereotype.Component;
import org.tayrona.sakila.data.tables.Address;
import org.tayrona.sakila.data.tables.Staff;
import org.tayrona.sakila.data.tables.Store;
import org.tayrona.sakila.data.tables.records.AddressRecord;
import org.tayrona.sakila.data.tables.records.CityRecord;
import org.tayrona.sakila.data.tables.records.StaffRecord;
import org.tayrona.sakila.data.tables.records.StoreRecord;

import java.util.Map;
import java.util.Optional;

@Slf4j
@Component
public class StoreGenerator {

    private final DSLContext dslContext;

    private final Faker faker;

    private final AddressGenerator addressGenerator;

    public StoreGenerator(DSLContext dslContext, Faker faker, AddressGenerator addressGenerator) {
        this.dslContext = dslContext;
        this.faker = faker;
        this.addressGenerator = addressGenerator;
    }

    public Result<StoreRecord> existingStores() {
        return dslContext
                .selectFrom(Store.STORE)
                .fetch();
    }

    public Map<Long, StoreRecord> storesMappedByCityId() {
        return dslContext.select(Address.ADDRESS.CITY_ID, Store.STORE.asterisk())
                .from(Store.STORE.join(Address.ADDRESS).on(Store.STORE.ADDRESS_ID.eq(Address.ADDRESS.ADDRESS_ID)))
                .fetch()
                .intoMap(Address.ADDRESS.CITY_ID, StoreRecord.class);
    }

    public Optional<StoreRecord> getStoreById(long storeId) {
        return dslContext
                .selectFrom(Store.STORE)
                .where(Store.STORE.STORE_ID.eq(storeId))
                .fetch()
                .stream()
                .findFirst();
    }

    public void persistOneStorePerCity(int staffCount) {
        if (staffCount < 0) {
            throw new IllegalArgumentException("Staff count cannot be negative");
        }
        Map<Long, StoreRecord> citiesWithStores = storesMappedByCityId();
        Result<CityRecord> existingCities = addressGenerator.existingCities();
        for (CityRecord cityRecord : existingCities) {
            if (!citiesWithStores.containsKey(cityRecord.getCityId())) {

                AddressRecord address = addressGenerator.persistNewAddressForCity(cityRecord);
                StoreRecord storeRecord = generateNewStoreWithAddress(address);
                storeRecord.store();

                StaffRecord manager = generateNewStaff(storeRecord);
                String fullName = manager.getLastName() + ", " + manager.getFirstName();
                log.info("persisting store manager {} for new store in {}", fullName, cityRecord.getCity());
                address = addressGenerator.persistNewAddressForCity(cityRecord);
                manager.setAddressId(address.getAddressId());
                manager.store();

                storeRecord.setManagerStaffId(manager.getStaffId());
                storeRecord.update();
                citiesWithStores.put(cityRecord.getCityId(), storeRecord);

                for (int i = 0; i < staffCount; i++) {
                    StaffRecord staff = generateNewStaff(storeRecord);
                    address = addressGenerator.persistNewAddressForCity(cityRecord);
                    staff.setAddressId(address.getAddressId());
                    staff.store();
                }
                log.info("persisted {} staff for new store in {}", staffCount, cityRecord.getCity());
            }
        }
    }

    public StoreRecord generateNewStoreWithAddress(AddressRecord storeAddress) {
        StoreRecord storeRecord = dslContext.newRecord(Store.STORE);
        storeRecord.setAddressId(storeAddress.getAddressId());
        return storeRecord;
    }

    public Result<StaffRecord> existingStaff() {
        return dslContext
                .selectFrom(Staff.STAFF)
                .fetch();
    }

    public StaffRecord generateNewStaff(StoreRecord storeRecord) {
        StaffRecord staffRecord = dslContext.newRecord(Staff.STAFF);
        staffRecord.setFirstName(faker.name().firstName());
        staffRecord.setLastName(faker.name().lastName());
        String username = staffRecord.getFirstName().toLowerCase() + "." + staffRecord.getLastName().toLowerCase();
        staffRecord.setEmail(faker.internet().emailAddress(username));
        staffRecord.setActive(true);
        staffRecord.setStoreId(storeRecord.getStoreId());
        staffRecord.setUsername(username);
        staffRecord.setPassword(faker.internet().password(true).getBytes());
        return staffRecord;
    }
}
