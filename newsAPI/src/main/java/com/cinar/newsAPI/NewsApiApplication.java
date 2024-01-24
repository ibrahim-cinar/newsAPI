package com.cinar.newsAPI;

import com.cinar.newsAPI.dto.CreateUserRequest;
import com.cinar.newsAPI.model.Role;
import com.cinar.newsAPI.service.UserService;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import lombok.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;

@SpringBootApplication
public class NewsApiApplication {



    public static void main(String[] args) {
		SpringApplication.run(NewsApiApplication.class, args);
	}

	@Bean
	public OpenAPI springShopOpenAPI(@org.springframework.beans.factory.annotation.Value("${application-description}") String description,
								 @org.springframework.beans.factory.annotation.Value("${application-version}") String version){
	return new OpenAPI()
			.info(new Info()
			.title("Simple Web News API")
			.version(version).
			description(description)
			.license(new License().name("Simple Web News API Licence")));
}

}
