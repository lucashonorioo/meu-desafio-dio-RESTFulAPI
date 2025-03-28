package me.dio;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
		info = @Info(title = "API Santander Dev Week", version = "1.0", description = "Documentação da API"),
		servers = {
				@Server(url = "/", description = "Local"),
				@Server(url = "meu-desafio-dio-restfulapi-production.up.railway.app", description = "Railway")
		}
)

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
