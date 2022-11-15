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
import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record4;
import org.jooq.Row4;
import org.jooq.impl.UpdatableRecordImpl;
import org.tayrona.sakila.data.tables.Country;

import javax.annotation.Nullable;
import java.time.OffsetDateTime;


/**
 * Country details table
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
@Entity
@Table(
    name = "COUNTRY",
    schema = "PUBLIC"
)
public class CountryRecord extends UpdatableRecordImpl<CountryRecord> implements Record4<Long, String, String, OffsetDateTime> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>PUBLIC.COUNTRY.COUNTRY_ID</code>.
     */
    public void setCountryId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>PUBLIC.COUNTRY.COUNTRY_ID</code>.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COUNTRY_ID", nullable = false)
    public Long getCountryId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>PUBLIC.COUNTRY.COUNTRY</code>.
     */
    public void setCountry(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>PUBLIC.COUNTRY.COUNTRY</code>.
     */
    @Column(name = "COUNTRY", nullable = false, length = 50)
    @NotNull
    @Size(max = 50)
    public String getCountry() {
        return (String) get(1);
    }

    /**
     * Setter for <code>PUBLIC.COUNTRY.COUNTRY_ABBREVIATION</code>.
     */
    public void setCountryAbbreviation(@Nullable String value) {
        set(2, value);
    }

    /**
     * Getter for <code>PUBLIC.COUNTRY.COUNTRY_ABBREVIATION</code>.
     */
    @Column(name = "COUNTRY_ABBREVIATION", length = 5)
    @Size(max = 5)
    @Nullable
    public String getCountryAbbreviation() {
        return (String) get(2);
    }

    /**
     * Setter for <code>PUBLIC.COUNTRY.LAST_UPDATE</code>.
     */
    public void setLastUpdate(OffsetDateTime value) {
        set(3, value);
    }

    /**
     * Getter for <code>PUBLIC.COUNTRY.LAST_UPDATE</code>.
     */
    @Column(name = "LAST_UPDATE", nullable = false, precision = 6)
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
    public Row4<Long, String, String, OffsetDateTime> fieldsRow() {
        return (Row4) super.fieldsRow();
    }

    @Override
    public Row4<Long, String, String, OffsetDateTime> valuesRow() {
        return (Row4) super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return Country.COUNTRY.COUNTRY_ID;
    }

    @Override
    public Field<String> field2() {
        return Country.COUNTRY.COUNTRY_;
    }

    @Override
    public Field<String> field3() {
        return Country.COUNTRY.COUNTRY_ABBREVIATION;
    }

    @Override
    public Field<OffsetDateTime> field4() {
        return Country.COUNTRY.LAST_UPDATE;
    }

    @Override
    public Long component1() {
        return getCountryId();
    }

    @Override
    public String component2() {
        return getCountry();
    }

    @Override
    @Nullable
    public String component3() {
        return getCountryAbbreviation();
    }

    @Override
    public OffsetDateTime component4() {
        return getLastUpdate();
    }

    @Override
    public Long value1() {
        return getCountryId();
    }

    @Override
    public String value2() {
        return getCountry();
    }

    @Override
    @Nullable
    public String value3() {
        return getCountryAbbreviation();
    }

    @Override
    public OffsetDateTime value4() {
        return getLastUpdate();
    }

    @Override
    public CountryRecord value1(Long value) {
        setCountryId(value);
        return this;
    }

    @Override
    public CountryRecord value2(String value) {
        setCountry(value);
        return this;
    }

    @Override
    public CountryRecord value3(@Nullable String value) {
        setCountryAbbreviation(value);
        return this;
    }

    @Override
    public CountryRecord value4(OffsetDateTime value) {
        setLastUpdate(value);
        return this;
    }

    @Override
    public CountryRecord values(Long value1, String value2, @Nullable String value3, OffsetDateTime value4) {
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
     * Create a detached CountryRecord
     */
    public CountryRecord() {
        super(Country.COUNTRY);
    }

    /**
     * Create a detached, initialised CountryRecord
     */
    public CountryRecord(Long countryId, String country, @Nullable String countryAbbreviation, OffsetDateTime lastUpdate) {
        super(Country.COUNTRY);

        setCountryId(countryId);
        setCountry(country);
        setCountryAbbreviation(countryAbbreviation);
        setLastUpdate(lastUpdate);
    }

    /**
     * Create a detached, initialised CountryRecord
     */
    public CountryRecord(org.tayrona.sakila.data.tables.pojos.Country value) {
        super(Country.COUNTRY);

        if (value != null) {
            setCountryId(value.getCountryId());
            setCountry(value.getCountry());
            setCountryAbbreviation(value.getCountryAbbreviation());
            setLastUpdate(value.getLastUpdate());
        }
    }
}
