package com.psp.primera.evaluacion.titanic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class Titanic {

    public static final String[] COMANDOS = {
        "java",
        "-cp",
        "iceberg/target/classes",
        "com.psp.primera.evaluacion.titanic.Botes"
    };

    public static final String MSG_ERROR = "Se ha producido un error al ejecutar el comando";

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            String tripulantes = desplegar(COMANDOS);
            System.out.print(tripulantes);
        }
    }

    public static String desplegar(String[] comandos) {
        StringBuilder output = new StringBuilder();
        Random random = new Random();

        try {
            Process process = Runtime.getRuntime().exec(comandos);

            // Leer salida estándar
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream())
            );

            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            int exitVal = process.waitFor();

            // Espera aleatoria entre 2 y 6 segundos
            int espera = 2000 + random.nextInt(4001);
            Thread.sleep(espera);

            if (exitVal == 0) {
                return output.toString();
            } else {
                System.err.println(MSG_ERROR);
                return "";
            }

        } catch (IOException | InterruptedException e) {
            System.err.println("Error: " + e.getMessage());
            return "";
        }
    }
}
