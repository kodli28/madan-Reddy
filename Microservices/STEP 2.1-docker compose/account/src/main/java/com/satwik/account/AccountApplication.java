package com.satwik.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
		info = @Info(
				title = "Accounts microservice RestApi Documentation",
				description = "Eazy Bank accounts microservices Rest API Documentation",
				version = "v1",
				contact = @Contact(
						name = "satwik Kodlera shivanand",
						email ="satwik.smg@gmail.com",
						url="https://www.eazybytes.com"
				),
				license = @License(
						name="Apache 2.0",
						url = "https://www.eazybytes.com"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Eazy Bank accounts microservices Rest API Documentation",
				url = "https://www.eazybytes.com"
		)
)
/*@ComponentScans({ @ComponentScan("com.eazybytes.accounts.controller") })
@EnableJpaRepositories("com.eazybytes.accounts.repository")
@EntityScan("com.eazybytes.accounts.model")*/
public class AccountApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountApplication.class, args);
	}

}
