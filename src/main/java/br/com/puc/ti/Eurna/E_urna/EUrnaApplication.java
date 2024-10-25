package br.com.puc.ti.Eurna.E_urna;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(
    info = @Info(
        title = "E-Urna API",
        version = "1.0",
        description = "API para gerenciamento de urnas eletrônicas"
    )
)
public class EUrnaApplication {

	public static void main(String[] args) {
		SpringApplication.run(EUrnaApplication.class, args);
	}

}
