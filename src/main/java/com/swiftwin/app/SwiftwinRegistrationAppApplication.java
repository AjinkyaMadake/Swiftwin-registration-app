package com.swiftwin.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class SwiftwinRegistrationAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SwiftwinRegistrationAppApplication.class, args);
	}

}
