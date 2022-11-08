/*
 * This file is generated by jOOQ.
 */
package com.tayrona.sakila.data.generated.tables;


import com.tayrona.sakila.data.generated.Indexes;
import com.tayrona.sakila.data.generated.Keys;
import com.tayrona.sakila.data.generated.Sakila;
import com.tayrona.sakila.data.generated.tables.records.FilmTextRecord;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function3;
import org.jooq.Index;
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
 * Film title and description table for fulltext search
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class FilmText extends TableImpl<FilmTextRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>SAKILA.FILM_TEXT</code>
     */
    public static final FilmText FILM_TEXT = new FilmText();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<FilmTextRecord> getRecordType() {
        return FilmTextRecord.class;
    }

    /**
     * The column <code>SAKILA.FILM_TEXT.FILM_ID</code>.
     */
    public final TableField<FilmTextRecord, Long> FILM_ID = createField(DSL.name("FILM_ID"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>SAKILA.FILM_TEXT.TITLE</code>.
     */
    public final TableField<FilmTextRecord, String> TITLE = createField(DSL.name("TITLE"), SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>SAKILA.FILM_TEXT.DESCRIPTION</code>.
     */
    public final TableField<FilmTextRecord, String> DESCRIPTION = createField(DSL.name("DESCRIPTION"), SQLDataType.VARCHAR(1000000000), this, "");

    private FilmText(Name alias, Table<FilmTextRecord> aliased) {
        this(alias, aliased, null);
    }

    private FilmText(Name alias, Table<FilmTextRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment("Film title and description table for fulltext search"), TableOptions.table());
    }

    /**
     * Create an aliased <code>SAKILA.FILM_TEXT</code> table reference
     */
    public FilmText(String alias) {
        this(DSL.name(alias), FILM_TEXT);
    }

    /**
     * Create an aliased <code>SAKILA.FILM_TEXT</code> table reference
     */
    public FilmText(Name alias) {
        this(alias, FILM_TEXT);
    }

    /**
     * Create a <code>SAKILA.FILM_TEXT</code> table reference
     */
    public FilmText() {
        this(DSL.name("FILM_TEXT"), null);
    }

    public <O extends Record> FilmText(Table<O> child, ForeignKey<O, FilmTextRecord> key) {
        super(child, key, FILM_TEXT);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Sakila.SAKILA;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.asList(Indexes.IDX_TITLE_DESCRIPTION);
    }

    @Override
    public UniqueKey<FilmTextRecord> getPrimaryKey() {
        return Keys.PK_FILM_TEXT;
    }

    @Override
    public FilmText as(String alias) {
        return new FilmText(DSL.name(alias), this);
    }

    @Override
    public FilmText as(Name alias) {
        return new FilmText(alias, this);
    }

    @Override
    public FilmText as(Table<?> alias) {
        return new FilmText(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public FilmText rename(String name) {
        return new FilmText(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public FilmText rename(Name name) {
        return new FilmText(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public FilmText rename(Table<?> name) {
        return new FilmText(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row3 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row3<Long, String, String> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function3<? super Long, ? super String, ? super String, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function3<? super Long, ? super String, ? super String, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
