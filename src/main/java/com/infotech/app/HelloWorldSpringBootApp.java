package com.infotech.app;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath*:applicationContext.xml")
public class HelloWorldSpringBootApp {

	public static void main(String[] args) {
		SpringApplication.run(HelloWorldSpringBootApp.class, args);
	}
}
