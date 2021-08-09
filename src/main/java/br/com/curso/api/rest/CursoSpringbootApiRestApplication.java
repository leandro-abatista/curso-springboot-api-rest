package br.com.curso.api.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EntityScan(basePackages = {"br.com.curso.api.rest.model"})/*cria e ler as classes deste pacote no banco de dados*/
@ComponentScan(basePackages = {"br.com.curso.api.rest.*"})/*ler e configura tudo que estiver dentro destas pastas*/
@EnableJpaRepositories(basePackages = {"br.com.curso.api.rest.repositories"})/*interface do JPA*/
@EnableTransactionManagement
@EnableWebMvc/*ativa recursos mvc. pode trabalhar com várias arquiteturas dentro do projeto*/
@RestController
@EnableAutoConfiguration/*configura automaticamente todo o projeto*/
public class CursoSpringbootApiRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(CursoSpringbootApiRestApplication.class, args);
	}

}
