package com.example.rediscachetest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*
* Access H2 database console - http://localhost:8080/h2
* https://howtodoinjava.com/spring-boot2/h2-database-example/
* https://www.baeldung.com/spring-boot-h2-database
 */
@SpringBootApplication
public class RedisCacheTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedisCacheTestApplication.class, args);
	}

}
