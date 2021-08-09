package br.com.curso.api.rest.exception;

public class RegraDeNegocio extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public RegraDeNegocio(String mensagemErro) {
		super(mensagemErro);
	}
}
