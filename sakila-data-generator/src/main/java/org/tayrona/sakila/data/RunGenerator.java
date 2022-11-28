package org.tayrona.sakila.data;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.tayrona.sakila.data.generators.AddressGenerator;
import org.tayrona.sakila.data.generators.FilmGenerator;
import org.tayrona.sakila.data.generators.StoreGenerator;

import java.io.IOException;

@Slf4j
@Component
public class RunGenerator implements CommandLineRunner {
    private final AddressGenerator addressGenerator;
    private final StoreGenerator storeGenerator;
    private final FilmGenerator filmGenerator;

    public RunGenerator(AddressGenerator addressGenerator, StoreGenerator storeGenerator, FilmGenerator filmGenerator) {
        this.addressGenerator = addressGenerator;
        this.storeGenerator = storeGenerator;
        this.filmGenerator = filmGenerator;
    }

    @Override
    public void run(String... args) throws IOException {
        addressGenerator.persistStates(addressGenerator.generateStates(50));
        addressGenerator.persistCities(addressGenerator.generateOneCityPerState());
        storeGenerator.persistOneStorePerCity(5);
        long count;
        count = filmGenerator.populateActors();
        log.info("{} Actors where populated", count);
        count = filmGenerator.populateFilms();
        log.info("{} Films where populated", count);
        count = filmGenerator.refreshActorsForAllFilms();
        log.info("{} Films had their actors refreshed", count);
        count = filmGenerator.refreshCategoriesForAllFilms();
        log.info("{} Films had their categories refreshed", count);
    }
}
