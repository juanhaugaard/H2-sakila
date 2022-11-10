package org.tayrona.sakila.data;

import com.github.javafaker.Faker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Locale;

@SpringBootApplication
@EnableTransactionManagement
public class GeneratorApplication {

	public static void main(String[] args) {
		System.setProperty("org.jooq.no-logo", "true");
		SpringApplication.run(GeneratorApplication.class, args);
	}

	@Bean
	public Faker makeFaker() {
		return new Faker(Locale.US);
	}
}
