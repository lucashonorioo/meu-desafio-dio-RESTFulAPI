package me.dio.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "API de Gerenciamento de Geladeira",
                version = "v1",
                description = "Esta API permite o gerenciamento de geladeiras, locais de armazenamento e alimentos."
        ),
        servers = {
                @Server(url = "http://localhost:8080", description = "Servidor Local")
        }
)
public class SwaggerConfig {
}
