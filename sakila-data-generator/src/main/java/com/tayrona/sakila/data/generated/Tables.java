/*
 * This file is generated by jOOQ.
 */
package com.tayrona.sakila.data.generated;


import com.tayrona.sakila.data.generated.tables.Actor;
import com.tayrona.sakila.data.generated.tables.Address;
import com.tayrona.sakila.data.generated.tables.Category;
import com.tayrona.sakila.data.generated.tables.City;
import com.tayrona.sakila.data.generated.tables.Country;
import com.tayrona.sakila.data.generated.tables.Customer;
import com.tayrona.sakila.data.generated.tables.Film;
import com.tayrona.sakila.data.generated.tables.FilmActor;
import com.tayrona.sakila.data.generated.tables.FilmCategory;
import com.tayrona.sakila.data.generated.tables.FilmText;
import com.tayrona.sakila.data.generated.tables.Inventory;
import com.tayrona.sakila.data.generated.tables.Language;
import com.tayrona.sakila.data.generated.tables.Payment;
import com.tayrona.sakila.data.generated.tables.Rental;
import com.tayrona.sakila.data.generated.tables.Staff;
import com.tayrona.sakila.data.generated.tables.Store;
import com.tayrona.sakila.data.generated.tables.TestTable;


/**
 * Convenience access to all tables in SAKILA.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Tables {

    /**
     * Actor details table
     */
    public static final Actor ACTOR = Actor.ACTOR;

    /**
     * Address details table
     */
    public static final Address ADDRESS = Address.ADDRESS;

    /**
     * Category details table
     */
    public static final Category CATEGORY = Category.CATEGORY;

    /**
     * City details table
     */
    public static final City CITY = City.CITY;

    /**
     * Country details table
     */
    public static final Country COUNTRY = Country.COUNTRY;

    /**
     * Customer details table
     */
    public static final Customer CUSTOMER = Customer.CUSTOMER;

    /**
     * Film details table
     */
    public static final Film FILM = Film.FILM;

    /**
     * Film Actor cross-reference table
     */
    public static final FilmActor FILM_ACTOR = FilmActor.FILM_ACTOR;

    /**
     * Film Category cross-reference table
     */
    public static final FilmCategory FILM_CATEGORY = FilmCategory.FILM_CATEGORY;

    /**
     * Film title and description table for fulltext search
     */
    public static final FilmText FILM_TEXT = FilmText.FILM_TEXT;

    /**
     * Inventory details table
     */
    public static final Inventory INVENTORY = Inventory.INVENTORY;

    /**
     * Language details table
     */
    public static final Language LANGUAGE = Language.LANGUAGE;

    /**
     * Payment details table
     */
    public static final Payment PAYMENT = Payment.PAYMENT;

    /**
     * Rental details table
     */
    public static final Rental RENTAL = Rental.RENTAL;

    /**
     * Staff details table
     */
    public static final Staff STAFF = Staff.STAFF;

    /**
     * Store details table
     */
    public static final Store STORE = Store.STORE;

    /**
     * test table
     */
    public static final TestTable TEST_TABLE = TestTable.TEST_TABLE;
}