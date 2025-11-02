package com.psp.primera.evaluacion.titanic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Titanic {

    public static final String[] COMANDOS = {
        "java",
        "-cp",
        "iceberg/target/classes",
        "com.psp.primera.evaluacion.titanic.Botes"
    };
    public static final String ESPACIO = "";
    public static final String COMA = ",";
    public static final String LLAVE_ABIERTA = "[";
    public static final String LLAVE_CERRADA = "]";
    public static final String TXT_BOTES = "B%02d desplegado.\n";

    public static void main(String[] args) {
        List<int[]> resultados = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            String salida = ejecutarBote(COMANDOS);
            if (salida != null && !salida.isBlank()) {
                int[] datos = parsearSalida(salida);
                if (datos != null) {
                    resultados.add(datos);
                }
            }
            System.out.printf(TXT_BOTES, i);
        }

        // Usando la interfaz
        GeneradorInforme generador = new InformeMarkdown();
        generador.generarInforme(resultados);
    }

    private static String ejecutarBote(String[] comandos) {
        StringBuilder output = new StringBuilder();
        try {
            Process process = Runtime.getRuntime().exec(comandos);
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream())
            );

            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line);
            }

            int exitVal = process.waitFor();
            Thread.sleep(2000 + new Random().nextInt(4001));

            return (exitVal == 0) ? output.toString() : ESPACIO;

        } catch (IOException | InterruptedException e) {
            System.err.println(e.getMessage());
            return ESPACIO;
        }
    }

    private static int[] parsearSalida(String linea) {
        try {
            linea = linea.replace(LLAVE_ABIERTA, ESPACIO).replace(LLAVE_CERRADA, ESPACIO).trim();
            String[] partes = linea.split(COMA);
            int[] nums = new int[4];
            for (int i = 0; i < partes.length; i++) {
                nums[i] = Integer.parseInt(partes[i].trim());
            }
            return nums;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }
}
