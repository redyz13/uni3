package org.example;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CalcolatriceTest {
    private static Calcolatrice calcolatrice;

    @BeforeAll
    public static void setUp() {
        calcolatrice = new Calcolatrice();
    }

    @Test
    public void testSomma() {
        assertEquals(12, calcolatrice.somma(11, 1), "Somma corretta");
    }

    @Test
    public void testSottrazione() {
        assertEquals(10, calcolatrice.sottrazione(11, 1), "Sottrazione corretta");
    }

    @Test
    public void testMoltiplicazione() {
        assertEquals(22, calcolatrice.moltiplicazione(11, 2), "Moltiplicazione corretta");
    }

    @Test
    public void testDivisione() {
        assertNull(calcolatrice.divisione(11, 0), "Divisione per 0 corretta");
        assertEquals(6, calcolatrice.divisione(12, 2), "Divisione corretta");
    }
}
