package com.alertincident.troncon_routier_service;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class TronconRoutierServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(TronconRoutierServiceApplication.class, args);
    }
}

