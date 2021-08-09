package br.com.curso.api.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController/*classe de controle*/
@RequestMapping("/usuarios")
public class IndexController {
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping(value = "/", produces = "application/json")
	public ResponseEntity init() {
		return new ResponseEntity("Olá Rest API Spring Boot", HttpStatus.OK);
	}
}
