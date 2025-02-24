package com.miniproject.wtlmini;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.miniproject.wtlmini.repository")
public class WtlMiniApplication {

    public static void main(String[] args) {
        SpringApplication.run(WtlMiniApplication.class, args);
    }

}
