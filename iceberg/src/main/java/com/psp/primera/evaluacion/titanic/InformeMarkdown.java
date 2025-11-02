package com.psp.primera.evaluacion.titanic;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class InformeMarkdown implements GeneradorInforme {

    @Override
    public void generarInforme(List<int[]> datosBotes) {
        DateTimeFormatter fecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter hora = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime ahora = LocalDateTime.now();

        int totalMujeres = 0;
        int totalVarones = 0;
        int totalNinos = 0;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Informe.md"))) {

            writer.write("# SERVICIO DE EMERGENCIAS\n\n");
            writer.write(String.format("Ejecución realizada el día %s a las %s\n\n",
                    fecha.format(ahora), hora.format(ahora)));

            for (int i = 0; i < datosBotes.size(); i++) {
                int[] datos = datosBotes.get(i);
                int varones = datos[0];
                int mujeres = datos[1];
                int ninos = datos[2];
                int total = datos[3];

                writer.write(String.format("## Bote B%02d\n\n", i));
                writer.write(String.format("- Total Salvados %d\n", total));
                writer.write(String.format("  - Mujeres %d\n", mujeres));
                writer.write(String.format("  - Varones %d\n", varones));
                writer.write(String.format("  - Niños %d\n\n", ninos));

                totalMujeres += mujeres;
                totalVarones += varones;
                totalNinos += ninos;
            }

            int totalSalvados = totalMujeres + totalVarones + totalNinos;

            writer.write("## Total\n");
            writer.write(String.format("- Total Salvados %d\n", totalSalvados));
            writer.write(String.format("  - Mujeres %d\n", totalMujeres));
            writer.write(String.format("  - Varones %d\n", totalVarones));
            writer.write(String.format("  - Niños %d\n", totalNinos));
            writer.write("\n");

            System.out.println("✅ Informe.md generado correctamente.");

        } catch (IOException e) {
            System.err.println("Error al generar el informe: " + e.getMessage());
        }
    }
}
