package org.example.Meteo;

import java.util.ArrayList;
import java.util.List;

public class Provincia {
    private String nombre;
    private List<Concello> concellos;

    public Provincia(String nombre, List<Concello> concellos){
        this.nombre = nombre;
        this.concellos = concellos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Concello> getConcellos() {
        return concellos;
    }

    public void setConcellos(List<Concello> concellos) {
        this.concellos = concellos;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(nombre).append("\n");
        concellos.forEach(c -> sb.append("\t").append(c.toString()).append("\n"));

        return sb.toString();
    }
}
