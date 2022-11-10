package org.tayrona.sakila.data.generators;

import com.github.javafaker.Faker;
import org.tayrona.sakila.data.tables.City;
import org.tayrona.sakila.data.tables.Country;
import org.tayrona.sakila.data.tables.records.CityRecord;
import org.tayrona.sakila.data.tables.records.CountryRecord;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Component
public class CityAndState {

    private final DSLContext dslContext;

    private final Faker faker;

    public CityAndState(DSLContext dslContext, Faker faker) {
        this.dslContext = dslContext;
        this.faker = faker;
    }

    public Set<String> generateStates(int count) {
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

    public void persistStates(Collection<String> states) {
        Result<CountryRecord> existingStates = dslContext.selectFrom(Country.COUNTRY).fetch();
        int count = 0;
        for (String state : states) {
            if (existingStates.stream().map(CountryRecord::getCountry).noneMatch(state::equals)) {
                dslContext.insertInto(Country.COUNTRY)
                        .columns(Country.COUNTRY.COUNTRY_)
                        .values(state)
                        .execute();
                count += 1;
            }
        }
        log.info("Persisted {} states in table Country", count);
    }
    public List<CityRecord> generateACityPerState() {
        List<CityRecord> cities = new ArrayList<>();
        Result<CountryRecord> existingStates = dslContext.selectFrom(Country.COUNTRY).fetch();
        for (CountryRecord state : existingStates) {
            Result<CityRecord> cityRecords = dslContext.selectFrom(City.CITY)
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
    public void persistCities(List<CityRecord> cities) {
        Result<CityRecord> cityRecords = dslContext.selectFrom(City.CITY).fetch();
        int count = 0;
        for (CityRecord city : cities) {
            boolean found = cityRecords.stream()
                    .map(CityRecord::getCityId)
                    .anyMatch(it->it.equals(city.getCountryId()));
            if (!found) {
                dslContext.insertInto(City.CITY)
                        .columns(City.CITY.CITY_, City.CITY.COUNTRY_ID).values(city.getCity(), city.getCountryId())
                        .execute();
                count += 1;
            }
        }
        log.info("Persisted {} cities in table City", count);
    }
}
