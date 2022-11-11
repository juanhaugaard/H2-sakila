package org.tayrona.sakila.data;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.tayrona.sakila.data.generators.AddressGenerator;
import org.tayrona.sakila.data.generators.StoreGenerator;

@Slf4j
@Component
public class RunGenerator implements CommandLineRunner {
    private final AddressGenerator addressGenerator;
    private final StoreGenerator storeGenerator;

    public RunGenerator(AddressGenerator addressGenerator, StoreGenerator storeGenerator) {
        this.addressGenerator = addressGenerator;
        this.storeGenerator = storeGenerator;
    }

    @Override
    public void run(String... args) {
        addressGenerator.persistStates(addressGenerator.generateStates(50));
        addressGenerator.persistCities(addressGenerator.generateACityPerState());
        storeGenerator.persistOneStorePerCity(5);
    }
}
