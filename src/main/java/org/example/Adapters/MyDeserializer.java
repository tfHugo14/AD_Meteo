package org.example.Adapters;

import com.google.gson.*;
import org.example.Meteo.*;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class MyDeserializer implements JsonDeserializer<Prediccion> {
    @Override
    public Prediccion deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        JsonObject predConcello = jsonObject.getAsJsonObject("predConcello");

        // deserialize Concello
        String nome = predConcello.getAsJsonPrimitive("nome").getAsString();
        int idConcello = predConcello.getAsJsonPrimitive("idConcello").getAsInt();
        Concello concello = new Concello(idConcello, nome);

        List<PrediccionDia> listaObjetosPredDiaConcello = new ArrayList<>();
        JsonArray listaPredDiaConcello = predConcello.getAsJsonArray("listaPredDiaConcello");
        PrediccionDia prediccionDiaDesiralized;

        // deserialize each PrediccionDia in listaPredDiaConcello
        for ( int i = 0; i<listaPredDiaConcello.size(); i++ ){
            JsonObject prediccionDiaJson = listaPredDiaConcello.get(i).getAsJsonObject();
            int tMax = prediccionDiaJson.get("tMax").isJsonNull() ? -1441 : prediccionDiaJson.getAsJsonPrimitive("tMax").getAsInt();
            int tMin = prediccionDiaJson.get("tMin").isJsonNull() ? -1441 : prediccionDiaJson.getAsJsonPrimitive("tMin").getAsInt();
            int uvMax = prediccionDiaJson.get("uvMax").isJsonNull() ? -1441 : prediccionDiaJson.getAsJsonPrimitive("uvMax").getAsInt();
            int nivelAviso = prediccionDiaJson.get("nivelAviso").isJsonNull() ? -1 : prediccionDiaJson.getAsJsonPrimitive("nivelAviso").getAsInt();
            LocalDate dataPredicion =
                    LocalDateTime.parse(prediccionDiaJson.get("dataPredicion").isJsonNull() ?
                            "2444-04-04T00:00:00" :
                            prediccionDiaJson.getAsJsonPrimitive("dataPredicion").getAsString()).toLocalDate();

            List<VariableFranxa> listaVariableFranxa = new ArrayList<>();
            Map<VariableMeteoroloxica, String> variablesMap = VariableMeteoroloxica.getMap();
            // deserialize each VariableFranxa in each PrediccionDia
            for (Map.Entry<VariableMeteoroloxica, String> entry : variablesMap.entrySet()) {
                String value = entry.getValue(); // value is the name of the object in json
                VariableMeteoroloxica key = entry.getKey();

                if (!prediccionDiaJson.get(value).isJsonNull()) { // only instantiate if the VariableFranxa exists in json
                    JsonObject jsonObjectVarMeteo = prediccionDiaJson.get(value).getAsJsonObject();
                    int valorManha = jsonObjectVarMeteo.getAsJsonPrimitive("manha").getAsInt();
                    int valorTarde = jsonObjectVarMeteo.getAsJsonPrimitive("tarde").getAsInt();
                    int valorNoche = jsonObjectVarMeteo.getAsJsonPrimitive("noite").getAsInt();
                    VariableFranxa variableFranxa = new VariableFranxa(key, valorManha, valorTarde, valorNoche);
                    listaVariableFranxa.add(variableFranxa);
                }
            }
            prediccionDiaDesiralized = new PrediccionDia(dataPredicion, nivelAviso, tMax, tMin, uvMax, listaVariableFranxa);
            listaObjetosPredDiaConcello.add(prediccionDiaDesiralized);
        }

        return new Prediccion(concello, listaObjetosPredDiaConcello);
    }
}