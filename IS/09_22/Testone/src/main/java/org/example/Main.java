package org.example;

public class Main {
    public static void main(String[] args) {
        Calcolatrice calcolatrice = new Calcolatrice();
        System.out.println(calcolatrice.somma(10.5f, 15));
        System.out.println(calcolatrice.sottrazione(10, 15.5f));
        System.out.println(calcolatrice.moltiplicazione(10, 15.5f));
        System.out.println(calcolatrice.divisione(10, 15.5f));
    }
}
