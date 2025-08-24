package com.asem.orderservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {
				"com.asem.orderservice",
				"com.example.common",
		})
@EntityScan(basePackages = {
		"com.asem.orderservice.Entity",
		"com.example.common"
})
@EnableJpaRepositories(basePackages = {"com.asem.orderservice.Repository", "com.example.common"})
@EnableDiscoveryClient
public class OrderserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderserviceApplication.class, args);
	}

}
