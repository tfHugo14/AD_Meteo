package org.example.Meteo;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum VariableMeteoroloxica {
    CIELO("ceo"),
    LLUVIA("pchoiva"),
    TEMPERATURA_MAXIMA("tmaxFranxa"),
    TEMPERATURA_MINIMA("tminFranxa"),
    VIENTO("vento");

    private String variableMeteorologica;

    VariableMeteoroloxica(String variableMeteorologica) {
        this.variableMeteorologica = variableMeteorologica;
    }

    public String getVariableMeteorologica() {
        return variableMeteorologica;
    }

    public static Map<VariableMeteoroloxica, String> getMap() {
        return Stream.of(values())
                .collect(Collectors.toMap(
                        variable -> variable,
                        VariableMeteoroloxica::getVariableMeteorologica
                ));
    }

    // procesa los datos de ceo para que de un resultado legible

}
