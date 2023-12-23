package com.pizango.marca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MarcaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarcaApplication.class, args);
	}

}
