package com.amogh.affirm.ms.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
public class LoanGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(LoanGatewayApplication.class, args);
    }
}
