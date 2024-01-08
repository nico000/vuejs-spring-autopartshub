package org.example.services_autoparts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

@SpringBootApplication
public class ServicesAutopartsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicesAutopartsApplication.class, args);
	}

}
