/*
 * This file is generated by jOOQ.
 */
package com.tayrona.sakila.data.generated.tables.records;


import com.tayrona.sakila.data.generated.tables.City;

import java.time.OffsetDateTime;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record4;
import org.jooq.Row4;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * City details table
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class CityRecord extends UpdatableRecordImpl<CityRecord> implements Record4<Long, String, Long, OffsetDateTime> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>SAKILA.CITY.CITY_ID</code>.
     */
    public void setCityId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>SAKILA.CITY.CITY_ID</code>.
     */
    public Long getCityId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>SAKILA.CITY.CITY</code>.
     */
    public void setCity(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>SAKILA.CITY.CITY</code>.
     */
    public String getCity() {
        return (String) get(1);
    }

    /**
     * Setter for <code>SAKILA.CITY.COUNTRY_ID</code>.
     */
    public void setCountryId(Long value) {
        set(2, value);
    }

    /**
     * Getter for <code>SAKILA.CITY.COUNTRY_ID</code>.
     */
    public Long getCountryId() {
        return (Long) get(2);
    }

    /**
     * Setter for <code>SAKILA.CITY.LAST_UPDATE</code>.
     */
    public void setLastUpdate(OffsetDateTime value) {
        set(3, value);
    }

    /**
     * Getter for <code>SAKILA.CITY.LAST_UPDATE</code>.
     */
    public OffsetDateTime getLastUpdate() {
        return (OffsetDateTime) get(3);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record4 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row4<Long, String, Long, OffsetDateTime> fieldsRow() {
        return (Row4) super.fieldsRow();
    }

    @Override
    public Row4<Long, String, Long, OffsetDateTime> valuesRow() {
        return (Row4) super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return City.CITY.CITY_ID;
    }

    @Override
    public Field<String> field2() {
        return City.CITY.CITY_;
    }

    @Override
    public Field<Long> field3() {
        return City.CITY.COUNTRY_ID;
    }

    @Override
    public Field<OffsetDateTime> field4() {
        return City.CITY.LAST_UPDATE;
    }

    @Override
    public Long component1() {
        return getCityId();
    }

    @Override
    public String component2() {
        return getCity();
    }

    @Override
    public Long component3() {
        return getCountryId();
    }

    @Override
    public OffsetDateTime component4() {
        return getLastUpdate();
    }

    @Override
    public Long value1() {
        return getCityId();
    }

    @Override
    public String value2() {
        return getCity();
    }

    @Override
    public Long value3() {
        return getCountryId();
    }

    @Override
    public OffsetDateTime value4() {
        return getLastUpdate();
    }

    @Override
    public CityRecord value1(Long value) {
        setCityId(value);
        return this;
    }

    @Override
    public CityRecord value2(String value) {
        setCity(value);
        return this;
    }

    @Override
    public CityRecord value3(Long value) {
        setCountryId(value);
        return this;
    }

    @Override
    public CityRecord value4(OffsetDateTime value) {
        setLastUpdate(value);
        return this;
    }

    @Override
    public CityRecord values(Long value1, String value2, Long value3, OffsetDateTime value4) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached CityRecord
     */
    public CityRecord() {
        super(City.CITY);
    }

    /**
     * Create a detached, initialised CityRecord
     */
    public CityRecord(Long cityId, String city, Long countryId, OffsetDateTime lastUpdate) {
        super(City.CITY);

        setCityId(cityId);
        setCity(city);
        setCountryId(countryId);
        setLastUpdate(lastUpdate);
    }
}
