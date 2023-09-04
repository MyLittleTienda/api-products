package com.mlt.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ApiProductsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiProductsApplication.class, args);
    }

}
