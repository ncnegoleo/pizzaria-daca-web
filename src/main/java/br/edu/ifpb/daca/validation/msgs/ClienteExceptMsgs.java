package br.edu.ifpb.daca.validation.msgs;

public enum ClienteExceptMsgs {

	ID_MSG_EXCEPT("O Id do cliente n�o pode ser menor que 1 ou nulo"),
	NOME_MSG_EXCEPT("O nome do cliente n�o pode ser vazio ou nulo"),
	ENDERECO_MSG_EXCEPT("O endere�o n�o pode ser nulo"),
	TELEFONE_MSG_EXCEPT("O telefone n�o pode ser nulo ou vazio e deve\n "
			+ "atender os seguintes formatos: (99)9999-9999 ou 9999-9999");

	private final String valor;

	ClienteExceptMsgs(String msg) {
		valor = msg;
	}

	public String getValor() {
		return valor;
	}

}