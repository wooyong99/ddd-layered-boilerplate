package com.example.dddlayerdboilerplate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class DddLayerdBoilerplateApplication {

    public static void main(String[] args) {
        SpringApplication.run(DddLayerdBoilerplateApplication.class, args);
    }

}
