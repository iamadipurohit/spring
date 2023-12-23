package com.example.ji;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={
"com.example.something", "com.example.application"})
public class JiApplication {

	public static void main(String[] args) {
		SpringApplication.run(JiApplication.class, args);
	}

}
