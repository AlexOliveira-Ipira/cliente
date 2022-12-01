package br.dev.oliveiratec.client.services.exceptions;

public class DadosNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DadosNotFoundException(String msg) {
		super(msg);
	}
}
