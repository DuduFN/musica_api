package br.com.magna.musicaapi.infra;


public class ValidacaoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ValidacaoException(String mensagem) {
		super(mensagem);
	}
}
