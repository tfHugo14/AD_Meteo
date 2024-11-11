package org.example.Meteo;

import java.time.LocalDate;
import java.util.List;

public class PrediccionDia {
    private LocalDate dataPredicion; // Guádala para que la ponga mejor como LocalDate
    private int nivelAviso;
    private int tMax;
    private int tMin;
    private int uvMax;
    private List<VariableFranxa> listaVariableFranxa;

    public PrediccionDia(LocalDate dataPredicion, int nivelAviso, int tMax, int tMin, int uvMax, List<VariableFranxa> listaVariableFranxa) {
        this.dataPredicion = dataPredicion;
        this.nivelAviso = nivelAviso;
        this.tMax = tMax;
        this.tMin = tMin;
        this.uvMax = uvMax;
        this.listaVariableFranxa = listaVariableFranxa;
    }

    public LocalDate getDataPredicion() {
        return dataPredicion;
    }

    public void setDataPredicion(LocalDate dataPredicion) {
        this.dataPredicion = dataPredicion;
    }

    public int getNivelAviso() {
        return nivelAviso;
    }

    public void setNivelAviso(int nivelAviso) {
        this.nivelAviso = nivelAviso;
    }

    public int gettMax() {
        return tMax;
    }

    public void settMax(int tMax) {
        this.tMax = tMax;
    }

    public int gettMin() {
        return tMin;
    }

    public void settMin(int tMin) {
        this.tMin = tMin;
    }

    public int getUvMax() {
        return uvMax;
    }

    public void setUvMax(int uvMax) {
        this.uvMax = uvMax;
    }

    public List<VariableFranxa> getListaVariableFranxa() {
        return listaVariableFranxa;
    }

    public void setListaVariableFranxa(List<VariableFranxa> listaVariableFranxa) {
        this.listaVariableFranxa = listaVariableFranxa;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("\n")
            .append(dataPredicion).append(" (aviso: ").append(nivelAviso).append(") , ")
            .append("Máxima: ").append(tMax)
            .append(", Mínima: ").append(tMin)
            .append(", Índice ultravioleta máx: ").append(uvMax).append("\n");
            listaVariableFranxa.forEach(variableFranxa -> sb.append(variableFranxa.toString()));
            sb.append("--------------------------------------------------");
        return sb.toString();
    }
}
