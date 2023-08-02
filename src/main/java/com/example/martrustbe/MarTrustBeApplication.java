package com.example.martrustbe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MarTrustBeApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarTrustBeApplication.class, args);
	}

}
