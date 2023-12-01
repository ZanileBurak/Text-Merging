package com.yazlab.TextMerging;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class TextMergingApplication {

	public static void main(String[] args) {
		SpringApplication.run(TextMergingApplication.class, args);
	}

}
