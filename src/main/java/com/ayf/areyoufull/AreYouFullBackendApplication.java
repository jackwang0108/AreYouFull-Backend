package com.ayf.areyoufull;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ayf.areyoufull.mapper")
public class AreYouFullBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(AreYouFullBackendApplication.class, args);
	}

}
