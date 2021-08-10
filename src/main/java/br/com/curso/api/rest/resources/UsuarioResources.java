package br.com.curso.api.rest.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.curso.api.rest.dto.UsuarioDTO;
import br.com.curso.api.rest.exception.ErroDeAutenticacao;
import br.com.curso.api.rest.exception.RegraDeNegocio;
import br.com.curso.api.rest.model.Usuario;
import br.com.curso.api.rest.service.UsuarioService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/usuarios")
public class UsuarioResources {
	
	@Autowired
	private UsuarioService service;
	
	@PostMapping("/")
	public ResponseEntity salvarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
		Usuario usuario = Usuario.builder()
				.nome(usuarioDTO.getNome())
				.email(usuarioDTO.getEmail())
				.login(usuarioDTO.getLogin())
				.senha(usuarioDTO.getSenha()).build();
		try {
			Usuario usuarioSalvo = service.salvarUsuario(usuario);
			return new ResponseEntity(usuarioSalvo, HttpStatus.CREATED);
		} catch (RegraDeNegocio e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PostMapping("/autenticar")
	public ResponseEntity autenticar(@RequestBody UsuarioDTO usuarioDTO) {
		try {
			Usuario usuarioAutenticado = service.autenticar(usuarioDTO.getEmail(), usuarioDTO.getSenha());
			return ResponseEntity.ok(usuarioAutenticado);
		} catch (ErroDeAutenticacao e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity deletarUsuario(@PathVariable("id") Long id) {
		return service.obterUsuarioPorId(id).map( entidade -> {
			service.deletarUsuario(entidade);
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}).orElseGet(
			() -> new ResponseEntity("Usuário não encontrado na base de dados!", HttpStatus.BAD_REQUEST)
		);
	}
	
}
