package org.tayrona.sakila.data.generators;

import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.jooq.DSLContext;
import org.jooq.Record1;
import org.jooq.Result;
import org.springframework.stereotype.Component;
import org.tayrona.sakila.data.tables.Address;
import org.tayrona.sakila.data.tables.City;
import org.tayrona.sakila.data.tables.Country;
import org.tayrona.sakila.data.tables.records.AddressRecord;
import org.tayrona.sakila.data.tables.records.CityRecord;
import org.tayrona.sakila.data.tables.records.CountryRecord;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Component
public class AddressGenerator {

    private final DSLContext dslContext;

    private final Faker faker;

    private Map<String, String> statesMap;

    public AddressGenerator(DSLContext dslContext, Faker faker) {
        this.dslContext = dslContext;
        this.faker = faker;
        initialize();
    }

    private void initialize() {
        statesMap = new HashMap<>(states.length);
        for (int i = 0; i < states.length; i++) {
            statesMap.put(states[i], stateAbbreviations[i]);
        }
    }

    public Set<String> generateStates(int count) {
        Set<String> setOfStates = new HashSet<>();
        int limit = count * 100;
        int iteration = 0;
        while (setOfStates.size() < count) {
            setOfStates.add(states[RandomUtils.nextInt(0,states.length)]);
            if (++iteration >= limit) {
                break;
            }
        }
        log.info("States generated: {}, iterations:{}", setOfStates.size(), iteration);
        return setOfStates;
    }

    public Result<CountryRecord> existingStates() {
        return dslContext
                .selectFrom(Country.COUNTRY)
                .fetch();
    }

    public Optional<CountryRecord> getCountryById(long id) {
        return dslContext
                .selectFrom(Country.COUNTRY)
                .where(Country.COUNTRY.COUNTRY_ID.eq(id))
                .fetch()
                .stream()
                .findFirst();
    }

    public void persistStates(Collection<String> states) {
        // we use countries as states for persistence, so as not to change sakila too much from the original
        List<CountryRecord> existingStates = existingStates();
        int count = 0;
        for (String state : states) {
            if (existingStates.stream().map(CountryRecord::getCountry).noneMatch(state::equals)) {
                dslContext
                        .insertInto(Country.COUNTRY)
                        .columns(Country.COUNTRY.COUNTRY_, Country.COUNTRY.COUNTRY_ABBREVIATION)
                        .values(state, statesMap.get(state))
                        .execute();
                count += 1;
            }
        }
        log.info("Persisted {} states in table Country", count);
    }
    public List<CityRecord> generateACityPerState() {
        List<CityRecord> cities = new ArrayList<>();
        List<CountryRecord> existingStates = existingStates();
        for (CountryRecord state : existingStates) {
            Result<CityRecord> cityRecords = dslContext
                    .selectFrom(City.CITY)
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

    public List<CityRecord> existingCities() {
        return dslContext
                .selectFrom(City.CITY)
                .fetch();
    }

    public Optional<CityRecord> getCityById(long cityId) {
        return dslContext
                .selectFrom(City.CITY)
                .where(City.CITY.CITY_ID.eq(cityId))
                .fetch()
                .stream()
                .findFirst();
    }
    public void persistCities(List<CityRecord> cities) {
        int count = 0;
        List<CityRecord> existingCities = existingCities();
        for (CityRecord city : cities) {
            boolean found = existingCities.stream()
                    .map(CityRecord::getCityId)
                    .anyMatch(it->it.equals(city.getCountryId()));
            if (!found) {
                dslContext
                        .insertInto(City.CITY)
                        .columns(City.CITY.CITY_, City.CITY.COUNTRY_ID).values(city.getCity(), city.getCountryId())
                        .execute();
                count += 1;
            }
        }
        log.info("Persisted {} cities in table City", count);
    }
    public AddressRecord generateAddressForCity(long cityId) {
        CityRecord cityRecord = getCityById(cityId).orElseThrow();
        return generateAddressForCity(cityRecord);
    }
    public AddressRecord generateAddressForCity(CityRecord cityRecord) {
        if (null == cityRecord) {
            throw new IllegalArgumentException("cityRecord is required");
        }
        CountryRecord countryRecord = getCountryById(cityRecord.getCountryId()).orElseThrow();
        AddressRecord addressRecord = new AddressRecord();
        String zipCode = faker.address().zipCodeByState(countryRecord.getCountryAbbreviation());
        String phone = faker.numerify("(###) ###-####");
        try {
            String county = faker.address().countyByZipCode(zipCode);
            addressRecord.setDistrict(county);
        } catch (RuntimeException rex) {
            log.error("{}", rex.getMessage());
        }
        addressRecord.setCityId(cityRecord.getCityId());
        addressRecord.setAddress(faker.address().streetAddress());
        addressRecord.setAddress2(faker.address().secondaryAddress());
        addressRecord.setPostalCode(zipCode);
        addressRecord.setPhone(phone);
        return addressRecord;
    }

    public AddressRecord persistNewAddressForCity(long cityId) {
        AddressRecord addressRecord = generateAddressForCity(cityId);
        return persistNewAddress(addressRecord);
    }

    public AddressRecord persistNewAddressForCity(CityRecord cityRecord) {
        AddressRecord addressRecord = generateAddressForCity(cityRecord);
        return persistNewAddress(addressRecord);
    }

    public AddressRecord persistNewAddress(AddressRecord addressRecord) {
        Result<Record1<Long>> addressId = dslContext
                .insertInto(Address.ADDRESS)
                .set(addressRecord)
                .returningResult(Address.ADDRESS.ADDRESS_ID)
                .fetch();
        if (addressId.isNotEmpty()) {
            addressRecord.setAddressId(addressId.getValue(0, Address.ADDRESS.ADDRESS_ID));
        }
        addressRecord.attach(dslContext.configuration());
        return addressRecord;
    }

    static String[] states = {
            "Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado", "Connecticut", "Delaware", "Florida", "Georgia", "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa", "Kansas", "Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota", "Mississippi", "Missouri", "Montana", "Nebraska", "Nevada", "New Hampshire", "New Jersey", "New Mexico", "New York", "North Carolina", "North Dakota", "Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island", "South Carolina", "South Dakota", "Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington", "West Virginia", "Wisconsin", "Wyoming"
    };
    static String[] stateAbbreviations = {
            "AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VA", "WA", "WV", "WI", "WY"
    };
}
