package org.example.Meteo;

import java.net.MalformedURLException;
import java.net.URL;

public class VariableFranxa {
    private final int NO_DATA = -9999;
    private VariableMeteoroloxica variableMeteorologica;
    private int valorManha;
    private int valorTarde;
    private int valorNoche;

    private String imgManha;
    private String imgTarde;
    private String imgNoche;

    public VariableFranxa(VariableMeteoroloxica variableMeteorologica, int valorManha, int valorTarde, int valorNoche) throws MalformedURLException {
        this.variableMeteorologica = variableMeteorologica;
        this.valorManha = valorManha;
        this.valorTarde = valorTarde;
        this.valorNoche = valorNoche;

        if (variableMeteorologica == VariableMeteoroloxica.CIELO || variableMeteorologica == VariableMeteoroloxica.VIENTO) {
            String baseUrlPath = getBaseUrlPath(variableMeteorologica);

            imgManha = baseUrlPath + this.valorManha + ".png";
            imgTarde = baseUrlPath + this.valorTarde + ".png";
            imgNoche = baseUrlPath + this.valorNoche + ".png";
        }
    }

    private String getBaseUrlPath(VariableMeteoroloxica variableMeteorologica) {
        switch (variableMeteorologica) {
            case CIELO:
                return "https://www.meteogalicia.gal/datosred/infoweb/meteo/imagenes/meteoros/ceo/";
            case VIENTO:
                return "https://www.meteogalicia.gal/datosred/infoweb/meteo/imagenes/meteoros/vento/";
            default:
                throw new IllegalArgumentException("Unknown VariableMeteoroloxica: " + variableMeteorologica);
        }
    }

    public VariableMeteoroloxica getVariableMeteorologica() {
        return variableMeteorologica;
    }

    public void setVariableMeteorologica(VariableMeteoroloxica variableMeteorologica) {
        this.variableMeteorologica = variableMeteorologica;
    }

    public int getValorManha() {
        return valorManha;
    }

    public void setValorManha(int valorManha) {
        this.valorManha = valorManha;
    }

    public int getValorTarde() {
        return valorTarde;
    }

    public void setValorTarde(int valorTarde) {
        this.valorTarde = valorTarde;
    }

    public int getValorNoche() {
        return valorNoche;
    }

    public void setValorNoche(int valorNoche) {
        this.valorNoche = valorNoche;
    }

    public String getImgManha() {
        return imgManha;
    }

    public String getImgTarde() {
        return imgTarde;
    }

    public String getImgNoche() {
        return imgNoche;
    }

    @Override
    public String toString() {
        return variableMeteorologica.toString()+": (" + (valorManha==NO_DATA?"-":valorManha) +", "+ (valorTarde==NO_DATA?"-":valorTarde) + ", " + (valorNoche==NO_DATA?"-":valorNoche) + ")\n";
    }
}
