package org.tayrona.sakila.data.generators;

import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.jooq.Record1;
import org.jooq.Result;
import org.springframework.stereotype.Component;
import org.tayrona.sakila.data.tables.Address;
import org.tayrona.sakila.data.tables.City;
import org.tayrona.sakila.data.tables.Staff;
import org.tayrona.sakila.data.tables.Store;
import org.tayrona.sakila.data.tables.records.AddressRecord;
import org.tayrona.sakila.data.tables.records.CityRecord;
import org.tayrona.sakila.data.tables.records.StaffRecord;
import org.tayrona.sakila.data.tables.records.StoreRecord;

import java.util.List;
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

    public Optional<StoreRecord> getStoreById(long storeId) {
        return dslContext
                .selectFrom(Store.STORE)
                .where(Store.STORE.STORE_ID.eq(storeId))
                .fetch()
                .stream()
                .findFirst();
    }

    private boolean cityHasStore(long cityId) {
        return dslContext
                .select()
                .from(City.CITY)
                .join(Address.ADDRESS)
                .on(City.CITY.CITY_ID.eq(Address.ADDRESS.CITY_ID))
                .where(City.CITY.CITY_ID.eq(cityId))
                .fetch().isNotEmpty();
    }

    public void persistOneStorePerCity(int staffCount) {
        if (staffCount < 0) {
            throw new IllegalArgumentException("Staff count cannot be negative");
        }
        List<CityRecord> existingCities = addressGenerator.existingCities();
        for (CityRecord cityRecord : existingCities) {
            if (!cityHasStore(cityRecord.getCityId())) {

                log.info("persisting new store for {}", cityRecord.getCity());
                AddressRecord address = addressGenerator.persistNewAddressForCity(cityRecord);
                StoreRecord storeRecord = generateNewStoreWithAddress(address);
                storeRecord = persistNewStore(storeRecord);

                StaffRecord manager = generateNewStaff(storeRecord);
                String fullName = manager.getLastName() + ", " + manager.getFirstName();
                log.info("persisting store manager {} for {}", fullName, cityRecord.getCity());
                address = addressGenerator.persistNewAddressForCity(cityRecord);
                manager.setAddressId(address.getAddressId());
                manager = persistNewStaff(manager);

                storeRecord.setManagerStaffId(manager.getStaffId());
                storeRecord.update(Store.STORE.MANAGER_STAFF_ID);

                for (int i = 0; i < staffCount; i++) {
                    StaffRecord staff = generateNewStaff(storeRecord);
                    fullName = staff.getLastName() + ", " + staff.getFirstName();
                    log.info("persisting store staff {} for {}", fullName, cityRecord.getCity());
                    address = addressGenerator.persistNewAddressForCity(cityRecord);
                    staff.setAddressId(address.getAddressId());
                    persistNewStaff(staff);
                }
            }
        }
    }

    public StaffRecord persistNewStaff(StaffRecord staffRecord) {
        Result<Record1<Long>> staffId = dslContext
                .insertInto(Staff.STAFF)
                .set(staffRecord)
                .returningResult(Staff.STAFF.STAFF_ID)
                .fetch();
        if (staffId.isNotEmpty()) {
            staffRecord.setStaffId(staffId.getValue(0, Staff.STAFF.STAFF_ID));
        }
        staffRecord.attach(dslContext.configuration());
        return staffRecord;

    }

    public StoreRecord generateNewStoreWithAddress(AddressRecord storeAddress) {
        StoreRecord storeRecord = new StoreRecord();
        storeRecord.setAddressId(storeAddress.getAddressId());
        return storeRecord;
    }

    public StoreRecord persistNewStore(StoreRecord storeRecord) {
        Result<Record1<Long>> storeId = dslContext
                .insertInto(Store.STORE)
                .set(storeRecord)
                .returningResult(Store.STORE.STORE_ID)
                .fetch();
        if (storeId.isNotEmpty()) {
            storeRecord.setStoreId(storeId.getValue(0, Store.STORE.STORE_ID));
        }
        storeRecord.attach(dslContext.configuration());
        return storeRecord;
    }

    public Result<StaffRecord> existingStaff() {
        return dslContext
                .selectFrom(Staff.STAFF)
                .fetch();
    }

    public StaffRecord generateNewStaff(StoreRecord storeRecord) {
        StaffRecord staffRecord = new StaffRecord();
        staffRecord.setFirstName(faker.name().firstName());
        staffRecord.setLastName(faker.name().lastName());
        staffRecord.setEmail(faker.internet().emailAddress());
        staffRecord.setActive(true);
        staffRecord.setStoreId(storeRecord.getStoreId());
        staffRecord.setUsername(staffRecord.getFirstName().toLowerCase() + "." + staffRecord.getLastName().toLowerCase());
        staffRecord.setPassword(faker.internet().password(true).getBytes());
        return staffRecord;
    }
}
