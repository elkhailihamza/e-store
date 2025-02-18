package org.project.estoreeurikaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EStoreEurikaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EStoreEurikaServerApplication.class, args);
	}

}
