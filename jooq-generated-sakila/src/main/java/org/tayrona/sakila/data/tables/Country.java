/*
 * This file is generated by jOOQ.
 */
package org.tayrona.sakila.data.tables;


import java.time.OffsetDateTime;
import java.util.function.Function;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function4;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row4;
import org.jooq.Schema;
import org.jooq.SelectField;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;
import org.tayrona.sakila.data.Keys;
import org.tayrona.sakila.data.Public;
import org.tayrona.sakila.data.tables.records.CountryRecord;


/**
 * Country details table
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Country extends TableImpl<CountryRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>PUBLIC.COUNTRY</code>
     */
    public static final Country COUNTRY = new Country();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<CountryRecord> getRecordType() {
        return CountryRecord.class;
    }

    /**
     * The column <code>PUBLIC.COUNTRY.COUNTRY_ID</code>.
     */
    public final TableField<CountryRecord, Long> COUNTRY_ID = createField(DSL.name("COUNTRY_ID"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>PUBLIC.COUNTRY.COUNTRY</code>.
     */
    public final TableField<CountryRecord, String> COUNTRY_ = createField(DSL.name("COUNTRY"), SQLDataType.VARCHAR(50).nullable(false), this, "");

    /**
     * The column <code>PUBLIC.COUNTRY.COUNTRY_ABBREVIATION</code>.
     */
    public final TableField<CountryRecord, String> COUNTRY_ABBREVIATION = createField(DSL.name("COUNTRY_ABBREVIATION"), SQLDataType.VARCHAR(5), this, "");

    /**
     * The column <code>PUBLIC.COUNTRY.LAST_UPDATE</code>.
     */
    public final TableField<CountryRecord, OffsetDateTime> LAST_UPDATE = createField(DSL.name("LAST_UPDATE"), SQLDataType.TIMESTAMPWITHTIMEZONE(6).nullable(false).defaultValue(DSL.field("CURRENT_TIMESTAMP", SQLDataType.TIMESTAMPWITHTIMEZONE)), this, "");

    private Country(Name alias, Table<CountryRecord> aliased) {
        this(alias, aliased, null);
    }

    private Country(Name alias, Table<CountryRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment("Country details table"), TableOptions.table());
    }

    /**
     * Create an aliased <code>PUBLIC.COUNTRY</code> table reference
     */
    public Country(String alias) {
        this(DSL.name(alias), COUNTRY);
    }

    /**
     * Create an aliased <code>PUBLIC.COUNTRY</code> table reference
     */
    public Country(Name alias) {
        this(alias, COUNTRY);
    }

    /**
     * Create a <code>PUBLIC.COUNTRY</code> table reference
     */
    public Country() {
        this(DSL.name("COUNTRY"), null);
    }

    public <O extends Record> Country(Table<O> child, ForeignKey<O, CountryRecord> key) {
        super(child, key, COUNTRY);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public Identity<CountryRecord, Long> getIdentity() {
        return (Identity<CountryRecord, Long>) super.getIdentity();
    }

    @Override
    public UniqueKey<CountryRecord> getPrimaryKey() {
        return Keys.PK_COUNTRY;
    }

    @Override
    public Country as(String alias) {
        return new Country(DSL.name(alias), this);
    }

    @Override
    public Country as(Name alias) {
        return new Country(alias, this);
    }

    @Override
    public Country as(Table<?> alias) {
        return new Country(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public Country rename(String name) {
        return new Country(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Country rename(Name name) {
        return new Country(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public Country rename(Table<?> name) {
        return new Country(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row4 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row4<Long, String, String, OffsetDateTime> fieldsRow() {
        return (Row4) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function4<? super Long, ? super String, ? super String, ? super OffsetDateTime, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function4<? super Long, ? super String, ? super String, ? super OffsetDateTime, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
