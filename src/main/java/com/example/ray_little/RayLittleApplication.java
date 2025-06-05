package com.example.ray_little;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.ray_little.repository")
public class RayLittleApplication {

	public static void main(String[] args) {
		SpringApplication.run(RayLittleApplication.class, args);
	}

}
