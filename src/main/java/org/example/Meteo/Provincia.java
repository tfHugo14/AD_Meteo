package org.example.Meteo;

import java.util.List;

public class Provincia {
    private String nombre;
    private List<Concello> concellos;

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
        sb.append(nombre);
        concellos.forEach(c -> sb.append(c.toString()));

        return sb.toString();
    }
}
