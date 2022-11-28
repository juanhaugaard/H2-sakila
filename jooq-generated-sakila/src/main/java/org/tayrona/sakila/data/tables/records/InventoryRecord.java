/*
 * This file is generated by jOOQ.
 */
package org.tayrona.sakila.data.tables.records;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record4;
import org.jooq.Row4;
import org.jooq.impl.UpdatableRecordImpl;
import org.tayrona.sakila.data.tables.Inventory;

import java.time.OffsetDateTime;


/**
 * Inventory details table
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
@Entity
@Table(
    name = "INVENTORY",
    schema = "PUBLIC",
    indexes = {
        @Index(name = "IDX_STORE_ID_FILM_ID", unique = true, columnList = "STORE_ID ASC, FILM_ID ASC")
    }
)
public class InventoryRecord extends UpdatableRecordImpl<InventoryRecord> implements Record4<Long, Long, Long, OffsetDateTime> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>PUBLIC.INVENTORY.INVENTORY_ID</code>.
     */
    public void setInventoryId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>PUBLIC.INVENTORY.INVENTORY_ID</code>.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "INVENTORY_ID", nullable = false)
    public Long getInventoryId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>PUBLIC.INVENTORY.FILM_ID</code>.
     */
    public void setFilmId(Long value) {
        set(1, value);
    }

    /**
     * Getter for <code>PUBLIC.INVENTORY.FILM_ID</code>.
     */
    @Column(name = "FILM_ID", nullable = false)
    @NotNull
    public Long getFilmId() {
        return (Long) get(1);
    }

    /**
     * Setter for <code>PUBLIC.INVENTORY.STORE_ID</code>.
     */
    public void setStoreId(Long value) {
        set(2, value);
    }

    /**
     * Getter for <code>PUBLIC.INVENTORY.STORE_ID</code>.
     */
    @Column(name = "STORE_ID", nullable = false)
    @NotNull
    public Long getStoreId() {
        return (Long) get(2);
    }

    /**
     * Setter for <code>PUBLIC.INVENTORY.LAST_UPDATE</code>.
     */
    public void setLastUpdate(OffsetDateTime value) {
        set(3, value);
    }

    /**
     * Getter for <code>PUBLIC.INVENTORY.LAST_UPDATE</code>.
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
    public Row4<Long, Long, Long, OffsetDateTime> fieldsRow() {
        return (Row4) super.fieldsRow();
    }

    @Override
    public Row4<Long, Long, Long, OffsetDateTime> valuesRow() {
        return (Row4) super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return Inventory.INVENTORY.INVENTORY_ID;
    }

    @Override
    public Field<Long> field2() {
        return Inventory.INVENTORY.FILM_ID;
    }

    @Override
    public Field<Long> field3() {
        return Inventory.INVENTORY.STORE_ID;
    }

    @Override
    public Field<OffsetDateTime> field4() {
        return Inventory.INVENTORY.LAST_UPDATE;
    }

    @Override
    public Long component1() {
        return getInventoryId();
    }

    @Override
    public Long component2() {
        return getFilmId();
    }

    @Override
    public Long component3() {
        return getStoreId();
    }

    @Override
    public OffsetDateTime component4() {
        return getLastUpdate();
    }

    @Override
    public Long value1() {
        return getInventoryId();
    }

    @Override
    public Long value2() {
        return getFilmId();
    }

    @Override
    public Long value3() {
        return getStoreId();
    }

    @Override
    public OffsetDateTime value4() {
        return getLastUpdate();
    }

    @Override
    public InventoryRecord value1(Long value) {
        setInventoryId(value);
        return this;
    }

    @Override
    public InventoryRecord value2(Long value) {
        setFilmId(value);
        return this;
    }

    @Override
    public InventoryRecord value3(Long value) {
        setStoreId(value);
        return this;
    }

    @Override
    public InventoryRecord value4(OffsetDateTime value) {
        setLastUpdate(value);
        return this;
    }

    @Override
    public InventoryRecord values(Long value1, Long value2, Long value3, OffsetDateTime value4) {
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
     * Create a detached InventoryRecord
     */
    public InventoryRecord() {
        super(Inventory.INVENTORY);
    }

    /**
     * Create a detached, initialised InventoryRecord
     */
    public InventoryRecord(Long inventoryId, Long filmId, Long storeId, OffsetDateTime lastUpdate) {
        super(Inventory.INVENTORY);

        setInventoryId(inventoryId);
        setFilmId(filmId);
        setStoreId(storeId);
        setLastUpdate(lastUpdate);
    }

    /**
     * Create a detached, initialised InventoryRecord
     */
    public InventoryRecord(org.tayrona.sakila.data.tables.pojos.Inventory value) {
        super(Inventory.INVENTORY);

        if (value != null) {
            setInventoryId(value.getInventoryId());
            setFilmId(value.getFilmId());
            setStoreId(value.getStoreId());
            setLastUpdate(value.getLastUpdate());
        }
    }
}
