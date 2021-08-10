package br.com.curso.api.rest.service;

import java.util.Optional;

import br.com.curso.api.rest.model.Usuario;

public interface UsuarioService {
	
	Usuario autenticar(String email, String senha);
	Usuario salvarUsuario(Usuario usuario);
	void validarEmail(String email);
	Optional<Usuario> obterUsuarioPorId(Long id);
	void deletarUsuario(Usuario usuario);
}
