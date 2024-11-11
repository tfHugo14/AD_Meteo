package org.example.Meteo;

import java.util.List;

public class Prediccion {
    private Concello concello;
    private List<PrediccionDia> listaPredDiaConcello;

    public Prediccion(Concello concello, List<PrediccionDia> listaPredDiaConcello) {
        this.concello = concello;
        this.listaPredDiaConcello = listaPredDiaConcello;
    }

    public Concello getConcello() {
        return concello;
    }

    public void setConcello(Concello concello) {
        this.concello = concello;
    }

    public List<PrediccionDia> getListaPredDiaConcello() {
        return listaPredDiaConcello;
    }

    public void setListaPredDiaConcello(List<PrediccionDia> listaPredDiaConcello) {
        this.listaPredDiaConcello = listaPredDiaConcello;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(concello.getNome()).append(" [").append(concello.getIdConcello()).append("]")
                .append("\n--------------------------------------------------");
                listaPredDiaConcello.forEach(prediccionDia -> sb.append(prediccionDia.toString()));

        return sb.toString();
    }
}
