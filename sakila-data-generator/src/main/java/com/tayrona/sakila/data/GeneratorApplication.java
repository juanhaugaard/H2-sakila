package com.tayrona.sakila.data;

import com.github.javafaker.Faker;
import com.tayrona.sakila.data.generators.CityAndState;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Locale;

@SpringBootApplication
@EnableTransactionManagement
@Slf4j
public class GeneratorApplication implements CommandLineRunner {

	@Autowired
	private DSLContext create;

	public static void main(String[] args) {
		System.setProperty("org.jooq.no-logo", "true");
		SpringApplication.run(GeneratorApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Faker faker = new Faker(Locale.US);
		CityAndState.persistStates(create, CityAndState.generateStates(50, faker));
		CityAndState.persistCities(create, CityAndState.generateACityPerState(create, faker));
	}
}
