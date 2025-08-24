package com.asem.bookingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {
		"com.example.common",
		"com.asem.bookingservice.Repository"
})
@ComponentScan(basePackages = {
		"com.asem.bookingservice",
		"com.example.common"
})
@EntityScan(basePackages = {
		"com.asem.bookingservice.Entity",
		"com.example.common"
})
@EnableScheduling
@EnableDiscoveryClient
public class BookingserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookingserviceApplication.class, args);
	}

}
