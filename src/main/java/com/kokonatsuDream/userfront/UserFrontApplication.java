package com.kokonatsuDream.userfront;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan( basePackages = {"domain"} )
public class UserFrontApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserFrontApplication.class, args);
	}
}
