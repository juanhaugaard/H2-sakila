package org.tayrona.sakila.data.generators;

import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.jooq.Record2;
import org.jooq.Record4;
import org.jooq.Result;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Component;
import org.tayrona.sakila.data.Tables;
import org.tayrona.sakila.data.tables.records.PaymentRecord;
import org.tayrona.sakila.data.tables.records.RentalRecord;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@Component
public class TransactionGenerator {
    private final DSLContext dslContext;

    private final Faker faker;

    public TransactionGenerator(DSLContext dslContext, Faker faker) {
        this.dslContext = dslContext;
        this.faker = faker;
    }

    public long generateTransactionsForAllStores(int yearsBack, int numberOfDays, int rentalsPerStore) {
        long transactionCount = 0;
        Set<Long> storeIds = existingStoreIds();
        for (Long storeId : storeIds) {
            long transactionCountThisStore = persistTransactionsForOneStoreAllDays(storeId, yearsBack, numberOfDays, rentalsPerStore);
            transactionCount += transactionCountThisStore;
            log.info("{} transactions persisted for store {} and {} days", transactionCount, storeId, numberOfDays);
        }
        log.info("{} transactions persisted for {} stores and {} days", transactionCount, storeIds.size(), numberOfDays);
        return transactionCount;
    }

    public long persistTransactionsForOneStoreAllDays(Long storeId, int yearsBack, int numberOfDays, int rentalsPerStore) {
        long transactionCount = 0;
        Calendar calendar = createCalendar(yearsBack);
        Set<Long> staffIds = fetchStaffIdsByStore(storeId);
        Set<Long> customerIdsByStore = new HashSet<>();
        customerIdsByStore.addAll(dslContext
                .selectDistinct(Tables.CUSTOMER.CUSTOMER_ID)
                .from(Tables.CUSTOMER)
                .where(Tables.CUSTOMER.STORE_ID.eq(storeId))
                .fetchSet(Tables.CUSTOMER.CUSTOMER_ID));
        Result<Record4<Long, Byte, BigDecimal, Long>> inventory = queryForInventory(storeId);
        for (int day = 0; day < numberOfDays; day++) {
            long transactionCountPerDay = 0;
            Timestamp nowTimeStamp = Timestamp.from(calendar.toInstant());
            LocalDateTime today = nowTimeStamp.toLocalDateTime().truncatedTo(ChronoUnit.DAYS);
            LocalDateTime tomorrow = today.plusDays(1);
            LocalDateTime now = nowTimeStamp.toLocalDateTime();
            Set<Long> customerIds = new HashSet<>(rentalsPerStore);
            customerIds.addAll(dslContext
                    .selectDistinct(Tables.RENTAL.CUSTOMER_ID)
                    .from(Tables.RENTAL)
                    .join(Tables.CUSTOMER)
                    .on(Tables.RENTAL.CUSTOMER_ID.eq(Tables.CUSTOMER.CUSTOMER_ID)
                            .and(Tables.CUSTOMER.STORE_ID.eq(storeId))
                            .and(Tables.RENTAL.RENTAL_DATE.between(today, tomorrow)))
                    .fetchSet(Tables.RENTAL.CUSTOMER_ID));
            if (!customerIdsByStore.isEmpty() && inventory.isNotEmpty()) {
                while (customerIds.size() < rentalsPerStore) {
                    Long customerId = fetchUniqueRandomCustomer(customerIds, customerIdsByStore);
                    Record4<Long, Byte, BigDecimal, Long> inventoryRecord = fetchRandomInventory(inventory);
                    Long staffId = fetchRandomStaff(staffIds);
                    RentalRecord rentalRecord = dslContext.newRecord(Tables.RENTAL);
                    rentalRecord.setRentalDate(now);
                    rentalRecord.setInventoryId(inventoryRecord.value1());
                    rentalRecord.setCustomerId(customerId);
                    rentalRecord.setStaffId(staffId);
                    rentalRecord.setReturnDate(now.plusDays(inventoryRecord.value2()));
                    rentalRecord.store();
                    PaymentRecord paymentRecord = dslContext.newRecord(Tables.PAYMENT);
                    paymentRecord.setCustomerId(customerId);
                    paymentRecord.setStaffId(staffId);
                    paymentRecord.setRentalId(rentalRecord.getRentalId());
                    paymentRecord.setAmount(inventoryRecord.value3());
                    paymentRecord.setPaymentDate(now);
                    paymentRecord.store();
                    transactionCountPerDay += 1;
                }
            }
            log.info("{} transactions persisted for store {}, date: {}", transactionCountPerDay, storeId, calendar.getTime());
            calendar.add(Calendar.DAY_OF_YEAR, 1);
            transactionCount += transactionCountPerDay;
        }
        return transactionCount;
    }

    private Set<Long> fetchStaffIdsByStore(Long storeId) {
        return dslContext
                .select(Tables.STAFF.STAFF_ID)
                .from(Tables.STAFF)
                .where(Tables.STAFF.STORE_ID.eq(storeId))
                .fetch()
                .intoSet(Tables.STAFF.STAFF_ID);
    }

    private Result<Record2<Long, Long>> fetchCustomerIdsByStore(Long storeId) {
        return dslContext
                .select(
                        DSL.max(Tables.CUSTOMER.CUSTOMER_ID),
                        DSL.min(Tables.CUSTOMER.CUSTOMER_ID)
                ).from(Tables.CUSTOMER)
                .where(Tables.CUSTOMER.STORE_ID.eq(storeId))
                .fetch();
    }

    private Long fetchRandomStaff(Set<Long> staffIds) {
        int index = faker.random().nextInt(staffIds.size());
        for (Long staffId : staffIds) {
            if (index-- < 1) {
                return staffId;
            }
        }
        throw new IllegalStateException("Failed to find the " + index + " staffId in set: " + staffIds);
    }

    private Record4<Long, Byte, BigDecimal, Long> fetchRandomInventory(Result<Record4<Long, Byte, BigDecimal, Long>> inventory) {
        int index = faker.random().nextInt(inventory.size());
        return inventory.get(index);
    }

    private Long fetchUniqueRandomCustomer(Set<Long> customerIdsThisStoreToday, Set<Long> customerIdsForThisStore) {
        Long customerId = null;
        while (null == customerId) {
            int index = faker.random().nextInt(customerIdsForThisStore.size());
            long candidateCustomerId = customerIdsForThisStore.stream().skip(index).findFirst().orElseThrow();
            if (!customerIdsThisStoreToday.contains(candidateCustomerId)) {
                customerIdsThisStoreToday.add(candidateCustomerId);
                customerId = candidateCustomerId;
            }
        }
        return customerId;
    }

    private Result<Record4<Long, Byte, BigDecimal, Long>> queryForInventory(Long storeId) {
        return dslContext
                .select(
                        Tables.INVENTORY.INVENTORY_ID,
                        Tables.FILM.RENTAL_DURATION,
                        Tables.FILM.RENTAL_RATE,
                        Tables.ADDRESS.CITY_ID
                ).from(
                        Tables.INVENTORY.join(Tables.STORE).on(Tables.STORE.STORE_ID.eq(storeId))
                                .join(Tables.ADDRESS).on(Tables.ADDRESS.ADDRESS_ID.eq(Tables.STORE.ADDRESS_ID))
                                .join(Tables.FILM).on(Tables.INVENTORY.FILM_ID.eq(Tables.FILM.FILM_ID))
                ).fetch();
    }

    private Set<Long> existingStoreIds() {
        return dslContext
                .select(Tables.STORE.STORE_ID)
                .from(Tables.STORE)
                .fetch()
                .intoSet(Tables.STORE.STORE_ID);
    }

    private Calendar createCalendar(int yearsBack) {
        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.HOUR, 1);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.AM_PM, Calendar.PM);
        calendar.set(Calendar.DAY_OF_YEAR, 1);
        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - yearsBack);
        return calendar;
    }
}
