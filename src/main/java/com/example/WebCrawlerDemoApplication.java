package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@EnableAutoConfiguration(exclude=ErrorMvcAutoConfiguration.class)
public class WebCrawlerDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebCrawlerDemoApplication.class, args);
	}
}
