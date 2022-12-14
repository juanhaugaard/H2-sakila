/*
 * This file is generated by jOOQ.
 */
package org.tayrona.sakila.data;


import java.util.Arrays;
import java.util.List;

import org.jooq.Catalog;
import org.jooq.Domain;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;
import org.tayrona.sakila.data.tables.Actor;
import org.tayrona.sakila.data.tables.Address;
import org.tayrona.sakila.data.tables.Category;
import org.tayrona.sakila.data.tables.City;
import org.tayrona.sakila.data.tables.Country;
import org.tayrona.sakila.data.tables.Customer;
import org.tayrona.sakila.data.tables.Film;
import org.tayrona.sakila.data.tables.FilmActor;
import org.tayrona.sakila.data.tables.FilmCategory;
import org.tayrona.sakila.data.tables.FilmText;
import org.tayrona.sakila.data.tables.Inventory;
import org.tayrona.sakila.data.tables.Language;
import org.tayrona.sakila.data.tables.Payment;
import org.tayrona.sakila.data.tables.Rental;
import org.tayrona.sakila.data.tables.Staff;
import org.tayrona.sakila.data.tables.Store;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Public extends SchemaImpl {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>PUBLIC</code>
     */
    public static final Public PUBLIC = new Public();

    /**
     * Actor details table
     */
    public final Actor ACTOR = Actor.ACTOR;

    /**
     * Address details table
     */
    public final Address ADDRESS = Address.ADDRESS;

    /**
     * Category details table
     */
    public final Category CATEGORY = Category.CATEGORY;

    /**
     * City details table
     */
    public final City CITY = City.CITY;

    /**
     * Country details table
     */
    public final Country COUNTRY = Country.COUNTRY;

    /**
     * Customer details table
     */
    public final Customer CUSTOMER = Customer.CUSTOMER;

    /**
     * Film details table
     */
    public final Film FILM = Film.FILM;

    /**
     * Film Actor cross-reference table
     */
    public final FilmActor FILM_ACTOR = FilmActor.FILM_ACTOR;

    /**
     * Film Category cross-reference table
     */
    public final FilmCategory FILM_CATEGORY = FilmCategory.FILM_CATEGORY;

    /**
     * Film title and description table for fulltext search
     */
    public final FilmText FILM_TEXT = FilmText.FILM_TEXT;

    /**
     * Inventory details table
     */
    public final Inventory INVENTORY = Inventory.INVENTORY;

    /**
     * Language details table
     */
    public final Language LANGUAGE = Language.LANGUAGE;

    /**
     * Payment details table
     */
    public final Payment PAYMENT = Payment.PAYMENT;

    /**
     * Rental details table
     */
    public final Rental RENTAL = Rental.RENTAL;

    /**
     * Staff details table
     */
    public final Staff STAFF = Staff.STAFF;

    /**
     * Store details table
     */
    public final Store STORE = Store.STORE;

    /**
     * No further instances allowed
     */
    private Public() {
        super("PUBLIC", null);
    }


    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Domain<?>> getDomains() {
        return Arrays.asList(
            Domains.UPC,
            Domains.YEARVALUE
        );
    }

    @Override
    public final List<Table<?>> getTables() {
        return Arrays.asList(
            Actor.ACTOR,
            Address.ADDRESS,
            Category.CATEGORY,
            City.CITY,
            Country.COUNTRY,
            Customer.CUSTOMER,
            Film.FILM,
            FilmActor.FILM_ACTOR,
            FilmCategory.FILM_CATEGORY,
            FilmText.FILM_TEXT,
            Inventory.INVENTORY,
            Language.LANGUAGE,
            Payment.PAYMENT,
            Rental.RENTAL,
            Staff.STAFF,
            Store.STORE
        );
    }
}
