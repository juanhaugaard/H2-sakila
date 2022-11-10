--liquibase formatted sql

--changeset Juan Haugaard:1
--comment: Create domain yearValue
            CREATE DOMAIN YEARVALUE AS SMALLINT
            COMMENT 'YearValue a small int between 1900 and 2155'
            CHECK ((VALUE >= 1900) AND (VALUE <= 2155));
--rollback DROP DOMAIN YEARVALUE;

--changeset Juan Haugaard:2
--comment: Create domain mpaa_rating
            CREATE DOMAIN MPAA_RATING AS ENUM ('G','PG','PG-13','R','NC-17')
            COMMENT 'MPAA Rating ENUM (G,PG,PG-13,R,NC-17)';
--rollback DROP DOMAIN MPAA_RATING;

--changeset Juan Haugaard:3
--comment: Create domain Special Features ENUM
            CREATE DOMAIN SPECIAL_FEATURES_ENUM AS ENUM ('Trailers','Commentaries','Deleted Scenes','Behind the Scenes')
            COMMENT 'Special Features ENUM (Trailers, Commentaries, Deleted Scenes, Behind the Scenes)';
--rollback DROP DOMAIN SPECIAL_FEATURES_ENUM;

--changeset Juan Haugaard:4
--comment: Create domain special_features
            CREATE DOMAIN SPECIAL_FEATURES AS VARCHAR(200) ARRAY[4]
            COMMENT 'Special features array (Trailers, Commentaries, Deleted Scenes, Behind the Scenes)'
            CHECK(EXISTS(VALUES ('Trailers', 'Commentaries', 'Deleted Scenes', 'Behind the Scenes')));
--rollback DROP DOMAIN SPECIAL_FEATURES;

--changeset Juan Haugaard:5
--comment: Create text concatenation function
            CREATE ALIAS _group_concat DETERMINISTIC AS
            'String group_concat(String text1, String text2) {
            if (null == text1) return text2;
            if (null == text2) return text1;
            return text1 + "," + text2;}';
--rollback DROP ALIAS _group_concat;

--changeset Juan Haugaard:6
--comment: Create text reverse function
--            CREATE ALIAS REVERSE DETERMINISTIC FOR 'com.tayrona.sakila.procedures.StringUtils.reverse';
            CREATE ALIAS REVERSE DETERMINISTIC AS
            'String reverse(String value) {
            if (null != value) return (new StringBuilder(value)).reverse().toString();
            else return null;}';
--rollback DROP ALIAS REVERSE;

--changeset Juan Haugaard:7
--comment: Create table Category
            CREATE TABLE CATEGORY (
                category_id BIGINT GENERATED ALWAYS AS IDENTITY(INCREMENT BY 1 NO MAXVALUE NO MINVALUE CACHE 1) NOT NULL,
                name VARCHAR(25) NOT NULL,
                last_update TIMESTAMP WITH TIME ZONE DEFAULT current_timestamp() NOT NULL,
                CONSTRAINT PK_CATEGORY PRIMARY KEY (category_id)
            );
            COMMENT ON TABLE CATEGORY IS 'Category details table';
--rollback DROP TABLE CATEGORY;

--changeset Juan Haugaard:8
--comment: Create table Country
            CREATE TABLE COUNTRY (
            country_id BIGINT GENERATED ALWAYS AS IDENTITY(INCREMENT BY 1 NO MAXVALUE NO MINVALUE CACHE 1) NOT NULL,
            country VARCHAR(50) NOT NULL,
            last_update TIMESTAMP WITH TIME ZONE DEFAULT current_timestamp() ON UPDATE current_timestamp() NOT NULL,
            CONSTRAINT PK_COUNTRY PRIMARY KEY (country_id)
            );
            COMMENT ON TABLE COUNTRY IS 'Country details table';
--rollback DROP TABLE COUNTRY;

--changeset Juan Haugaard:9
--comment: Create table City
            CREATE TABLE CITY (
                city_id BIGINT GENERATED ALWAYS AS IDENTITY(INCREMENT BY 1 NO MAXVALUE NO MINVALUE CACHE 1) NOT NULL,
                city VARCHAR(50) NOT NULL,
                country_id BIGINT NOT NULL,
                last_update TIMESTAMP WITH TIME ZONE DEFAULT current_timestamp() ON UPDATE current_timestamp() NOT NULL,
                CONSTRAINT PK_CITY PRIMARY KEY (city_id),
                CONSTRAINT FK_CITY_COUNTRY FOREIGN KEY (country_id)
                    REFERENCES COUNTRY (country_id) ON DELETE    RESTRICT ON UPDATE CASCADE
            );
            COMMENT ON TABLE CITY IS 'City details table';
--rollback DROP TABLE CITY;

--changeset Juan Haugaard:10
--comment: Create table Address
            CREATE TABLE ADDRESS (
                address_id BIGINT GENERATED ALWAYS AS IDENTITY(INCREMENT BY 1 NO MAXVALUE NO MINVALUE CACHE 1) NOT NULL,
                address VARCHAR(50) NOT NULL,
                address2 VARCHAR(50) DEFAULT NULL,
                district VARCHAR(20) NOT NULL,
                city_id BIGINT NOT NULL,
                postal_code VARCHAR(10) DEFAULT NULL,
                phone VARCHAR(20) NOT NULL,
                last_update TIMESTAMP WITH TIME ZONE DEFAULT current_timestamp() ON UPDATE current_timestamp() NOT NULL,
                CONSTRAINT PK_ADDRESS PRIMARY KEY (address_id),
                CONSTRAINT FK_ADDRESS_CITY FOREIGN KEY (city_id)
                    REFERENCES CITY (city_id) ON DELETE RESTRICT ON UPDATE CASCADE
            );
            COMMENT ON TABLE ADDRESS IS 'Address details table';
--rollback DROP TABLE ADDRESS;

--changeset Juan Haugaard:11
--comment: Create table Language
            CREATE TABLE LANGUAGE (
                language_id BIGINT GENERATED ALWAYS AS IDENTITY(INCREMENT BY 1 NO MAXVALUE NO MINVALUE CACHE 1) NOT NULL,
                name VARCHAR(20) NOT NULL,
                last_update TIMESTAMP WITH TIME ZONE DEFAULT current_timestamp() ON UPDATE current_timestamp() NOT NULL,
                CONSTRAINT PK_LANGUAGE PRIMARY KEY (language_id)
            );
            COMMENT ON TABLE LANGUAGE IS 'Language details table';
--rollback DROP TABLE LANGUAGE;

--changeset Juan Haugaard:12
--comment: Create table ACTOR
            CREATE TABLE ACTOR (
                actor_id BIGINT GENERATED ALWAYS AS IDENTITY(INCREMENT BY 1 NO MAXVALUE NO MINVALUE CACHE 1) NOT NULL,
                first_name VARCHAR(45) NOT NULL,
                last_name VARCHAR(45) NOT NULL,
                last_update TIMESTAMP WITH TIME ZONE DEFAULT current_timestamp() ON UPDATE current_timestamp() NOT NULL,
                CONSTRAINT PK_ACTOR PRIMARY KEY (actor_id)
            );
            COMMENT ON TABLE ACTOR IS 'Actor details table';
--rollback DROP TABLE ACTOR;

--changeset Juan Haugaard:13
--comment: Create table Store
            CREATE TABLE STORE (
                store_id BIGINT GENERATED ALWAYS AS IDENTITY(INCREMENT BY 1 NO MAXVALUE NO MINVALUE CACHE 1) NOT NULL,
                manager_staff_id BIGINT NOT NULL,
                address_id BIGINT NOT NULL,
                last_update TIMESTAMP WITH TIME ZONE DEFAULT current_timestamp() ON UPDATE current_timestamp() NOT NULL,
                CONSTRAINT PK_STORE PRIMARY KEY (store_id),
                CONSTRAINT idx_unique_manager UNIQUE (manager_staff_id),
                CONSTRAINT fk_store_address FOREIGN KEY (address_id)
                    REFERENCES ADDRESS (address_id) ON DELETE RESTRICT ON UPDATE CASCADE
            );
            COMMENT ON TABLE STORE IS 'Store details table';
--rollback DROP TABLE STORE;

--changeset Juan Haugaard:14
--comment: Create table Staff
            CREATE TABLE STAFF (
                staff_id BIGINT GENERATED ALWAYS AS IDENTITY(INCREMENT BY 1 NO MAXVALUE NO MINVALUE CACHE 1) NOT NULL,
                first_name VARCHAR(45) NOT NULL,
                last_name VARCHAR(45) NOT NULL,
                address_id BIGINT NOT NULL,
                picture BLOB DEFAULT NULL,
                email VARCHAR(50) DEFAULT NULL,
                store_id BIGINT NOT NULL,
                active BOOLEAN NOT NULL DEFAULT TRUE,
                username VARCHAR(16) NOT NULL,
                password BINARY(40) DEFAULT NULL,
                last_update TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                CONSTRAINT PK_STAFF PRIMARY KEY (staff_id),
                CONSTRAINT fk_staff_store FOREIGN KEY (store_id)
                    REFERENCES STORE (store_id) ON DELETE RESTRICT ON UPDATE CASCADE,
                CONSTRAINT fk_staff_address FOREIGN KEY (address_id)
                    REFERENCES ADDRESS (address_id) ON DELETE RESTRICT ON UPDATE CASCADE
            );
            COMMENT ON TABLE STAFF IS 'Staff details table';
--rollback DROP TABLE STAFF;

--changeset Juan Haugaard:15
--comment: Alter table Store to add Foreign key to staff table
            ALTER TABLE STORE
            ADD CONSTRAINT fk_store_staff FOREIGN KEY (manager_staff_id)
                REFERENCES STAFF (staff_id) ON DELETE RESTRICT ON UPDATE CASCADE;
--rollback ALTER TABLE STORE
--rollback DROP CONSTRAINT fk_store_staff;

--changeset Juan Haugaard:16
--comment: Create table Customer
            CREATE TABLE CUSTOMER (
                customer_id BIGINT GENERATED ALWAYS AS IDENTITY(INCREMENT BY 1 NO MAXVALUE NO MINVALUE CACHE 1) NOT NULL,
                store_id BIGINT NOT NULL,
                first_name VARCHAR(45) NOT NULL,
                last_name VARCHAR(45) NOT NULL,
                email VARCHAR(50) DEFAULT NULL,
                address_id BIGINT NOT NULL,
                active BOOLEAN NOT NULL DEFAULT TRUE,
                create_date TIMESTAMP WITH TIME ZONE DEFAULT current_timestamp() NOT NULL,
                last_update TIMESTAMP WITH TIME ZONE DEFAULT current_timestamp() ON UPDATE current_timestamp() NOT NULL,
                CONSTRAINT PK_CUSTOMER PRIMARY KEY (customer_id),
                CONSTRAINT FK_CUSTOMER_ADDRESS FOREIGN KEY (address_id)
                    REFERENCES ADDRESS (address_id) ON DELETE RESTRICT ON UPDATE CASCADE,
                CONSTRAINT FK_CUSTOMER_STORE FOREIGN KEY (store_id)
                    REFERENCES STORE (store_id) ON DELETE RESTRICT ON UPDATE CASCADE
            );
            COMMENT ON TABLE CUSTOMER IS 'Customer details table';
            CREATE INDEX idx_last_name ON CUSTOMER (last_name);
--rollback DROP TABLE CUSTOMER;

--changeset Juan Haugaard:17
--comment: Create table Film
            CREATE TABLE FILM (
                film_id BIGINT GENERATED ALWAYS AS IDENTITY(INCREMENT BY 1 NO MAXVALUE NO MINVALUE CACHE 1) NOT NULL,
                title VARCHAR(255) NOT NULL,
                description TEXT DEFAULT NULL,
                release_year YEARVALUE DEFAULT NULL,
                language_id BIGINT NOT NULL,
                original_language_id BIGINT DEFAULT NULL,
                rental_duration TINYINT NOT NULL DEFAULT 3,
                rental_rate DECIMAL(4,2) NOT NULL DEFAULT 4.99,
                length SMALLINT DEFAULT NULL,
                replacement_cost DECIMAL(5,2) NOT NULL DEFAULT 19.99,
                barcode CHAR(20),
                reverse_barcode CHAR(20) GENERATED ALWAYS AS (REVERSE(barcode)),
                rating MPAA_RATING DEFAULT 'G',
                special_features SPECIAL_FEATURES DEFAULT NULL,
                last_update TIMESTAMP WITH TIME ZONE DEFAULT current_timestamp() ON UPDATE current_timestamp() NOT NULL,
                CONSTRAINT PK_FILM PRIMARY KEY (film_id),
                CONSTRAINT fk_film_language FOREIGN KEY (language_id)
                    REFERENCES LANGUAGE (language_id) ON DELETE RESTRICT ON UPDATE CASCADE,
                CONSTRAINT fk_film_language_original FOREIGN KEY (original_language_id)
                    REFERENCES LANGUAGE (language_id) ON DELETE RESTRICT ON UPDATE CASCADE
            );
            COMMENT ON TABLE FILM IS 'Film details table';
            CREATE INDEX idx_title ON FILM(title);
            CREATE INDEX idx_barcode ON FILM(barcode);
            CREATE INDEX idx_reverse_barcode ON FILM(reverse_barcode);
           -- ALTER TABLE FILM ALTER COLUMN barcode CHAR(20) GENERATED ALWAYS AS (LPAD(barcode ,12,'0'));
--rollback DROP TABLE FILM;

--changeset Juan Haugaard:18
--comment: Add insert trigger to table Film
            CREATE TRIGGER InsertFilmTrigger AFTER INSERT ON
                FILM FOR EACH ROW CALL 'com.tayrona.sakila.procedures.InsertFilmTrigger';
--rollback DROP TRIGGER InsertFilmTrigger;

--changeset Juan Haugaard:19
--comment: Add update trigger to table Film
            CREATE TRIGGER UpdateFilmTrigger AFTER UPDATE ON
                FILM FOR EACH ROW CALL 'com.tayrona.sakila.procedures.UpdateFilmTrigger';
--rollback DROP TRIGGER UpdateFilmTrigger;

--changeset Juan Haugaard:20
--comment: Add delete trigger to table Film
            CREATE TRIGGER DeleteFilmTrigger AFTER DELETE ON
                FILM FOR EACH ROW CALL 'com.tayrona.sakila.procedures.DeleteFilmTrigger';
--rollback DROP TRIGGER DeleteFilmTrigger;

--changeset Juan Haugaard:21
--comment: Create table film_text
            CREATE TABLE FILM_TEXT (
                film_id BIGINT NOT NULL,
                title VARCHAR(255) NOT NULL,
                description TEXT,
                CONSTRAINT PK_FILM_TEXT PRIMARY KEY (film_id)
            );
            COMMENT ON TABLE FILM_TEXT IS 'Film title and description table for fulltext search';
            CREATE INDEX idx_title_description ON FILM_TEXT(title, description);
--rollback DROP TABLE FILM_TEXT;

--changeset Juan Haugaard:22
--comment: Create table inventory
            CREATE TABLE INVENTORY (
                inventory_id BIGINT GENERATED ALWAYS AS IDENTITY(INCREMENT BY 1 NO MAXVALUE NO MINVALUE CACHE 1) NOT NULL,
                film_id BIGINT NOT NULL,
                store_id BIGINT NOT NULL,
                last_update TIMESTAMP WITH TIME ZONE DEFAULT current_timestamp() ON UPDATE current_timestamp() NOT NULL,
                CONSTRAINT PK_INVENTORY PRIMARY KEY (inventory_id),
                CONSTRAINT fk_inventory_store FOREIGN KEY (store_id)
                    REFERENCES STORE (store_id) ON DELETE RESTRICT ON UPDATE CASCADE,
                CONSTRAINT fk_inventory_film FOREIGN KEY (film_id)
                    REFERENCES FILM (film_id) ON DELETE RESTRICT ON UPDATE CASCADE
            );
            COMMENT ON TABLE INVENTORY IS 'Inventory details table';
            CREATE INDEX idx_store_id_film_id ON INVENTORY (store_id, film_id);
--rollback DROP TABLE INVENTORY;

--changeset Juan Haugaard:23
--comment: Create table Rental
            CREATE TABLE RENTAL (
                rental_id BIGINT GENERATED ALWAYS AS IDENTITY(INCREMENT BY 1 NO MAXVALUE NO MINVALUE CACHE 1) NOT NULL,
                rental_date DATETIME NOT NULL,
                inventory_id BIGINT NOT NULL,
                customer_id BIGINT NOT NULL,
                return_date DATETIME DEFAULT NULL,
                staff_id BIGINT NOT NULL,
                last_update TIMESTAMP WITH TIME ZONE DEFAULT current_timestamp() ON UPDATE current_timestamp() NOT NULL,
                CONSTRAINT PK_RENTAL PRIMARY KEY (rental_id),
                CONSTRAINT fk_rental_staff FOREIGN KEY (staff_id)
                    REFERENCES STAFF (staff_id) ON DELETE RESTRICT ON UPDATE CASCADE,
                CONSTRAINT fk_rental_inventory FOREIGN KEY (inventory_id)
                    REFERENCES INVENTORY (inventory_id) ON DELETE RESTRICT ON UPDATE CASCADE,
                CONSTRAINT fk_rental_customer FOREIGN KEY (customer_id)
                    REFERENCES CUSTOMER (customer_id) ON DELETE RESTRICT ON UPDATE CASCADE
            );
            COMMENT ON TABLE RENTAL IS 'Rental details table';
            CREATE UNIQUE INDEX idx_rental_date_inventory_id_customer_id
                ON RENTAL (rental_date, inventory_id, customer_id);
--rollback DROP TABLE RENTAL;

--changeset Juan Haugaard:24
--comment: Create table Payment
            CREATE TABLE PAYMENT (
                payment_id BIGINT GENERATED ALWAYS AS IDENTITY(INCREMENT BY 1 NO MAXVALUE NO MINVALUE CACHE 1) NOT NULL,
                customer_id BIGINT NOT NULL,
                staff_id BIGINT NOT NULL,
                rental_id BIGINT DEFAULT NULL,
                amount DECIMAL(5,2) NOT NULL,
                payment_date DATETIME NOT NULL,
                last_update TIMESTAMP WITH TIME ZONE DEFAULT current_timestamp() ON UPDATE current_timestamp() NOT NULL,
                CONSTRAINT PK_PAYMENT PRIMARY KEY (payment_id),
                CONSTRAINT fk_payment_rental FOREIGN KEY (rental_id)
                    REFERENCES RENTAL (rental_id) ON DELETE SET NULL ON UPDATE CASCADE,
                CONSTRAINT fk_payment_customer FOREIGN KEY (customer_id)
                    REFERENCES CUSTOMER (customer_id) ON DELETE RESTRICT ON UPDATE CASCADE,
                CONSTRAINT fk_payment_staff FOREIGN KEY (staff_id)
                    REFERENCES STAFF (staff_id) ON DELETE RESTRICT ON UPDATE CASCADE
            );
            COMMENT ON TABLE PAYMENT IS 'Payment details table';
--rollback DROP TABLE PAYMENT;

--changeset Juan Haugaard:25
--comment: Create table Film Actor cross-reference
            CREATE TABLE FILM_ACTOR (
                actor_id BIGINT NOT NULL,
                film_id BIGINT NOT NULL,
                last_update TIMESTAMP WITH TIME ZONE DEFAULT current_timestamp() ON UPDATE current_timestamp() NOT NULL,
                CONSTRAINT PK_FILM_ACTOR PRIMARY KEY (actor_id, film_id),
                CONSTRAINT fk_film_actor_actor FOREIGN KEY (actor_id)
                    REFERENCES ACTOR (actor_id) ON DELETE RESTRICT ON UPDATE CASCADE,
                CONSTRAINT fk_film_actor_film FOREIGN KEY (film_id)
                    REFERENCES FILM (film_id) ON DELETE RESTRICT ON UPDATE CASCADE
            );
            COMMENT ON TABLE FILM_ACTOR IS 'Film Actor cross-reference table';
--rollback DROP TABLE FILM_ACTOR;

--changeset Juan Haugaard:26
--comment: Create table Film Category cross-reference
            CREATE TABLE FILM_CATEGORY (
                film_id BIGINT NOT NULL,
                category_id BIGINT NOT NULL,
                last_update TIMESTAMP WITH TIME ZONE DEFAULT current_timestamp() ON UPDATE current_timestamp() NOT NULL,
                CONSTRAINT PK_FILM_CATEGORY PRIMARY KEY (film_id, category_id),
                CONSTRAINT fk_film_category_film FOREIGN KEY (film_id)
                    REFERENCES FILM (film_id) ON DELETE RESTRICT ON UPDATE CASCADE,
                CONSTRAINT fk_film_category_category FOREIGN KEY (category_id)
                    REFERENCES CATEGORY (category_id) ON DELETE RESTRICT ON UPDATE CASCADE
            );
            COMMENT ON TABLE FILM_CATEGORY IS 'Film Category cross-reference table';
--rollback DROP TABLE FILM_CATEGORY;

--changeset Juan Haugaard:27
--comment: Define function enum_to_ordinal
            CREATE ALIAS ENUM_TO_ORDINAL DETERMINISTIC FOR 'com.tayrona.sakila.procedures.EnumUtils.valueToOrdinal';
--rollback DROP ALIAS ENUM_TO_ORDINAL;

--changeset Juan Haugaard:28
--comment: Define function mpaa_rating_to_ordinal
            CREATE ALIAS MPAA_RATING_TO_ORDINAL DETERMINISTIC FOR 'com.tayrona.sakila.procedures.EnumUtils.mpaaRatingToOrdinal';
--rollback DROP ALIAS MPAA_RATING_TO_ORDINAL;

--changeset Juan Haugaard:29
--comment: Define function special_features_to_ordinal
            CREATE ALIAS SPECIAL_FEATURES_TO_ORDINAL DETERMINISTIC FOR 'com.tayrona.sakila.procedures.EnumUtils.specialFeaturesToOrdinal';
--rollback DROP ALIAS SPECIAL_FEATURES_TO_ORDINAL;

--changeset Juan Haugaard:30
--comment: Create table TEST_TABLE
            create table TEST_TABLE (
                id BIGINT GENERATED ALWAYS AS IDENTITY(INCREMENT BY 1 NO MAXVALUE NO MINVALUE CACHE 1) NOT NULL,
                features SPECIAL_FEATURES default NULL,
                CONSTRAINT PK_TESTTABLE PRIMARY KEY (id)
            );
            COMMENT ON TABLE TEST_TABLE IS 'test table';
--rollback DROP TABLE TEST_TABLE;

--changeset Juan Haugaard:31
--comment: Add trigger to table TEST_TABLE
            CREATE TRIGGER TestTrigger AFTER INSERT,UPDATE,DELETE ON
                TEST_TABLE FOR EACH ROW CALL 'com.tayrona.sakila.procedures.TestTrigger';
--rollback DROP TRIGGER TestTrigger;

--changeset Juan Haugaard:32
--comment: TEST_TABLE initial data
            INSERT INTO TEST_TABLE (FEATURES) VALUES(ARRAY['Commentaries', 'Deleted Scenes']);
            INSERT INTO TEST_TABLE (FEATURES) VALUES(ARRAY['Commentaries', 'Trailers']);
            INSERT INTO TEST_TABLE (FEATURES) VALUES(ARRAY['Commentaries']);
            INSERT INTO TEST_TABLE (FEATURES) VALUES(ARRAY['Trailers']);
            INSERT INTO TEST_TABLE (FEATURES) VALUES(ARRAY['Deleted Scenes']);
            INSERT INTO TEST_TABLE (FEATURES) VALUES(ARRAY['Behind the Scenes']);
            UPDATE TEST_TABLE SET FEATURES = ARRAY_APPEND(FEATURES, 'Deleted Scenes') WHERE ID=2;
            UPDATE TEST_TABLE SET FEATURES = ARRAY_APPEND(FEATURES, 'Behind the Scenes') WHERE ID=2;
--rollback TRUNCATE TABLE TEST_TABLE;
