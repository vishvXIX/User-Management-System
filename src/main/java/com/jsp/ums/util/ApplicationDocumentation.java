package com.jsp.ums.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import jakarta.servlet.http.HttpServlet;

@Configuration
@OpenAPIDefinition
public class ApplicationDocumentation {
	
	
	
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
//		
//		 int statusCode = determineStatusCode(); 
//		 resp.setStatus(statusCode);
//		 resp.getWriter().println("Your API response data goes here");
////		super.doGet(req, resp);
//	}
	
//	private int determineStatusCode() {
//		
//		return HttpServletResponse.SC_CREATED;
//	}

	Contact contact() {
		return new Contact()
				.email("vishvpatolioya@gmail.com")
				.url("www.linkedin.com/in/\r\n"
						+ "vishv-patoliya\r\n"
						+ "\r\n"
						+ "");
	}

	Info info() {
		return new Info()
				.title("Uaser Management System API")
				.version("1.0v")
				.description("User Management System is a RESTful API built using " + 
				"Spring Boot and MySQL database");
	}
	
	@Bean
	OpenAPI openAPI() {
		return new OpenAPI().info(info());
	}
	
}
