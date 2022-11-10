/*
 * This file is generated by jOOQ.
 */
package com.tayrona.sakila.data.generated.tables.records;


import com.tayrona.sakila.data.generated.tables.Payment;
import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record7;
import org.jooq.Row7;
import org.jooq.impl.UpdatableRecordImpl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;


/**
 * Payment details table
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class PaymentRecord extends UpdatableRecordImpl<PaymentRecord> implements Record7<Long, Long, Long, Long, BigDecimal, LocalDateTime, OffsetDateTime> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>PUBLIC.PAYMENT.PAYMENT_ID</code>.
     */
    public void setPaymentId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>PUBLIC.PAYMENT.PAYMENT_ID</code>.
     */
    public Long getPaymentId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>PUBLIC.PAYMENT.CUSTOMER_ID</code>.
     */
    public void setCustomerId(Long value) {
        set(1, value);
    }

    /**
     * Getter for <code>PUBLIC.PAYMENT.CUSTOMER_ID</code>.
     */
    public Long getCustomerId() {
        return (Long) get(1);
    }

    /**
     * Setter for <code>PUBLIC.PAYMENT.STAFF_ID</code>.
     */
    public void setStaffId(Long value) {
        set(2, value);
    }

    /**
     * Getter for <code>PUBLIC.PAYMENT.STAFF_ID</code>.
     */
    public Long getStaffId() {
        return (Long) get(2);
    }

    /**
     * Setter for <code>PUBLIC.PAYMENT.RENTAL_ID</code>.
     */
    public void setRentalId(Long value) {
        set(3, value);
    }

    /**
     * Getter for <code>PUBLIC.PAYMENT.RENTAL_ID</code>.
     */
    public Long getRentalId() {
        return (Long) get(3);
    }

    /**
     * Setter for <code>PUBLIC.PAYMENT.AMOUNT</code>.
     */
    public void setAmount(BigDecimal value) {
        set(4, value);
    }

    /**
     * Getter for <code>PUBLIC.PAYMENT.AMOUNT</code>.
     */
    public BigDecimal getAmount() {
        return (BigDecimal) get(4);
    }

    /**
     * Setter for <code>PUBLIC.PAYMENT.PAYMENT_DATE</code>.
     */
    public void setPaymentDate(LocalDateTime value) {
        set(5, value);
    }

    /**
     * Getter for <code>PUBLIC.PAYMENT.PAYMENT_DATE</code>.
     */
    public LocalDateTime getPaymentDate() {
        return (LocalDateTime) get(5);
    }

    /**
     * Setter for <code>PUBLIC.PAYMENT.LAST_UPDATE</code>.
     */
    public void setLastUpdate(OffsetDateTime value) {
        set(6, value);
    }

    /**
     * Getter for <code>PUBLIC.PAYMENT.LAST_UPDATE</code>.
     */
    public OffsetDateTime getLastUpdate() {
        return (OffsetDateTime) get(6);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record7 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row7<Long, Long, Long, Long, BigDecimal, LocalDateTime, OffsetDateTime> fieldsRow() {
        return (Row7) super.fieldsRow();
    }

    @Override
    public Row7<Long, Long, Long, Long, BigDecimal, LocalDateTime, OffsetDateTime> valuesRow() {
        return (Row7) super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return Payment.PAYMENT.PAYMENT_ID;
    }

    @Override
    public Field<Long> field2() {
        return Payment.PAYMENT.CUSTOMER_ID;
    }

    @Override
    public Field<Long> field3() {
        return Payment.PAYMENT.STAFF_ID;
    }

    @Override
    public Field<Long> field4() {
        return Payment.PAYMENT.RENTAL_ID;
    }

    @Override
    public Field<BigDecimal> field5() {
        return Payment.PAYMENT.AMOUNT;
    }

    @Override
    public Field<LocalDateTime> field6() {
        return Payment.PAYMENT.PAYMENT_DATE;
    }

    @Override
    public Field<OffsetDateTime> field7() {
        return Payment.PAYMENT.LAST_UPDATE;
    }

    @Override
    public Long component1() {
        return getPaymentId();
    }

    @Override
    public Long component2() {
        return getCustomerId();
    }

    @Override
    public Long component3() {
        return getStaffId();
    }

    @Override
    public Long component4() {
        return getRentalId();
    }

    @Override
    public BigDecimal component5() {
        return getAmount();
    }

    @Override
    public LocalDateTime component6() {
        return getPaymentDate();
    }

    @Override
    public OffsetDateTime component7() {
        return getLastUpdate();
    }

    @Override
    public Long value1() {
        return getPaymentId();
    }

    @Override
    public Long value2() {
        return getCustomerId();
    }

    @Override
    public Long value3() {
        return getStaffId();
    }

    @Override
    public Long value4() {
        return getRentalId();
    }

    @Override
    public BigDecimal value5() {
        return getAmount();
    }

    @Override
    public LocalDateTime value6() {
        return getPaymentDate();
    }

    @Override
    public OffsetDateTime value7() {
        return getLastUpdate();
    }

    @Override
    public PaymentRecord value1(Long value) {
        setPaymentId(value);
        return this;
    }

    @Override
    public PaymentRecord value2(Long value) {
        setCustomerId(value);
        return this;
    }

    @Override
    public PaymentRecord value3(Long value) {
        setStaffId(value);
        return this;
    }

    @Override
    public PaymentRecord value4(Long value) {
        setRentalId(value);
        return this;
    }

    @Override
    public PaymentRecord value5(BigDecimal value) {
        setAmount(value);
        return this;
    }

    @Override
    public PaymentRecord value6(LocalDateTime value) {
        setPaymentDate(value);
        return this;
    }

    @Override
    public PaymentRecord value7(OffsetDateTime value) {
        setLastUpdate(value);
        return this;
    }

    @Override
    public PaymentRecord values(Long value1, Long value2, Long value3, Long value4, BigDecimal value5, LocalDateTime value6, OffsetDateTime value7) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached PaymentRecord
     */
    public PaymentRecord() {
        super(Payment.PAYMENT);
    }

    /**
     * Create a detached, initialised PaymentRecord
     */
    public PaymentRecord(Long paymentId, Long customerId, Long staffId, Long rentalId, BigDecimal amount, LocalDateTime paymentDate, OffsetDateTime lastUpdate) {
        super(Payment.PAYMENT);

        setPaymentId(paymentId);
        setCustomerId(customerId);
        setStaffId(staffId);
        setRentalId(rentalId);
        setAmount(amount);
        setPaymentDate(paymentDate);
        setLastUpdate(lastUpdate);
    }
}
