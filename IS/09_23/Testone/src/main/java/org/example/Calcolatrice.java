package org.example;

public class Calcolatrice {
    public float somma(float a, float b) {
        return a + b;
    }

    public float sottrazione(float a, float b) {
        return a - b;
    }

    public float moltiplicazione(float a, float b) {
        return a * b;
    }

    public Float divisione(float a, float b) {
        if (b != 0)
            return a / b;
        return null;
    }
}
