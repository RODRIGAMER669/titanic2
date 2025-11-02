package com.psp.primera.evaluacion.titanic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TitanicTest {

    @Test
    void testParsearSalidaValida() {
        String linea = "[1, 2, 3, 4]";
        int[] resultado = Titanic.parsearSalida(linea);
        assertArrayEquals(new int[]{1, 2, 3, 4}, resultado);
    }

    @Test
    void testParsearSalidaConEspacios() {
        String linea = "[  10 , 20 , 30 , 40 ]";
        int[] resultado = Titanic.parsearSalida(linea);
        assertArrayEquals(new int[]{10, 20, 30, 40}, resultado);
    }

    @Test
    void testParsearSalidaInvalida() {
        String linea = "[1, 2, abc, 4]";
        int[] resultado = Titanic.parsearSalida(linea);
        assertNull(resultado);
    }

    @Test
    void testParsearSalidaVacia() {
        String linea = "";
        int[] resultado = Titanic.parsearSalida(linea);
        assertNull(resultado);
    }
}
