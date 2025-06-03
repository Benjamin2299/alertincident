package com.alertincident.incident_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient  // Remplace @EnableEurekaClient dans les nouvelles versions
public class IncidentServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(IncidentServiceApplication.class, args);
    }
}
