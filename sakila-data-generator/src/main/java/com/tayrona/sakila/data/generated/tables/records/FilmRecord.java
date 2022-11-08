/*
 * This file is generated by jOOQ.
 */
package com.tayrona.sakila.data.generated.tables.records;


import com.tayrona.sakila.data.generated.enums.MpaaRating;
import com.tayrona.sakila.data.generated.tables.Film;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record13;
import org.jooq.Row13;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * Film details table
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class FilmRecord extends UpdatableRecordImpl<FilmRecord> implements Record13<Long, String, String, Short, Long, Long, Byte, BigDecimal, Short, BigDecimal, MpaaRating, String[], OffsetDateTime> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>SAKILA.FILM.FILM_ID</code>.
     */
    public void setFilmId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>SAKILA.FILM.FILM_ID</code>.
     */
    public Long getFilmId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>SAKILA.FILM.TITLE</code>.
     */
    public void setTitle(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>SAKILA.FILM.TITLE</code>.
     */
    public String getTitle() {
        return (String) get(1);
    }

    /**
     * Setter for <code>SAKILA.FILM.DESCRIPTION</code>.
     */
    public void setDescription(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>SAKILA.FILM.DESCRIPTION</code>.
     */
    public String getDescription() {
        return (String) get(2);
    }

    /**
     * Setter for <code>SAKILA.FILM.RELEASE_YEAR</code>. YearValue a small int
     * between 1900 and 2155
     */
    public void setReleaseYear(Short value) {
        set(3, value);
    }

    /**
     * Getter for <code>SAKILA.FILM.RELEASE_YEAR</code>. YearValue a small int
     * between 1900 and 2155
     */
    public Short getReleaseYear() {
        return (Short) get(3);
    }

    /**
     * Setter for <code>SAKILA.FILM.LANGUAGE_ID</code>.
     */
    public void setLanguageId(Long value) {
        set(4, value);
    }

    /**
     * Getter for <code>SAKILA.FILM.LANGUAGE_ID</code>.
     */
    public Long getLanguageId() {
        return (Long) get(4);
    }

    /**
     * Setter for <code>SAKILA.FILM.ORIGINAL_LANGUAGE_ID</code>.
     */
    public void setOriginalLanguageId(Long value) {
        set(5, value);
    }

    /**
     * Getter for <code>SAKILA.FILM.ORIGINAL_LANGUAGE_ID</code>.
     */
    public Long getOriginalLanguageId() {
        return (Long) get(5);
    }

    /**
     * Setter for <code>SAKILA.FILM.RENTAL_DURATION</code>.
     */
    public void setRentalDuration(Byte value) {
        set(6, value);
    }

    /**
     * Getter for <code>SAKILA.FILM.RENTAL_DURATION</code>.
     */
    public Byte getRentalDuration() {
        return (Byte) get(6);
    }

    /**
     * Setter for <code>SAKILA.FILM.RENTAL_RATE</code>.
     */
    public void setRentalRate(BigDecimal value) {
        set(7, value);
    }

    /**
     * Getter for <code>SAKILA.FILM.RENTAL_RATE</code>.
     */
    public BigDecimal getRentalRate() {
        return (BigDecimal) get(7);
    }

    /**
     * Setter for <code>SAKILA.FILM.LENGTH</code>.
     */
    public void setLength(Short value) {
        set(8, value);
    }

    /**
     * Getter for <code>SAKILA.FILM.LENGTH</code>.
     */
    public Short getLength() {
        return (Short) get(8);
    }

    /**
     * Setter for <code>SAKILA.FILM.REPLACEMENT_COST</code>.
     */
    public void setReplacementCost(BigDecimal value) {
        set(9, value);
    }

    /**
     * Getter for <code>SAKILA.FILM.REPLACEMENT_COST</code>.
     */
    public BigDecimal getReplacementCost() {
        return (BigDecimal) get(9);
    }

    /**
     * Setter for <code>SAKILA.FILM.RATING</code>. MPAA Rating ENUM
     * (G,PG,PG-13,R,NC-17)
     */
    public void setRating(MpaaRating value) {
        set(10, value);
    }

    /**
     * Getter for <code>SAKILA.FILM.RATING</code>. MPAA Rating ENUM
     * (G,PG,PG-13,R,NC-17)
     */
    public MpaaRating getRating() {
        return (MpaaRating) get(10);
    }

    /**
     * Setter for <code>SAKILA.FILM.SPECIAL_FEATURES</code>. Special features
     * array (Trailers, Commentaries, Deleted Scenes, Behind the Scenes)
     */
    public void setSpecialFeatures(String[] value) {
        set(11, value);
    }

    /**
     * Getter for <code>SAKILA.FILM.SPECIAL_FEATURES</code>. Special features
     * array (Trailers, Commentaries, Deleted Scenes, Behind the Scenes)
     */
    public String[] getSpecialFeatures() {
        return (String[]) get(11);
    }

    /**
     * Setter for <code>SAKILA.FILM.LAST_UPDATE</code>.
     */
    public void setLastUpdate(OffsetDateTime value) {
        set(12, value);
    }

    /**
     * Getter for <code>SAKILA.FILM.LAST_UPDATE</code>.
     */
    public OffsetDateTime getLastUpdate() {
        return (OffsetDateTime) get(12);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record13 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row13<Long, String, String, Short, Long, Long, Byte, BigDecimal, Short, BigDecimal, MpaaRating, String[], OffsetDateTime> fieldsRow() {
        return (Row13) super.fieldsRow();
    }

    @Override
    public Row13<Long, String, String, Short, Long, Long, Byte, BigDecimal, Short, BigDecimal, MpaaRating, String[], OffsetDateTime> valuesRow() {
        return (Row13) super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return Film.FILM.FILM_ID;
    }

    @Override
    public Field<String> field2() {
        return Film.FILM.TITLE;
    }

    @Override
    public Field<String> field3() {
        return Film.FILM.DESCRIPTION;
    }

    @Override
    public Field<Short> field4() {
        return Film.FILM.RELEASE_YEAR;
    }

    @Override
    public Field<Long> field5() {
        return Film.FILM.LANGUAGE_ID;
    }

    @Override
    public Field<Long> field6() {
        return Film.FILM.ORIGINAL_LANGUAGE_ID;
    }

    @Override
    public Field<Byte> field7() {
        return Film.FILM.RENTAL_DURATION;
    }

    @Override
    public Field<BigDecimal> field8() {
        return Film.FILM.RENTAL_RATE;
    }

    @Override
    public Field<Short> field9() {
        return Film.FILM.LENGTH;
    }

    @Override
    public Field<BigDecimal> field10() {
        return Film.FILM.REPLACEMENT_COST;
    }

    @Override
    public Field<MpaaRating> field11() {
        return Film.FILM.RATING;
    }

    @Override
    public Field<String[]> field12() {
        return Film.FILM.SPECIAL_FEATURES;
    }

    @Override
    public Field<OffsetDateTime> field13() {
        return Film.FILM.LAST_UPDATE;
    }

    @Override
    public Long component1() {
        return getFilmId();
    }

    @Override
    public String component2() {
        return getTitle();
    }

    @Override
    public String component3() {
        return getDescription();
    }

    @Override
    public Short component4() {
        return getReleaseYear();
    }

    @Override
    public Long component5() {
        return getLanguageId();
    }

    @Override
    public Long component6() {
        return getOriginalLanguageId();
    }

    @Override
    public Byte component7() {
        return getRentalDuration();
    }

    @Override
    public BigDecimal component8() {
        return getRentalRate();
    }

    @Override
    public Short component9() {
        return getLength();
    }

    @Override
    public BigDecimal component10() {
        return getReplacementCost();
    }

    @Override
    public MpaaRating component11() {
        return getRating();
    }

    @Override
    public String[] component12() {
        return getSpecialFeatures();
    }

    @Override
    public OffsetDateTime component13() {
        return getLastUpdate();
    }

    @Override
    public Long value1() {
        return getFilmId();
    }

    @Override
    public String value2() {
        return getTitle();
    }

    @Override
    public String value3() {
        return getDescription();
    }

    @Override
    public Short value4() {
        return getReleaseYear();
    }

    @Override
    public Long value5() {
        return getLanguageId();
    }

    @Override
    public Long value6() {
        return getOriginalLanguageId();
    }

    @Override
    public Byte value7() {
        return getRentalDuration();
    }

    @Override
    public BigDecimal value8() {
        return getRentalRate();
    }

    @Override
    public Short value9() {
        return getLength();
    }

    @Override
    public BigDecimal value10() {
        return getReplacementCost();
    }

    @Override
    public MpaaRating value11() {
        return getRating();
    }

    @Override
    public String[] value12() {
        return getSpecialFeatures();
    }

    @Override
    public OffsetDateTime value13() {
        return getLastUpdate();
    }

    @Override
    public FilmRecord value1(Long value) {
        setFilmId(value);
        return this;
    }

    @Override
    public FilmRecord value2(String value) {
        setTitle(value);
        return this;
    }

    @Override
    public FilmRecord value3(String value) {
        setDescription(value);
        return this;
    }

    @Override
    public FilmRecord value4(Short value) {
        setReleaseYear(value);
        return this;
    }

    @Override
    public FilmRecord value5(Long value) {
        setLanguageId(value);
        return this;
    }

    @Override
    public FilmRecord value6(Long value) {
        setOriginalLanguageId(value);
        return this;
    }

    @Override
    public FilmRecord value7(Byte value) {
        setRentalDuration(value);
        return this;
    }

    @Override
    public FilmRecord value8(BigDecimal value) {
        setRentalRate(value);
        return this;
    }

    @Override
    public FilmRecord value9(Short value) {
        setLength(value);
        return this;
    }

    @Override
    public FilmRecord value10(BigDecimal value) {
        setReplacementCost(value);
        return this;
    }

    @Override
    public FilmRecord value11(MpaaRating value) {
        setRating(value);
        return this;
    }

    @Override
    public FilmRecord value12(String[] value) {
        setSpecialFeatures(value);
        return this;
    }

    @Override
    public FilmRecord value13(OffsetDateTime value) {
        setLastUpdate(value);
        return this;
    }

    @Override
    public FilmRecord values(Long value1, String value2, String value3, Short value4, Long value5, Long value6, Byte value7, BigDecimal value8, Short value9, BigDecimal value10, MpaaRating value11, String[] value12, OffsetDateTime value13) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        value9(value9);
        value10(value10);
        value11(value11);
        value12(value12);
        value13(value13);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached FilmRecord
     */
    public FilmRecord() {
        super(Film.FILM);
    }

    /**
     * Create a detached, initialised FilmRecord
     */
    public FilmRecord(Long filmId, String title, String description, Short releaseYear, Long languageId, Long originalLanguageId, Byte rentalDuration, BigDecimal rentalRate, Short length, BigDecimal replacementCost, MpaaRating rating, String[] specialFeatures, OffsetDateTime lastUpdate) {
        super(Film.FILM);

        setFilmId(filmId);
        setTitle(title);
        setDescription(description);
        setReleaseYear(releaseYear);
        setLanguageId(languageId);
        setOriginalLanguageId(originalLanguageId);
        setRentalDuration(rentalDuration);
        setRentalRate(rentalRate);
        setLength(length);
        setReplacementCost(replacementCost);
        setRating(rating);
        setSpecialFeatures(specialFeatures);
        setLastUpdate(lastUpdate);
    }
}