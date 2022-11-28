package org.tayrona.sakila.data.generators;

import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.springframework.stereotype.Component;
import org.tayrona.sakila.data.Tables;
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
            setOfStates.add(states[RandomUtils.nextInt(0, states.length)]);
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
                .limit(1)
                .fetch()
                .stream()
                .findFirst();
    }

    public void persistStates(Collection<String> states) {
        // we use countries as states for persistence, so as not to change sakila too much from the original
        Set<String> existingStateSet = existingStates().intoSet(Country.COUNTRY.COUNTRY_);
        int count = 0;
        for (String state : states) {
            if (!existingStateSet.contains(state)) {
                CountryRecord countryRecord = dslContext.newRecord(Country.COUNTRY);
                countryRecord.setCountry(state);
                countryRecord.setCountryAbbreviation(statesMap.get(state));
                countryRecord.store();
                existingStateSet.add(countryRecord.getCountry());
                count += 1;
            }
        }
        log.info("Persisted {} states in table Country", count);
    }

    public List<CityRecord> generateOneCityPerState() {
        Result<CityRecord> existingCities = existingCities();
        Set<String> cityNames = existingCities.intoSet(City.CITY.CITY_);
        Map<Long, CityRecord> cityMap = existingCities.intoMap(City.CITY.COUNTRY_ID, City.CITY.getRecordType());
        Result<CountryRecord> existingStates = existingStates();
        for (CountryRecord state : existingStates) {
            if (!cityMap.containsKey(state.getCountryId())) {
                CityRecord cityRecord = dslContext.newRecord(City.CITY);
                cityRecord.setCity(uniquesCityName(cityNames));
                cityRecord.setCountryId(state.getCountryId());
                cityMap.put(cityRecord.getCountryId(), cityRecord);
            }
        }
        return new ArrayList<>(cityMap.values());
    }

    private String uniquesCityName(Set<String> cityNames) {
        String cityName;
        int count=0;
        // remove duplicates
        do {
            // remove compound city names like 'East Wilfordfurt'
            do {
                cityName = faker.address().cityName();
                count++;
            } while (cityName.contains(" ") && (count < 100));
        } while (cityNames.contains(cityName) && (count < 100));
        if (count>1) {
            log.warn("Tried {} times to get unique city: {}", count, cityName);
        }
        return cityName;
    }

    public Result<CityRecord> existingCities() {
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
        Set<String> existingCities = existingCities().intoSet(City.CITY.CITY_);
        for (CityRecord city : cities) {
            if (!existingCities.contains(city.getCity())) {
                city.store();
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
        AddressRecord addressRecord = dslContext.newRecord(Tables.ADDRESS);
        Pair<String, String> zipcodeAndCounty = generateZipcodeAndCountyFromStateAbbreviation(countryRecord.getCountryAbbreviation());
        String zipCode = zipcodeAndCounty.getLeft();
        String county = zipcodeAndCounty.getRight();
        String phone = faker.numerify("(###) ###-####");
        addressRecord.setCityId(cityRecord.getCityId());
        addressRecord.setAddress(faker.address().streetAddress());
        addressRecord.setAddress2(faker.address().secondaryAddress());
        addressRecord.setPostalCode(zipCode);
        addressRecord.setDistrict(county);
        addressRecord.setPhone(phone);
        return addressRecord;
    }

    private Pair<String, String> generateZipcodeAndCountyFromStateAbbreviation(String stateAbbreviation) {
        String zipCode = faker.address().zipCodeByState(stateAbbreviation);
        int limit = 50;
        int count = limit;
        while (--count > 0) {
            // about 3 times in 50 countyByZipCode() fails, so here we retry
            // failed countyByZipCode() calls to prevent the whole data
            // generation process from failing before completion
            try {
                String county = faker.address().countyByZipCode(zipCode);
                return new ImmutablePair<>(zipCode, county);
            } catch (RuntimeException rex) {
                log.error("ERROR: try {}, message: {}", limit-count, rex.getMessage());
            }
            zipCode = faker.address().zipCodeByState(stateAbbreviation);
        }
        return new ImmutablePair<>(zipCode, null);
    }

    public AddressRecord persistNewAddressForCity(long cityId) {
        AddressRecord addressRecord = generateAddressForCity(cityId);
        addressRecord.store();
        return addressRecord;
    }

    public AddressRecord persistNewAddressForCity(CityRecord cityRecord) {
        AddressRecord addressRecord = generateAddressForCity(cityRecord);
        addressRecord.store();
        return addressRecord;
    }

    static String[] states = {
            "Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado", "Connecticut", "Delaware", "Florida", "Georgia", "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa", "Kansas", "Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota", "Mississippi", "Missouri", "Montana", "Nebraska", "Nevada", "New Hampshire", "New Jersey", "New Mexico", "New York", "North Carolina", "North Dakota", "Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island", "South Carolina", "South Dakota", "Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington", "West Virginia", "Wisconsin", "Wyoming"
    };
    static String[] stateAbbreviations = {
            "AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VA", "WA", "WV", "WI", "WY"
    };
}
