package org.example.Adapters;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import org.example.Meteo.Concello;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ConcelloAdapter extends TypeAdapter<List<Concello>> {
    @Override
    public void write(JsonWriter jsonWriter, List<Concello> concellos) throws IOException { // serializar
    }

    @Override
    public List<Concello> read(JsonReader reader) throws IOException { // deserializar
        List<Concello> concellos = new ArrayList<>();

        reader.beginArray(); // [
        while (reader.hasNext()) { // hasta que no haya mas elementos
            concellos.add(readConcellos(reader));
        }
        reader.endArray(); //       ]
        return concellos;
    }

    private Concello readConcellos(JsonReader reader) throws IOException {
        int id = -1; String nombre = null;

        reader.beginObject(); // {
        while (reader.hasNext()) {
            String value = reader.nextName();
            if (value.equals("id"))
                id = reader.nextInt();
            else if (value.equals("nombre"))
                nombre = reader.nextString();
            else
                reader.skipValue();
        }
        reader.endObject(); //      }
        return new Concello(id, nombre);
    }
}