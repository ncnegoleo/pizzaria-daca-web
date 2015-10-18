package br.edu.ifpb.daca.validation;

public class DifferentArgumentException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public DifferentArgumentException(String message) {
        super(message);
    }
}