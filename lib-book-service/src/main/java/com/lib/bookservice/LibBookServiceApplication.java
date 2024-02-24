package com.lib.bookservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class LibBookServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibBookServiceApplication.class, args);
	}

}
