package com.spl.splserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class SplServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SplServerApplication.class, args);
	}

}
