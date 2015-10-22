package br.edu.ifpb.daca;

public class TestPedido {
    
    public static void main(String[] args) {

        String telefone = "8791955410";
        String prefix = telefone.substring(0,2);
        String frist = telefone.substring(2,6);
        String secund = telefone.substring(6,10);
        System.out.println(prefix + " " + frist + " " + secund);
    }
}
