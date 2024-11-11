package org.example.Adapters;

import com.google.gson.*;
import org.example.Meteo.Prediccion;
import org.example.Meteo.PrediccionDia;
import org.example.Meteo.VariableFranxa;

import java.lang.reflect.Type;

public class MySerializer<P> implements JsonSerializer {
    @Override
    public JsonElement serialize(Object object, Type typeOfSrc, JsonSerializationContext context) {
        Prediccion prediccion = (Prediccion) object;

        JsonObject jsonObject = new JsonObject();
        JsonObject predConcello = new JsonObject();
        predConcello.addProperty("idConcello", prediccion.getConcello().getIdConcello());

        JsonArray listaPredDiaConcello = new JsonArray();
        JsonObject predDia = new JsonObject();
        for ( PrediccionDia p : prediccion.getListaPredDiaConcello() ) {
            predDia.addProperty("dataPredicion", p.getDataPredicion().toString());
            predDia.addProperty("nivelAviso", p.getNivelAviso());
            predDia.addProperty("tMax", p.gettMax());
            predDia.addProperty("tMin", p.gettMin());
            predDia.addProperty("uvMax", p.getUvMax());

            for ( VariableFranxa v : p.getListaVariableFranxa() ) {
                JsonObject varFranxa = new JsonObject();
                varFranxa.addProperty("manha", v.getValorManha());
                varFranxa.addProperty("noite", v.getValorNoche());
                varFranxa.addProperty("tarde", v.getValorTarde());

                predDia.add( v.getVariableMeteorologica().getVariableMeteorologica() , varFranxa );
            }
        }
        listaPredDiaConcello.add(predDia);

        predConcello.add("listaPredDiaConcello", listaPredDiaConcello);
        predConcello.addProperty("nome", prediccion.getConcello().getNome());

        jsonObject.add("predConcello", predConcello);
        return jsonObject;
    }
}
