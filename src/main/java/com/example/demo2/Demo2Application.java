package com.example.demo2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@EnableAutoConfiguration
@EnableSwagger2
public class Demo2Application {

	public static void main(String[] args) {
		SpringApplication.run(Demo2Application.class, args);
	}

}
