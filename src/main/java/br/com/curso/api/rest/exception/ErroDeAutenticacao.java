package br.com.curso.api.rest.exception;

public class ErroDeAutenticacao extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ErroDeAutenticacao(String mensagemErro) {
		super(mensagemErro);
	}

}
