package com.psp.primera.evaluacion.titanic;

import java.util.List;

public class InformePDF implements GeneradorInforme {

    private static final String MSG_NO_IMPLEMENTADO = "Este formato no está implementado en esta versión.";

    @Override
    public void generarInforme(List<int[]> datosBotes) {
        throw new UnsupportedOperationException(MSG_NO_IMPLEMENTADO);
    }
}
