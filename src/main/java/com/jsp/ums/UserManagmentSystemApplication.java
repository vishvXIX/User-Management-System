package com.jsp.ums;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.jsp.ums")
public class UserManagmentSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserManagmentSystemApplication.class, args);
	}

}
