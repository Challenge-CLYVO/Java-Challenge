package br.com.projetovet.projeto_vet.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfig {
	
	@Bean
	OpenAPI configurarSwagger() {
		return new OpenAPI().info(new Info().title("Projeto Vet")
				.description("Este é um projeto que realiza o gerenciamento de entradas para Clinicas, "
				+ "Tutor, Pet, Vacinas, Historico e consultas com serviços de caching, etc")
				.summary("Projeto para gestão do seu Pet")
				.version("1.0.0")
				.termsOfService("Textão")
				.license(new License().url("/licenses")
						.name("Premium License")));
	}

}
