package com.bankofbugbusters.apigate_way;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ApigateWayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApigateWayApplication.class, args);
	}

}
