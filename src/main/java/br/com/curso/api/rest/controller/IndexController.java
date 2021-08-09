package br.com.curso.api.rest.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.curso.api.rest.exception.RegraDeNegocio;
import br.com.curso.api.rest.model.Usuario;
import br.com.curso.api.rest.repositories.UsuarioRepository;

@RestController /* classe de controle */
@RequestMapping("/usuarios")
public class IndexController {

	@Autowired
	private UsuarioRepository repository;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping(value = "/", produces = "application/json")
	public ResponseEntity init() {
		// System.out.println("Parametro sendo recebido :: " + nome);
		return new ResponseEntity("Olá Rest API Spring Boot :: ", HttpStatus.OK);
	}

	@GetMapping(value = "/dados", produces = "application/json")
	public ResponseEntity<Usuario> retornaUsuario() {
		Usuario usuario = new Usuario();
		usuario.setId(1L);
		usuario.setNome("Leandro");
		usuario.setEmail("leandro@teste.com");
		usuario.setLogin("leandro");
		usuario.setSenha("123");

		return ResponseEntity.ok(usuario);
	}

	@GetMapping(value = "/listaUsuarios")
	public ResponseEntity<List<Usuario>> listaUsuarios() {

		List<Usuario> usuarios = new ArrayList<>();

		Usuario u1 = new Usuario();
		u1.setId(1L);
		u1.setNome("Leandro");
		u1.setEmail("leandro@teste.com");
		u1.setLogin("leandro");
		u1.setSenha("123");

		Usuario u2 = new Usuario();
		u2.setId(2L);
		u2.setNome("Rodrigo");
		u2.setEmail("rodrigo@teste.com");
		u2.setLogin("rodrigo");
		u2.setSenha("123");

		Usuario u3 = new Usuario();
		u3.setId(3L);
		u3.setNome("Maria Db");
		u3.setEmail("mariadb@teste.com");
		u3.setLogin("maria");
		u3.setSenha("123");

		usuarios.add(u1);
		usuarios.add(u2);
		usuarios.add(u3);

		return ResponseEntity.ok(usuarios);

	}

	@PostMapping(value = "/")
	public ResponseEntity<Usuario> salvarUsuario(@RequestBody Usuario usuario) {
		Usuario usuarioSalvo = repository.save(usuario);
		return new ResponseEntity(usuarioSalvo, HttpStatus.CREATED);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity consultaUsuario(@PathVariable("id") Long id) {
		try {
			Optional<Usuario> usuario = repository.findById(id);
			return new ResponseEntity(usuario.get(), HttpStatus.OK);
		} catch (RegraDeNegocio e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping(value = "/listaTodosUsuarios")
	public ResponseEntity<List<Usuario>> listaTodosUsuarios(){
		List<Usuario> lista = repository.findAll();
		return new ResponseEntity(lista, HttpStatus.OK);
	}

}
