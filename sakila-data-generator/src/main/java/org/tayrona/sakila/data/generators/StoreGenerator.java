package org.tayrona.sakila.data.generators;

import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.jooq.Record2;
import org.jooq.Result;
import org.jooq.impl.UpdatableRecordImpl;
import org.springframework.stereotype.Component;
import org.tayrona.sakila.data.Tables;
import org.tayrona.sakila.data.tables.Staff;
import org.tayrona.sakila.data.tables.Store;
import org.tayrona.sakila.data.tables.records.*;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
public class StoreGenerator {

    private final DSLContext dslContext;

    private final Faker faker;

    private final AddressGenerator addressGenerator;

    private final FilmGenerator filmGenerator;

    public StoreGenerator(DSLContext dslContext, Faker faker,
                          AddressGenerator addressGenerator,
                          FilmGenerator filmGenerator) {
        this.dslContext = dslContext;
        this.faker = faker;
        this.addressGenerator = addressGenerator;
        this.filmGenerator = filmGenerator;
    }

    public Result<StoreRecord> existingStores() {
        return dslContext
                .selectFrom(Store.STORE)
                .fetch();
    }

    public Map<Long, StoreRecord> storesMappedByCityId() {
        Result<StoreRecord> existingStores = existingStores();
        Map<Long, StoreRecord> existingStoresMap = new LinkedHashMap<>(existingStores.size());
        for (StoreRecord storeRecord : existingStores) {
            existingStoresMap.put(storeRecord.getStoreId(), storeRecord);
        }
        return existingStoresMap;
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

                AddressRecord addressRecord = addressGenerator.persistNewAddressForCity(cityRecord);
                StoreRecord storeRecord = generateNewStoreWithAddress(addressRecord);
                storeRecord.store();

                StaffRecord manager = generateNewStaff(storeRecord);
                String fullName = manager.getLastName() + ", " + manager.getFirstName();
                log.info("persisting store manager {} for new store in {}", fullName, cityRecord.getCity());
                addressRecord = addressGenerator.persistNewAddressForCity(cityRecord);
                manager.setAddressId(addressRecord.getAddressId());
                manager.store();

                storeRecord.setManagerStaffId(manager.getStaffId());
                storeRecord.update();
                citiesWithStores.put(cityRecord.getCityId(), storeRecord);

                for (int i = 0; i < staffCount; i++) {
                    StaffRecord staff = generateNewStaff(storeRecord);
                    addressRecord = addressGenerator.persistNewAddressForCity(cityRecord);
                    staff.setAddressId(addressRecord.getAddressId());
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

    public long populateInventoryForAllStores(int inventoryCount) {
        log.info("Persisting {} films per store inventory", inventoryCount);
        long totalInventoryCount = 0;
        Result<StoreRecord> existingStores = existingStores();
        long maxFilmId = filmGenerator.getMaxFilmId();
        for (StoreRecord storeRecord : existingStores) {
            List<InventoryRecord> inventoryRecords = generateInventoryForStore(storeRecord, maxFilmId, inventoryCount);
            totalInventoryCount += persistStoryInventory(inventoryRecords);
            log.info("generated {} inventory items for store {}", inventoryRecords.size(), storeRecord.getStoreId());
        }
        return totalInventoryCount;
    }

    public List<InventoryRecord> generateInventoryForStore(StoreRecord storeRecord, long maxFilmId, int inventoryCount) {
        List<InventoryRecord> inventoryRecords = new LinkedList<>();
        Set<Long> chosenFilms = new HashSet<>(inventoryCount);
        chosenFilms.addAll(dslContext
                .select(Tables.INVENTORY.FILM_ID)
                .from(Tables.INVENTORY)
                .where(Tables.INVENTORY.STORE_ID.eq(storeRecord.getStoreId()))
                .fetchSet(Tables.INVENTORY.FILM_ID));
        for (int i = chosenFilms.size(); i < inventoryCount; i++) {
            Long filmId = getRandomExistingFilm(chosenFilms, maxFilmId);
            InventoryRecord inventoryRecord = dslContext.newRecord(Tables.INVENTORY);
            inventoryRecord.setFilmId(filmId);
            inventoryRecord.setStoreId(storeRecord.getStoreId());
            inventoryRecords.add(inventoryRecord);
        }
        return inventoryRecords;
    }

    public long persistStoryInventory(List<InventoryRecord> inventoryRecords) {
        inventoryRecords.forEach(UpdatableRecordImpl::store);
        return inventoryRecords.size();
    }

    public Long getRandomExistingFilm(Set<Long> chosenFilms, long maxFilmId) {
        int count = 0;
        int limit = 50;
        do {
            Long randomFilmId = faker.random().nextLong(maxFilmId);
            if (!chosenFilms.contains(randomFilmId) && filmGenerator.filmExistsById(randomFilmId)) {
                chosenFilms.add(randomFilmId);
                return randomFilmId;
            }
        } while (count++ < limit);
        throw new RuntimeException("Failed to find an existing film in " + limit + " attempts");
    }

    public Set<String> existingCustomersByStore(StoreRecord storeRecord) {
        Result<Record2<String, String>> customerRecords = dslContext
                .select(Tables.CUSTOMER.FIRST_NAME, Tables.CUSTOMER.LAST_NAME)
                .from(Tables.CUSTOMER)
                .where(Tables.CUSTOMER.STORE_ID.eq(storeRecord.getStoreId()))
                .fetch();
        return customerRecords.stream()
                .map(customer -> customer.getValue(Tables.CUSTOMER.FIRST_NAME) + " " + customer.getValue(Tables.CUSTOMER.LAST_NAME))
                .collect(Collectors.toSet());
    }

    public long populateCustomersForAllStores(int customerCount) {
        log.info("Persisting {} Customers per store", customerCount);
        long totalCustomerCount = 0;
        Result<StoreRecord> existingStores = existingStores();
        for (StoreRecord storeRecord : existingStores) {
            Set<String> existingCustomerNames = existingCustomersByStore(storeRecord);
            List<CustomerRecord> customerRecords = generateCustomersForStore(storeRecord, existingCustomerNames, customerCount);
            totalCustomerCount += persistStoreCustomers(customerRecords);
            log.info("generated {} Customers for store {}", customerRecords.size(), storeRecord.getStoreId());
        }
        return totalCustomerCount;
    }

    public List<CustomerRecord> generateCustomersForStore(StoreRecord storeRecord, Set<String> existingCustomerNames, int customerCount) {
        List<CustomerRecord> customerRecords = new LinkedList<>();
        while (existingCustomerNames.size() < customerCount) {
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            StringBuilder sb = new StringBuilder(firstName).append(" ").append(lastName);
            if (!existingCustomerNames.contains(sb.toString())) {
                existingCustomerNames.add(sb.toString());
                CustomerRecord customerRecord = dslContext.newRecord(Tables.CUSTOMER);
                customerRecord.setStoreId(storeRecord.getStoreId());
                customerRecord.setActive(true);
                customerRecord.setFirstName(firstName);
                customerRecord.setLastName(lastName);
                String username = firstName.toLowerCase() + "." + lastName.toLowerCase();
                customerRecord.setEmail(faker.internet().emailAddress(username));
                AddressRecord addressRecord = addressGenerator.generateAddressForCity(storeRecord.getStoreId());
                addressRecord.store();
                customerRecord.setAddressId(addressRecord.getAddressId());
                customerRecords.add(customerRecord);
            }
        }
        return customerRecords;
    }

    public long persistStoreCustomers(List<CustomerRecord> customerRecords) {
        customerRecords.forEach(UpdatableRecordImpl::store);
        return customerRecords.size();
    }
}
