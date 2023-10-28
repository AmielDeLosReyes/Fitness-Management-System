package com.gs.grit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class GritStrengthApplication {

	public static void main(String[] args) {
		SpringApplication.run(GritStrengthApplication.class, args);
	}

}
