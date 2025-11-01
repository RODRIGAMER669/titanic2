package com.psp.primera.evaluacion.titanic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static final String[] COMANDOS = {
        "java",
        "-cp",
        "/home/debian/2ºDAM/PSP/Titanic2/iceberg/target/classes",
        "com.psp.primera.evaluacion.titanic.Botes"
    };

    public static final String MSG_ERROR = "Se ha producido un error al ejecutar el comando";

    public static void main(String[] args) {

        try {
            Process process = Runtime.getRuntime().exec(COMANDOS);
            StringBuilder output = new StringBuilder();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            //Dejamos el programa bloqueado hasta que termine el otro.
            int exitVal = process.waitFor();
            if (exitVal == 0) {
                System.out.println(output);
                System.exit(0);
            } else {
                System.out.println(MSG_ERROR);
                System.exit(1);
            }

        } catch (IOException | InterruptedException e) {
            System.exit(34);
        }
    }
}
