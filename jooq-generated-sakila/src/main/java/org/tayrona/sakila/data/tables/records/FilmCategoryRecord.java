/*
 * This file is generated by jOOQ.
 */
package org.tayrona.sakila.data.tables.records;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import java.time.OffsetDateTime;

import org.jooq.Field;
import org.jooq.Record2;
import org.jooq.Record3;
import org.jooq.Row3;
import org.jooq.impl.UpdatableRecordImpl;
import org.tayrona.sakila.data.tables.FilmCategory;


/**
 * Film Category cross-reference table
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
@Entity
@Table(
    name = "FILM_CATEGORY",
    schema = "PUBLIC"
)
public class FilmCategoryRecord extends UpdatableRecordImpl<FilmCategoryRecord> implements Record3<Long, Long, OffsetDateTime> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>PUBLIC.FILM_CATEGORY.FILM_ID</code>.
     */
    public void setFilmId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>PUBLIC.FILM_CATEGORY.FILM_ID</code>.
     */
    @Column(name = "FILM_ID", nullable = false)
    @NotNull
    public Long getFilmId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>PUBLIC.FILM_CATEGORY.CATEGORY_ID</code>.
     */
    public void setCategoryId(Long value) {
        set(1, value);
    }

    /**
     * Getter for <code>PUBLIC.FILM_CATEGORY.CATEGORY_ID</code>.
     */
    @Column(name = "CATEGORY_ID", nullable = false)
    @NotNull
    public Long getCategoryId() {
        return (Long) get(1);
    }

    /**
     * Setter for <code>PUBLIC.FILM_CATEGORY.LAST_UPDATE</code>.
     */
    public void setLastUpdate(OffsetDateTime value) {
        set(2, value);
    }

    /**
     * Getter for <code>PUBLIC.FILM_CATEGORY.LAST_UPDATE</code>.
     */
    @Column(name = "LAST_UPDATE", nullable = false, precision = 6)
    public OffsetDateTime getLastUpdate() {
        return (OffsetDateTime) get(2);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record2<Long, Long> key() {
        return (Record2) super.key();
    }

    // -------------------------------------------------------------------------
    // Record3 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row3<Long, Long, OffsetDateTime> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    @Override
    public Row3<Long, Long, OffsetDateTime> valuesRow() {
        return (Row3) super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return FilmCategory.FILM_CATEGORY.FILM_ID;
    }

    @Override
    public Field<Long> field2() {
        return FilmCategory.FILM_CATEGORY.CATEGORY_ID;
    }

    @Override
    public Field<OffsetDateTime> field3() {
        return FilmCategory.FILM_CATEGORY.LAST_UPDATE;
    }

    @Override
    public Long component1() {
        return getFilmId();
    }

    @Override
    public Long component2() {
        return getCategoryId();
    }

    @Override
    public OffsetDateTime component3() {
        return getLastUpdate();
    }

    @Override
    public Long value1() {
        return getFilmId();
    }

    @Override
    public Long value2() {
        return getCategoryId();
    }

    @Override
    public OffsetDateTime value3() {
        return getLastUpdate();
    }

    @Override
    public FilmCategoryRecord value1(Long value) {
        setFilmId(value);
        return this;
    }

    @Override
    public FilmCategoryRecord value2(Long value) {
        setCategoryId(value);
        return this;
    }

    @Override
    public FilmCategoryRecord value3(OffsetDateTime value) {
        setLastUpdate(value);
        return this;
    }

    @Override
    public FilmCategoryRecord values(Long value1, Long value2, OffsetDateTime value3) {
        value1(value1);
        value2(value2);
        value3(value3);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached FilmCategoryRecord
     */
    public FilmCategoryRecord() {
        super(FilmCategory.FILM_CATEGORY);
    }

    /**
     * Create a detached, initialised FilmCategoryRecord
     */
    public FilmCategoryRecord(Long filmId, Long categoryId, OffsetDateTime lastUpdate) {
        super(FilmCategory.FILM_CATEGORY);

        setFilmId(filmId);
        setCategoryId(categoryId);
        setLastUpdate(lastUpdate);
    }

    /**
     * Create a detached, initialised FilmCategoryRecord
     */
    public FilmCategoryRecord(org.tayrona.sakila.data.tables.pojos.FilmCategory value) {
        super(FilmCategory.FILM_CATEGORY);

        if (value != null) {
            setFilmId(value.getFilmId());
            setCategoryId(value.getCategoryId());
            setLastUpdate(value.getLastUpdate());
        }
    }
}
