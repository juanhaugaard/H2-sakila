/*
 * This file is generated by jOOQ.
 */
package org.tayrona.sakila.data.tables.records;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.OffsetDateTime;

import javax.annotation.Nullable;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record8;
import org.jooq.Row8;
import org.jooq.impl.UpdatableRecordImpl;
import org.tayrona.sakila.data.tables.Address;


/**
 * Address details table
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
@Entity
@Table(
    name = "ADDRESS",
    schema = "PUBLIC"
)
public class AddressRecord extends UpdatableRecordImpl<AddressRecord> implements Record8<Long, String, String, String, Long, String, String, OffsetDateTime> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>PUBLIC.ADDRESS.ADDRESS_ID</code>.
     */
    public void setAddressId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>PUBLIC.ADDRESS.ADDRESS_ID</code>.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ADDRESS_ID", nullable = false)
    public Long getAddressId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>PUBLIC.ADDRESS.ADDRESS</code>.
     */
    public void setAddress(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>PUBLIC.ADDRESS.ADDRESS</code>.
     */
    @Column(name = "ADDRESS", nullable = false, length = 50)
    @NotNull
    @Size(max = 50)
    public String getAddress() {
        return (String) get(1);
    }

    /**
     * Setter for <code>PUBLIC.ADDRESS.ADDRESS2</code>.
     */
    public void setAddress2(@Nullable String value) {
        set(2, value);
    }

    /**
     * Getter for <code>PUBLIC.ADDRESS.ADDRESS2</code>.
     */
    @Column(name = "ADDRESS2", length = 50)
    @Size(max = 50)
    @Nullable
    public String getAddress2() {
        return (String) get(2);
    }

    /**
     * Setter for <code>PUBLIC.ADDRESS.DISTRICT</code>.
     */
    public void setDistrict(@Nullable String value) {
        set(3, value);
    }

    /**
     * Getter for <code>PUBLIC.ADDRESS.DISTRICT</code>.
     */
    @Column(name = "DISTRICT", length = 50)
    @Size(max = 50)
    @Nullable
    public String getDistrict() {
        return (String) get(3);
    }

    /**
     * Setter for <code>PUBLIC.ADDRESS.CITY_ID</code>.
     */
    public void setCityId(Long value) {
        set(4, value);
    }

    /**
     * Getter for <code>PUBLIC.ADDRESS.CITY_ID</code>.
     */
    @Column(name = "CITY_ID", nullable = false)
    @NotNull
    public Long getCityId() {
        return (Long) get(4);
    }

    /**
     * Setter for <code>PUBLIC.ADDRESS.POSTAL_CODE</code>.
     */
    public void setPostalCode(@Nullable String value) {
        set(5, value);
    }

    /**
     * Getter for <code>PUBLIC.ADDRESS.POSTAL_CODE</code>.
     */
    @Column(name = "POSTAL_CODE", length = 10)
    @Size(max = 10)
    @Nullable
    public String getPostalCode() {
        return (String) get(5);
    }

    /**
     * Setter for <code>PUBLIC.ADDRESS.PHONE</code>.
     */
    public void setPhone(String value) {
        set(6, value);
    }

    /**
     * Getter for <code>PUBLIC.ADDRESS.PHONE</code>.
     */
    @Column(name = "PHONE", nullable = false, length = 20)
    @NotNull
    @Size(max = 20)
    public String getPhone() {
        return (String) get(6);
    }

    /**
     * Setter for <code>PUBLIC.ADDRESS.LAST_UPDATE</code>.
     */
    public void setLastUpdate(OffsetDateTime value) {
        set(7, value);
    }

    /**
     * Getter for <code>PUBLIC.ADDRESS.LAST_UPDATE</code>.
     */
    @Column(name = "LAST_UPDATE", nullable = false, precision = 6)
    public OffsetDateTime getLastUpdate() {
        return (OffsetDateTime) get(7);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record8 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row8<Long, String, String, String, Long, String, String, OffsetDateTime> fieldsRow() {
        return (Row8) super.fieldsRow();
    }

    @Override
    public Row8<Long, String, String, String, Long, String, String, OffsetDateTime> valuesRow() {
        return (Row8) super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return Address.ADDRESS.ADDRESS_ID;
    }

    @Override
    public Field<String> field2() {
        return Address.ADDRESS.ADDRESS_;
    }

    @Override
    public Field<String> field3() {
        return Address.ADDRESS.ADDRESS2;
    }

    @Override
    public Field<String> field4() {
        return Address.ADDRESS.DISTRICT;
    }

    @Override
    public Field<Long> field5() {
        return Address.ADDRESS.CITY_ID;
    }

    @Override
    public Field<String> field6() {
        return Address.ADDRESS.POSTAL_CODE;
    }

    @Override
    public Field<String> field7() {
        return Address.ADDRESS.PHONE;
    }

    @Override
    public Field<OffsetDateTime> field8() {
        return Address.ADDRESS.LAST_UPDATE;
    }

    @Override
    public Long component1() {
        return getAddressId();
    }

    @Override
    public String component2() {
        return getAddress();
    }

    @Override
    @Nullable
    public String component3() {
        return getAddress2();
    }

    @Override
    @Nullable
    public String component4() {
        return getDistrict();
    }

    @Override
    public Long component5() {
        return getCityId();
    }

    @Override
    @Nullable
    public String component6() {
        return getPostalCode();
    }

    @Override
    public String component7() {
        return getPhone();
    }

    @Override
    public OffsetDateTime component8() {
        return getLastUpdate();
    }

    @Override
    public Long value1() {
        return getAddressId();
    }

    @Override
    public String value2() {
        return getAddress();
    }

    @Override
    @Nullable
    public String value3() {
        return getAddress2();
    }

    @Override
    @Nullable
    public String value4() {
        return getDistrict();
    }

    @Override
    public Long value5() {
        return getCityId();
    }

    @Override
    @Nullable
    public String value6() {
        return getPostalCode();
    }

    @Override
    public String value7() {
        return getPhone();
    }

    @Override
    public OffsetDateTime value8() {
        return getLastUpdate();
    }

    @Override
    public AddressRecord value1(Long value) {
        setAddressId(value);
        return this;
    }

    @Override
    public AddressRecord value2(String value) {
        setAddress(value);
        return this;
    }

    @Override
    public AddressRecord value3(@Nullable String value) {
        setAddress2(value);
        return this;
    }

    @Override
    public AddressRecord value4(@Nullable String value) {
        setDistrict(value);
        return this;
    }

    @Override
    public AddressRecord value5(Long value) {
        setCityId(value);
        return this;
    }

    @Override
    public AddressRecord value6(@Nullable String value) {
        setPostalCode(value);
        return this;
    }

    @Override
    public AddressRecord value7(String value) {
        setPhone(value);
        return this;
    }

    @Override
    public AddressRecord value8(OffsetDateTime value) {
        setLastUpdate(value);
        return this;
    }

    @Override
    public AddressRecord values(Long value1, String value2, @Nullable String value3, @Nullable String value4, Long value5, @Nullable String value6, String value7, OffsetDateTime value8) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached AddressRecord
     */
    public AddressRecord() {
        super(Address.ADDRESS);
    }

    /**
     * Create a detached, initialised AddressRecord
     */
    public AddressRecord(Long addressId, String address, @Nullable String address2, @Nullable String district, Long cityId, @Nullable String postalCode, String phone, OffsetDateTime lastUpdate) {
        super(Address.ADDRESS);

        setAddressId(addressId);
        setAddress(address);
        setAddress2(address2);
        setDistrict(district);
        setCityId(cityId);
        setPostalCode(postalCode);
        setPhone(phone);
        setLastUpdate(lastUpdate);
    }

    /**
     * Create a detached, initialised AddressRecord
     */
    public AddressRecord(org.tayrona.sakila.data.tables.pojos.Address value) {
        super(Address.ADDRESS);

        if (value != null) {
            setAddressId(value.getAddressId());
            setAddress(value.getAddress());
            setAddress2(value.getAddress2());
            setDistrict(value.getDistrict());
            setCityId(value.getCityId());
            setPostalCode(value.getPostalCode());
            setPhone(value.getPhone());
            setLastUpdate(value.getLastUpdate());
        }
    }
}
