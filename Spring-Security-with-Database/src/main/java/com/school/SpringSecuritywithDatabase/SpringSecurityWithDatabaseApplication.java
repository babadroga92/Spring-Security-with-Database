package com.school.SpringSecuritywithDatabase;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = "com.school.SpringSecuritywithDatabase" )
@EnableScheduling
public class SpringSecurityWithDatabaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityWithDatabaseApplication.class, args);
	}

}
