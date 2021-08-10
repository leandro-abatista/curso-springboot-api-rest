package br.com.curso.api.rest.service.implementacao;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.curso.api.rest.exception.ErroDeAutenticacao;
import br.com.curso.api.rest.exception.RegraDeNegocio;
import br.com.curso.api.rest.model.Usuario;
import br.com.curso.api.rest.repositories.UsuarioRepository;
import br.com.curso.api.rest.service.UsuarioService;

@Service
@Transactional
public class UsuarioServiceImplementacao implements UsuarioService {
	
	@Autowired
	private UsuarioRepository repository;
	
	/**
	 * Método para autenticar um usuário para logar no sistema
	 */
	@Override
	public Usuario autenticar(String email, String senha) {
		
		Optional<Usuario> usuario = repository.findByEmail(email);
		
		if (!usuario.isPresent()) {//se o usuário não estiver no banco
			throw new ErroDeAutenticacao("Usuário não encontrado para o E-mail informado!");
		}
		
		if (!usuario.get().getSenha().equals(senha)) {
			throw new ErroDeAutenticacao("Senha informada é inválida. Por favor insira uma senha válida!");
		}
		
		/*caso exista o email e a senha é válida, retorna o usuário cadastrado*/
		return usuario.get();
	}
	
	/**
	 * Método para salvar um usuário
	 */
	@Override
	public Usuario salvarUsuario(Usuario usuario) {
		
		if (usuario.getNome() == null || usuario.getNome().isEmpty()) {
			throw new RegraDeNegocio("Informe o campo Nome!");
		}
		
		if (usuario.getLogin() == null || usuario.getLogin().isEmpty()) {
			throw new RegraDeNegocio("Informe o campo Login!");
		}
		
		if (usuario.getEmail() == null || usuario.getEmail().isEmpty()) {
			throw new RegraDeNegocio("Informe o campo E-mail!");
		}
		
		if (usuario.getSenha() == null || usuario.getSenha().isEmpty()) {
			throw new RegraDeNegocio("Informe o campo Senha!");
		}
		
		validarEmail(usuario.getEmail());//valida o e-mail antes de salvar o usuário
		
		return repository.save(usuario);
	}
	
	/**
	 * Método para validar e-mail
	 */
	public void validarEmail(String email) {
		boolean existe = repository.existsByEmail(email);
		
		/*Se já existir um e-mail cadastrado para o usuário informado*/
		if (existe) {
			throw new RegraDeNegocio("Já existe um usuário cadastrado com este e-mail!");
		}
	}
	
	/**
	 * Método para obter um usuário pelo id
	 */
	public Optional<Usuario> obterUsuarioPorId(Long id){
		return repository.findById(id);
	}

	@Override
	public void deletarUsuario(Usuario usuario) {
		Objects.requireNonNull(usuario.getId());
		repository.delete(usuario);
	}

}
