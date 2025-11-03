package com.psp.primera.evaluacion.titanic;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class InformeMarkdown implements GeneradorInforme {

    private static final String NOMBRE_ARCHIVO = "Informe.md";
    private static final String FORMATO_FECHA = "dd/MM/yyyy";
    private static final String FORMATO_HORA = "HH:mm:ss";
    private static final String ENCABEZADO = "# SERVICIO DE EMERGENCIAS\n\n";
    private static final String TXT_EJECUCION = "Ejecución realizada el día %s a las %s\n\n";
    private static final String[] CATEGORIAS = {"Varones", "Mujeres", "Niños"};
    private static final String TXT_EXITO = "Informe generado correctamente.";
    private static final String TXT_TOTAL = "## Total\n";
    private static final String TXT_TOTAL_SALVADOS = "- Total Salvados %d\n";
    private static final String TXT_FORMATO = "  - %s %d\n";
    private static final String TXT_SALTO_LINEA = "\n";
    private static final String TXT_IDBOTE = "## Bote B%02d\n\n";

    @Override
    public void generarInforme(List<int[]> datosBotes) {
        DateTimeFormatter fecha = DateTimeFormatter.ofPattern(FORMATO_FECHA);
        DateTimeFormatter hora = DateTimeFormatter.ofPattern(FORMATO_HORA);
        LocalDateTime ahora = LocalDateTime.now();

        int totalMujeres = 0;
        int totalVarones = 0;
        int totalNinos = 0;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(NOMBRE_ARCHIVO))) {

            writer.write(ENCABEZADO);
            writer.write(String.format(TXT_EJECUCION,
                    fecha.format(ahora), hora.format(ahora)));

            for (int i = 0; i < datosBotes.size(); i++) {
                int[] datos = datosBotes.get(i);
                int varones = datos[0];
                int mujeres = datos[1];
                int ninos = datos[2];
                int total = datos[3];

                writer.write(String.format(TXT_IDBOTE, i));
                writer.write(String.format(TXT_TOTAL_SALVADOS, total));
                escribir(writer, TXT_FORMATO, CATEGORIAS[1], mujeres);
                escribir(writer, TXT_FORMATO, CATEGORIAS[0], varones);
                escribir(writer, TXT_FORMATO, CATEGORIAS[2], ninos);
                writer.write(TXT_SALTO_LINEA);

                totalMujeres += mujeres;
                totalVarones += varones;
                totalNinos += ninos;
            }

            int totalSalvados = totalMujeres + totalVarones + totalNinos;

            writer.write(TXT_TOTAL);
            writer.write(String.format(TXT_TOTAL_SALVADOS, totalSalvados));
            escribir(writer, TXT_FORMATO, CATEGORIAS[1], totalMujeres);
            escribir(writer, TXT_FORMATO, CATEGORIAS[0], totalVarones);
            escribir(writer, TXT_FORMATO, CATEGORIAS[2], totalNinos);
            writer.write(TXT_SALTO_LINEA);

            System.out.println(TXT_EXITO);

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void escribir(BufferedWriter writer, String texto, String categoria, int cantidad) throws IOException {
        writer.write(String.format(texto, categoria, cantidad));

    }
}
