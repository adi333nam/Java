package com.cognizant.authorization;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableEurekaClient
@SpringBootApplication
@EnableZuulProxy
@EnableWebSecurity
public class AuthorizationMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthorizationMicroserviceApplication.class, args);
	}

}
