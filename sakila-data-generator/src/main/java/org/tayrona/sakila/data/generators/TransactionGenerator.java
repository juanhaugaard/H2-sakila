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
        Calendar calendar = createCalendar(yearsBack);
        Set<Long> storeIds = existingStoreIds();
        for (int day = 0; day < numberOfDays; day++) {
            for (Long storeId : storeIds) {
                long transactionCountThisStore = persistTransactionsForOneStoreAndDay(storeId, calendar, rentalsPerStore);
                log.info("{} transactions persisted for store {}, date: {}", transactionCountThisStore, storeId, calendar.getTime());
                transactionCount += transactionCountThisStore;
            }
            calendar.add(Calendar.DAY_OF_YEAR, 1);
        }
        return transactionCount;
    }

    public long persistTransactionsForOneStoreAndDay(Long storeId, Calendar calendar, int rentalsPerStore) {
        long transactionCount = 0;
        LocalDateTime now = Timestamp.from(calendar.toInstant()).toLocalDateTime();
        Result<Record4<Long, Byte, BigDecimal, Long>> inventory = queryForInventory(storeId);
        Result<Record2<Long, Long>> customerIdRange = fetchCustomerIdsByStore(storeId);
        Set<Long> customerIds = new HashSet<>();
        Set<Long> staffIds = fetchStaffIdsByStore(storeId);
        if (customerIdRange.isNotEmpty() && inventory.isNotEmpty()) {
            while (customerIds.size() < rentalsPerStore) {
                Long customerId = fetchUniqueRandomCustomer(customerIds, customerIdRange.get(0));
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
                transactionCount += 1;
            }
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

    private Long fetchUniqueRandomCustomer(Set<Long> customerIds, Record2<Long, Long> customerIdRange) {
        Long customerId = null;
        while (null == customerId) {
            long candidateCustomerId = faker.random().nextLong(customerIdRange.value1() - customerIdRange.value2()) + customerIdRange.value2();
            if (!customerIds.contains(candidateCustomerId) && customerExists(candidateCustomerId)) {
                customerIds.add(candidateCustomerId);
                customerId = candidateCustomerId;
            }
        }
        return customerId;
    }

    private boolean customerExists(long customerId) {
        return dslContext
                .select(Tables.CUSTOMER.CUSTOMER_ID)
                .from(Tables.CUSTOMER)
                .where(Tables.CUSTOMER.CUSTOMER_ID.eq(customerId))
                .limit(1)
                .fetch()
                .isNotEmpty();
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
