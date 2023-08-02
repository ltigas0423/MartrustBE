package com.example.MarTrustBE;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@SpringBootApplication
@EnableSwagger2
@EnableFeignClients(basePackageClasses = {})
public class MarTrustBeApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarTrustBeApplication.class, args);
	}

}
