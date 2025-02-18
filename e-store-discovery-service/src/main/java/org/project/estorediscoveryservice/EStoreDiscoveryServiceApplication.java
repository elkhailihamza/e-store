package org.project.estorediscoveryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EStoreDiscoveryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EStoreDiscoveryServiceApplication.class, args);
    }

}
