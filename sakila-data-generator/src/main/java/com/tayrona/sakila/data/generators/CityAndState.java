package com.tayrona.sakila.data.generators;

import com.github.javafaker.Faker;

import com.tayrona.sakila.data.generated.tables.City;
import com.tayrona.sakila.data.generated.tables.Country;
import com.tayrona.sakila.data.generated.tables.records.CityRecord;
import com.tayrona.sakila.data.generated.tables.records.CountryRecord;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CityAndState {
    private static final Logger log = LoggerFactory.getLogger(CityAndState.class);

    private CityAndState() {
        //disable instantiation
    }

    public static Set<String> generateStates(int count, Faker faker) {
        Set<String> states = new HashSet<>();
        int limit = count * 100;
        int iteration = 0;
        while (states.size() < count) {
            states.add(faker.address().state());
            if (++iteration >= limit) {
                break;
            }
        }
        log.info("States generated: {}, iterations:{}", states.size(), iteration);
        return states;
    }

    public static void persistStates(DSLContext create, Collection<String> states) {
        Result<CountryRecord> existingStates = create.selectFrom(Country.COUNTRY).fetch();
        int count = 0;
        for (String state : states) {
            if (existingStates.stream().map(CountryRecord::getCountry).noneMatch(state::equals)) {
                create.insertInto(Country.COUNTRY)
                        .columns(Country.COUNTRY.COUNTRY_)
                        .values(state)
                        .execute();
                count += 1;
            }
        }
        log.info("Persisted {} states in table Country", count);
    }
    public static List<CityRecord> generateACityPerState(DSLContext create, Faker faker) {
        List<CityRecord> cities = new ArrayList<>();
        Result<CountryRecord> existingStates = create.selectFrom(Country.COUNTRY).fetch();
        for (CountryRecord state : existingStates) {
            Result<CityRecord> cityRecords = create.selectFrom(City.CITY)
                    .where(City.CITY.COUNTRY_ID.eq(state.getCountryId()))
                    .fetch();
            if (cityRecords.isEmpty()) {
                CityRecord cityRecord = new CityRecord();
                cityRecord.setCity(faker.address().cityName());
                cityRecord.setCountryId(state.getCountryId());
                cities.add(cityRecord);
            }
        }
        return cities;
    }
    public static void persistCities(DSLContext create, List<CityRecord> cities) {
        Result<CityRecord> cityRecords = create.selectFrom(City.CITY).fetch();
        int count = 0;
        for (CityRecord city : cities) {
            boolean found = cityRecords.stream()
                    .map(CityRecord::getCityId)
                    .anyMatch(it->it.equals(city.getCountryId()));
            if (!found) {
                create.insertInto(City.CITY)
                        .columns(City.CITY.CITY_, City.CITY.COUNTRY_ID).values(city.getCity(), city.getCountryId())
                        .execute();
                count += 1;
            }
        }
        log.info("Persisted {} cities in table City", count);
    }
}
