package com.amogh.affirm.ms.facility;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class FacilityServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(FacilityServiceApplication.class, args);
    }
}
