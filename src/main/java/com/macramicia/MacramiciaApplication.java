package com.macramicia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class MacramiciaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MacramiciaApplication.class, args);
	}
}
