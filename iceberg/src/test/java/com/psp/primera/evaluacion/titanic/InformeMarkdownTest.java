package com.psp.primera.evaluacion.titanic;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InformeMarkdownTest {

    private InformeMarkdown informe;
    private final String archivo = "Informe.md";

    @BeforeEach
    void setUp() {
        informe = new InformeMarkdown();
    }

    @Test
    void testGenerarInforme_CreaArchivoCorrectamente() throws IOException {

        List<int[]> datosBotes = List.of(
                new int[]{2, 3, 1, 6},
                new int[]{1, 2, 0, 3}
        );

        informe.generarInforme(datosBotes);

        assertTrue(Files.exists(Path.of(archivo)), "El archivo Informe.md no fue creado");

        String contenido = Files.readString(Path.of(archivo));

        assertTrue(contenido.contains("# SERVICIO DE EMERGENCIAS"), "No contiene encabezado esperado");
        assertTrue(contenido.contains("## Bote B00"), "No contiene sección del primer bote");
        assertTrue(contenido.contains("## Bote B01"), "No contiene sección del segundo bote");
        assertTrue(contenido.contains("## Total"), "No contiene la sección total");
        assertTrue(contenido.contains("Total Salvados"), "No contiene texto de total salvados");

        assertTrue(contenido.contains("- Total Salvados 9"), "El total de salvados no coincide");
        assertTrue(contenido.contains("Mujeres 5"), "El total de mujeres no coincide");
        assertTrue(contenido.contains("Varones 3"), "El total de varones no coincide");
        assertTrue(contenido.contains("Niños 1"), "El total de niños no coincide");
    }

}
