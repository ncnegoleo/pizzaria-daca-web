package br.edu.ifpb.daca.validation;

@FunctionalInterface
interface Validator<T> {
    
    boolean vaidate(T t);
}

