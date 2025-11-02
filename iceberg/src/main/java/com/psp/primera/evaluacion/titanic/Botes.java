package com.psp.primera.evaluacion.titanic;

import java.util.Arrays;
import java.util.Random;

public class Botes {

    public static void main(String[] args) {
        int[] personas = generarPersonas();

        System.out.println(Arrays.toString(personas));
    }

    public static int[] generarPersonas() {
        Random r = new Random();

        int totalPersonas = r.nextInt(100) + 1;
        int ninos = r.nextInt(totalPersonas + 1);
        int mujeres = r.nextInt(totalPersonas - ninos + 1);
        int varones = totalPersonas - (ninos + mujeres);

        return new int[]{varones, mujeres, ninos, totalPersonas};
    }

}
