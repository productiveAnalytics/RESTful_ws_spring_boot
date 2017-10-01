package com.productiveanalytics.restful_ws_spring_boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.hateoas.config.EnableEntityLinks;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;

/**
 * Entry for Spring Boot application
 * @author LChawathe
 *
 */

@SpringBootApplication
@EnableEntityLinks
@EnableHypermediaSupport(type = HypermediaType.HAL)
public class RESTfulSpringBootApp {
	public static void main (String[] args) {
		SpringApplication.run(RESTfulSpringBootApp.class, args);
	}
}
