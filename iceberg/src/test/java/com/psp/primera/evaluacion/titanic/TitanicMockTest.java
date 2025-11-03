package com.psp.primera.evaluacion.titanic;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TitanicMockTest {

    @Test
    void testEjecutarBoteMock() throws Exception {

        Process processMock = mock(Process.class);
        Runtime runtimeMock = mock(Runtime.class);
        BufferedReader readerMock = mock(BufferedReader.class);

        when(runtimeMock.exec(any(String[].class))).thenReturn(processMock);
        when(processMock.getInputStream()).thenReturn(new ByteArrayInputStream("[1,2,3,4]".getBytes()));
        when(processMock.waitFor()).thenReturn(0);

        String salida = ejecutarBoteMock(new String[]{}, runtimeMock);

        assertEquals("[1,2,3,4]", salida);
    }

    private String ejecutarBoteMock(String[] comandos, Runtime runtime) {
        StringBuilder output = new StringBuilder();
        try {
            Process process = runtime.exec(comandos);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line);
            }

            int exitVal = process.waitFor();
            return (exitVal == 0) ? output.toString() : "";
        } catch (Exception e) {
            return "";
        }
    }
}
