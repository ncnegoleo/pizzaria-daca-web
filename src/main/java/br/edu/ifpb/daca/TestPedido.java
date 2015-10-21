package br.edu.ifpb.daca;

public class TestPedido {
    
    public static void main(String[] args) {

        String telefoneRegex = "(\\([0-9]{2}\\)){0,1}[0-9]{4,5}-[0-9]{4}";
        System.out.println("99195-5410".matches(telefoneRegex));
    }
}
