package com.docm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
@MapperScan("com.docm.dao")
@Component
public class DocmApplication {

	public static void main(String[] args) {
		SpringApplication.run(DocmApplication.class, args);
	}

}
