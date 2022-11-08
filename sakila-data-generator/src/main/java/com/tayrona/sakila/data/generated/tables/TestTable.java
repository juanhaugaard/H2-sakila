/*
 * This file is generated by jOOQ.
 */
package com.tayrona.sakila.data.generated.tables;


import com.tayrona.sakila.data.generated.Keys;
import com.tayrona.sakila.data.generated.Sakila;
import com.tayrona.sakila.data.generated.tables.records.TestTableRecord;

import java.util.function.Function;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function2;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row2;
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
 * test table
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class TestTable extends TableImpl<TestTableRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>SAKILA.TEST_TABLE</code>
     */
    public static final TestTable TEST_TABLE = new TestTable();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<TestTableRecord> getRecordType() {
        return TestTableRecord.class;
    }

    /**
     * The column <code>SAKILA.TEST_TABLE.ID</code>.
     */
    public final TableField<TestTableRecord, Long> ID = createField(DSL.name("ID"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>SAKILA.TEST_TABLE.FEATURES</code>.
     */
    public final TableField<TestTableRecord, String[]> FEATURES = createField(DSL.name("FEATURES"), SQLDataType.VARCHAR(200).getArrayDataType(), this, "");

    private TestTable(Name alias, Table<TestTableRecord> aliased) {
        this(alias, aliased, null);
    }

    private TestTable(Name alias, Table<TestTableRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment("test table"), TableOptions.table());
    }

    /**
     * Create an aliased <code>SAKILA.TEST_TABLE</code> table reference
     */
    public TestTable(String alias) {
        this(DSL.name(alias), TEST_TABLE);
    }

    /**
     * Create an aliased <code>SAKILA.TEST_TABLE</code> table reference
     */
    public TestTable(Name alias) {
        this(alias, TEST_TABLE);
    }

    /**
     * Create a <code>SAKILA.TEST_TABLE</code> table reference
     */
    public TestTable() {
        this(DSL.name("TEST_TABLE"), null);
    }

    public <O extends Record> TestTable(Table<O> child, ForeignKey<O, TestTableRecord> key) {
        super(child, key, TEST_TABLE);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Sakila.SAKILA;
    }

    @Override
    public Identity<TestTableRecord, Long> getIdentity() {
        return (Identity<TestTableRecord, Long>) super.getIdentity();
    }

    @Override
    public UniqueKey<TestTableRecord> getPrimaryKey() {
        return Keys.PK_TESTTABLE;
    }

    @Override
    public TestTable as(String alias) {
        return new TestTable(DSL.name(alias), this);
    }

    @Override
    public TestTable as(Name alias) {
        return new TestTable(alias, this);
    }

    @Override
    public TestTable as(Table<?> alias) {
        return new TestTable(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public TestTable rename(String name) {
        return new TestTable(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public TestTable rename(Name name) {
        return new TestTable(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public TestTable rename(Table<?> name) {
        return new TestTable(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row2 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row2<Long, String[]> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function2<? super Long, ? super String[], ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function2<? super Long, ? super String[], ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}