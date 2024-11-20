package org.example.App;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import org.example.Adapters.ConcelloAdapter;
import org.example.Adapters.MyDeserializer;
import org.example.Adapters.MySerializer;
import org.example.Adapters.ProvinciaAdapter;
import org.example.Meteo.Concello;
import org.example.Meteo.Prediccion;
import org.example.Meteo.Provincia;

import java.io.*;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, URISyntaxException {

        GsonBuilder gsonBuilder = new GsonBuilder()
                .registerTypeAdapter(Prediccion.class, new MyDeserializer())
//                .registerTypeAdapter(Prediccion.class, new MySerializer<Prediccion>())
                .setPrettyPrinting();

        Gson gson = gsonBuilder.create();

        System.out.print("Introduce el codigo postal para ver su predicción: ");
        Scanner input = new Scanner(System.in);
        int codPostal = input.nextInt();
        URL url = new URI("https://servizos.meteogalicia.gal/mgrss/predicion/jsonPredConcellos.action?idConc="+codPostal+"&request_locale=gl").toURL();
        InputStream is = url.openConnection().getInputStream();
        BufferedReader urlReader = new BufferedReader(new InputStreamReader(is));
        System.out.println(gson.fromJson(urlReader, Prediccion.class));
        urlReader.close();
    }
}

/*
//---Probar: ConcelloAdapter y ProvinciaAdapter
        Type tipoConcelloList = new TypeToken<List<Concello>>() {}.getType();
        Type tipoProvinciaList = new TypeToken<List<Provincia>>() {}.getType();

        GsonBuilder gsonBuilder = new GsonBuilder()
                .registerTypeAdapter(tipoProvinciaList, new ProvinciaAdapter())
                .registerTypeAdapter(tipoConcelloList, new ConcelloAdapter())
                .setPrettyPrinting();

        Gson gson = gsonBuilder.create();

        JsonReader concellosReader = new JsonReader(new InputStreamReader(new FileInputStream("concellos.json"), "UTF-8"));
        List<Concello> concellos = gson.fromJson(concellosReader, tipoConcelloList);
        concellos.forEach(System.out::println);

        JsonReader provinciasReader = new JsonReader(new InputStreamReader(new FileInputStream("provincias.json"), "UTF-8"));
        List<Provincia> provincias = gson.fromJson(provinciasReader, tipoProvinciaList);
        provincias.forEach(System.out::println);
*/

/*
//---Debug: imprimir contenido de la url a un json
        Writer writer = Files.newBufferedWriter(Path.of("prediccionMeteo.json"));
        int data = urlReader.read();
        while (data != -1) {
            writer.write( (char) data );
            data = urlReader.read();
        }
        writer.close(); urlReader.close();
*/

/*--Crear: Prediccion para probar la serializacion
        Concello concello = new Concello(1, "Vigo");
        VariableMeteoroloxica variableMeteoroloxica = VariableMeteoroloxica.CIELO; VariableMeteoroloxica variableMeteoroloxica2 = VariableMeteoroloxica.LLUVIA; VariableMeteoroloxica variableMeteoroloxica3 = VariableMeteoroloxica.TEMPERATURA_MAXIMA; VariableMeteoroloxica variableMeteoroloxica4 = VariableMeteoroloxica.TEMPERATURA_MINIMA; VariableMeteoroloxica variableMeteoroloxica5 = VariableMeteoroloxica.VIENTO;
        VariableFranxa variableFranxa = new VariableFranxa(variableMeteoroloxica, 1, 2, 3); VariableFranxa variableFranxa2 = new VariableFranxa(variableMeteoroloxica2, 4, 5, 6);VariableFranxa variableFranxa3 = new VariableFranxa(variableMeteoroloxica3, 7, 8, 9);VariableFranxa variableFranxa4 = new VariableFranxa(variableMeteoroloxica4, 10, 11, 12);VariableFranxa variableFranxa5 = new VariableFranxa(variableMeteoroloxica5, 13, 14, 15);
        List<VariableFranxa> listaVariableFranxa = List.of(variableFranxa, variableFranxa2, variableFranxa3, variableFranxa4, variableFranxa5);
        LocalDate dataPredicion = LocalDate.of(2024, 11, 4);
        PrediccionDia prediccionDia = new PrediccionDia(dataPredicion, 1, 2, 3, 4, listaVariableFranxa);
        Prediccion prediccion = new Prediccion(concello, List.of(prediccionDia));

        Writer w = Files.newBufferedWriter(Path.of("prediccion.json"));
        Gson gson = gsonBuilder.create();
        gson.toJson(prediccion, w);
        w.close();
*/