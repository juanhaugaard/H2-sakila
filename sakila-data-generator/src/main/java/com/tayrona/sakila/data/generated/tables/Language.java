/*
 * This file is generated by jOOQ.
 */
package com.tayrona.sakila.data.generated.tables;


import com.tayrona.sakila.data.generated.Keys;
import com.tayrona.sakila.data.generated.Sakila;
import com.tayrona.sakila.data.generated.tables.records.LanguageRecord;

import java.time.OffsetDateTime;
import java.util.function.Function;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function3;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row3;
import org.jooq.Schema;
import org.jooq.SelectField;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * Language details table
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Language extends TableImpl<LanguageRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>SAKILA.LANGUAGE</code>
     */
    public static final Language LANGUAGE = new Language();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<LanguageRecord> getRecordType() {
        return LanguageRecord.class;
    }

    /**
     * The column <code>SAKILA.LANGUAGE.LANGUAGE_ID</code>.
     */
    public final TableField<LanguageRecord, Long> LANGUAGE_ID = createField(DSL.name("LANGUAGE_ID"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>SAKILA.LANGUAGE.NAME</code>.
     */
    public final TableField<LanguageRecord, String> NAME = createField(DSL.name("NAME"), SQLDataType.VARCHAR(20).nullable(false), this, "");

    /**
     * The column <code>SAKILA.LANGUAGE.LAST_UPDATE</code>.
     */
    public final TableField<LanguageRecord, OffsetDateTime> LAST_UPDATE = createField(DSL.name("LAST_UPDATE"), SQLDataType.TIMESTAMPWITHTIMEZONE(6).nullable(false).defaultValue(DSL.field("CURRENT_TIMESTAMP", SQLDataType.TIMESTAMPWITHTIMEZONE)), this, "");

    private Language(Name alias, Table<LanguageRecord> aliased) {
        this(alias, aliased, null);
    }

    private Language(Name alias, Table<LanguageRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment("Language details table"), TableOptions.table());
    }

    /**
     * Create an aliased <code>SAKILA.LANGUAGE</code> table reference
     */
    public Language(String alias) {
        this(DSL.name(alias), LANGUAGE);
    }

    /**
     * Create an aliased <code>SAKILA.LANGUAGE</code> table reference
     */
    public Language(Name alias) {
        this(alias, LANGUAGE);
    }

    /**
     * Create a <code>SAKILA.LANGUAGE</code> table reference
     */
    public Language() {
        this(DSL.name("LANGUAGE"), null);
    }

    public <O extends Record> Language(Table<O> child, ForeignKey<O, LanguageRecord> key) {
        super(child, key, LANGUAGE);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Sakila.SAKILA;
    }

    @Override
    public Identity<LanguageRecord, Long> getIdentity() {
        return (Identity<LanguageRecord, Long>) super.getIdentity();
    }

    @Override
    public UniqueKey<LanguageRecord> getPrimaryKey() {
        return Keys.PK_LANGUAGE;
    }

    @Override
    public Language as(String alias) {
        return new Language(DSL.name(alias), this);
    }

    @Override
    public Language as(Name alias) {
        return new Language(alias, this);
    }

    @Override
    public Language as(Table<?> alias) {
        return new Language(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public Language rename(String name) {
        return new Language(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Language rename(Name name) {
        return new Language(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public Language rename(Table<?> name) {
        return new Language(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row3 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row3<Long, String, OffsetDateTime> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function3<? super Long, ? super String, ? super OffsetDateTime, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function3<? super Long, ? super String, ? super OffsetDateTime, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
