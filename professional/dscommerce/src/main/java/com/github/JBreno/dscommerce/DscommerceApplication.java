package com.github.JBreno.dscommerce;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class DscommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DscommerceApplication.class, args);
	}

	@Bean
	public CommandLineRunner testeSenha() {
		return args -> {
			PasswordEncoder encoder = new BCryptPasswordEncoder();

			String senhaDigitada = "123456"; // senha real
			String hashDoBanco = "$2a$10$kB6Oqbnsh.wzcCPEM8Z4leI9/HBmZJFpzis4D7C0LCb3yNvL8msoG"; // hash do Heroku

			boolean bate = encoder.matches(senhaDigitada, hashDoBanco);
			System.out.println("----------------- TESTE ------------------");
			System.out.println("Senha bate? " + bate);
		};
	}
}
