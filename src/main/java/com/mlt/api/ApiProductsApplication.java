package com.mlt.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class ApiProductsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiProductsApplication.class, args);
    }

}
