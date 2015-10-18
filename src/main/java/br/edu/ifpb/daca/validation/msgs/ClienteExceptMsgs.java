package br.edu.ifpb.daca.validation.msgs;

public enum ClienteExceptMsgs {

	ID_MSG_EXCEPT("O Id do cliente não pode ser menor que 1 ou nulo"),
	NOME_MSG_EXCEPT("O nome do cliente não pode ser vazio ou nulo"),
	ENDERECO_MSG_EXCEPT("O endereço não pode ser nulo"),
	TELEFONE_MSG_EXCEPT("O telefone não pode ser nulo ou vazio e deve\n "
			+ "atender os seguintes formatos: (99)9999-9999 ou 9999-9999");

	private final String valor;

	ClienteExceptMsgs(String msg) {
		valor = msg;
	}

	public String getValor() {
		return valor;
	}

}