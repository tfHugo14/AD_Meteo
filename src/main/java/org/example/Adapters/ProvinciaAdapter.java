package org.example.Adapters;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import org.example.Meteo.Concello;
import org.example.Meteo.Provincia;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProvinciaAdapter extends TypeAdapter<List<Provincia>> {
    @Override
    public void write(JsonWriter jsonWriter, List<Provincia> provincia) throws IOException { // serializar
    }

    @Override
    public List<Provincia> read(JsonReader reader) throws IOException {  // deserializar
        List<Provincia> provincias = new ArrayList<>();

        reader.beginObject(); // {
        while (reader.hasNext()) {
            provincias.add(readProvincia(reader));
        }
        reader.endObject(); //      }
        return provincias;
    }

    public Provincia readProvincia(JsonReader reader) throws IOException {
        String nombreProvincia = reader.nextName();

        ConcelloAdapter concelloAdapter = new ConcelloAdapter();
        List<Concello> concellos = concelloAdapter.read(reader);

        Provincia provincia = new Provincia(nombreProvincia,concellos);
        return provincia;
    }
}
