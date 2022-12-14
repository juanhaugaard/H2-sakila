/*
 * This file is generated by jOOQ.
 */
package org.tayrona.sakila.data.tables;


import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function4;
import org.jooq.Identity;
import org.jooq.Index;
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
import org.tayrona.sakila.data.Indexes;
import org.tayrona.sakila.data.Keys;
import org.tayrona.sakila.data.Public;
import org.tayrona.sakila.data.tables.records.InventoryRecord;


/**
 * Inventory details table
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Inventory extends TableImpl<InventoryRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>PUBLIC.INVENTORY</code>
     */
    public static final Inventory INVENTORY = new Inventory();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<InventoryRecord> getRecordType() {
        return InventoryRecord.class;
    }

    /**
     * The column <code>PUBLIC.INVENTORY.INVENTORY_ID</code>.
     */
    public final TableField<InventoryRecord, Long> INVENTORY_ID = createField(DSL.name("INVENTORY_ID"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>PUBLIC.INVENTORY.FILM_ID</code>.
     */
    public final TableField<InventoryRecord, Long> FILM_ID = createField(DSL.name("FILM_ID"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>PUBLIC.INVENTORY.STORE_ID</code>.
     */
    public final TableField<InventoryRecord, Long> STORE_ID = createField(DSL.name("STORE_ID"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>PUBLIC.INVENTORY.LAST_UPDATE</code>.
     */
    public final TableField<InventoryRecord, OffsetDateTime> LAST_UPDATE = createField(DSL.name("LAST_UPDATE"), SQLDataType.TIMESTAMPWITHTIMEZONE(6).nullable(false).defaultValue(DSL.field("CURRENT_TIMESTAMP", SQLDataType.TIMESTAMPWITHTIMEZONE)), this, "");

    private Inventory(Name alias, Table<InventoryRecord> aliased) {
        this(alias, aliased, null);
    }

    private Inventory(Name alias, Table<InventoryRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment("Inventory details table"), TableOptions.table());
    }

    /**
     * Create an aliased <code>PUBLIC.INVENTORY</code> table reference
     */
    public Inventory(String alias) {
        this(DSL.name(alias), INVENTORY);
    }

    /**
     * Create an aliased <code>PUBLIC.INVENTORY</code> table reference
     */
    public Inventory(Name alias) {
        this(alias, INVENTORY);
    }

    /**
     * Create a <code>PUBLIC.INVENTORY</code> table reference
     */
    public Inventory() {
        this(DSL.name("INVENTORY"), null);
    }

    public <O extends Record> Inventory(Table<O> child, ForeignKey<O, InventoryRecord> key) {
        super(child, key, INVENTORY);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.asList(Indexes.IDX_STORE_ID_FILM_ID);
    }

    @Override
    public Identity<InventoryRecord, Long> getIdentity() {
        return (Identity<InventoryRecord, Long>) super.getIdentity();
    }

    @Override
    public UniqueKey<InventoryRecord> getPrimaryKey() {
        return Keys.PK_INVENTORY;
    }

    @Override
    public List<ForeignKey<InventoryRecord, ?>> getReferences() {
        return Arrays.asList(Keys.FK_INVENTORY_FILM, Keys.FK_INVENTORY_STORE);
    }

    private transient Film _film;
    private transient Store _store;

    /**
     * Get the implicit join path to the <code>PUBLIC.FILM</code> table.
     */
    public Film film() {
        if (_film == null)
            _film = new Film(this, Keys.FK_INVENTORY_FILM);

        return _film;
    }

    /**
     * Get the implicit join path to the <code>PUBLIC.STORE</code> table.
     */
    public Store store() {
        if (_store == null)
            _store = new Store(this, Keys.FK_INVENTORY_STORE);

        return _store;
    }

    @Override
    public Inventory as(String alias) {
        return new Inventory(DSL.name(alias), this);
    }

    @Override
    public Inventory as(Name alias) {
        return new Inventory(alias, this);
    }

    @Override
    public Inventory as(Table<?> alias) {
        return new Inventory(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public Inventory rename(String name) {
        return new Inventory(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Inventory rename(Name name) {
        return new Inventory(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public Inventory rename(Table<?> name) {
        return new Inventory(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row4 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row4<Long, Long, Long, OffsetDateTime> fieldsRow() {
        return (Row4) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function4<? super Long, ? super Long, ? super Long, ? super OffsetDateTime, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function4<? super Long, ? super Long, ? super Long, ? super OffsetDateTime, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
