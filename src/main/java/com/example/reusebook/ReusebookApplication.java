package com.example.reusebook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.example.reusebook.Model")
public class ReusebookApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReusebookApplication.class, args);
	}

}