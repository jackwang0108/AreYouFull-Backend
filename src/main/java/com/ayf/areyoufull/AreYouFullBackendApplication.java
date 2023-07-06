package com.ayf.areyoufull;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@MapperScan("com.ayf.areyoufull.mapper")
@ServletComponentScan
public class AreYouFullBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(AreYouFullBackendApplication.class, args);
	}

}
