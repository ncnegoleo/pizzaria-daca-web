package br.edu.ifpb.daca.validation.msgs;

public enum LancheExcepMsg {

    ID_MSG_EXCEPT("O Id do lanche n�o pode ser menor que 1 ou nulo"),
    DESCRICAO_MSG_EXCEP("A descric�o do lanche n�o pode ser vazia ou nula"),
    VALOR_UNIT_MSG_EXCEP("O valor unitario do lanche n�o pode ser negativo");

    private final String valor;

    LancheExcepMsg(String msg) {
        valor = msg;
    }

    public String getValor() {
        return valor;
    }
}
