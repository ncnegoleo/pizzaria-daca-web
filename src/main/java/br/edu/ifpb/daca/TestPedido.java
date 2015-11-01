package br.edu.ifpb.daca;

public class TestPedido {
    
    public static void main(String[] args) {

        String telefoneRegex = "\\([0-9]{2}\\)[0-9]{5}-[0-9]{4}";
        
        System.out.println("(99)12345-1234".matches(telefoneRegex));
    }
}
