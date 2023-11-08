package com.theocean.fundering;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FunderingApplication {

    public static void main(final String[] args) {
        SpringApplication.run(FunderingApplication.class, args);
    }

}
