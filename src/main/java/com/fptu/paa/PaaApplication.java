package com.fptu.paa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PaaApplication {
	static {
		System.setProperty("org.hyperledger.fabric.sdk.service_discovery.as_localhost", "true");
	}
	public static void main(String[] args) {
		SpringApplication.run(PaaApplication.class, args);
	}

}
