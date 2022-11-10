package org.tayrona.sakila.data;

import org.tayrona.sakila.data.generators.CityAndState;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RunGenerator implements CommandLineRunner {

    private final CityAndState cityAndState;

    public RunGenerator(CityAndState cityAndState) {
        this.cityAndState = cityAndState;
    }

    @Override
    public void run(String... args) {
        cityAndState.persistStates(cityAndState.generateStates(50));
        cityAndState.persistCities(cityAndState.generateACityPerState());
    }
}
