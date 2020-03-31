package io.saga.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("io.saga")
public class StorageApplication {

    public static void main(String[] args) {
        SpringApplication.run(StorageApplication.class, args);
    }

}