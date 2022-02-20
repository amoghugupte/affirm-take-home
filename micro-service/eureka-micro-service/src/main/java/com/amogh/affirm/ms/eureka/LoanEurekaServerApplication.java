package com.amogh.affirm.ms.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class LoanEurekaServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(LoanEurekaServerApplication.class, args);
    }
}
