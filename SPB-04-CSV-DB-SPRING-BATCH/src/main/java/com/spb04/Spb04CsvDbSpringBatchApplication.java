package com.spb04;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class Spb04CsvDbSpringBatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(Spb04CsvDbSpringBatchApplication.class, args);
	}

}
