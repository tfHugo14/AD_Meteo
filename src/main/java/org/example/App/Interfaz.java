package org.example.App;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.Adapters.MyDeserializer;
import org.example.Meteo.Prediccion;
import org.example.Meteo.PrediccionDia;
import org.example.Meteo.VariableFranxa;
import org.example.Meteo.VariableMeteoroloxica;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class Interfaz {
    public static void main(String[] args) {
        JFrame frame = new JFrame("MeteoGalicia");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300,150);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        frame.getContentPane().add(mainPanel);
        JLabel label = new JLabel("Introduce el código postal para ver su predicción:");
        mainPanel.add(label);
        JTextField textField = new JTextField(15);
        mainPanel.add(textField);

        JButton button = new JButton("Buscar");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    showPrediccion( getPrediccion( Integer.parseInt(textField.getText()) ) );
                } catch (IOException | URISyntaxException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        mainPanel.add(button);

        frame.setBackground(Color.GRAY);
        frame.setVisible(true);
    }

    public static Prediccion getPrediccion(int codPostal) { // retrieves data
        GsonBuilder gsonBuilder = new GsonBuilder()
                                        .registerTypeAdapter(Prediccion.class, new MyDeserializer());
        Gson gson = gsonBuilder.create();
        try {
            URL url = new URI("https://servizos.meteogalicia.gal/mgrss/predicion/jsonPredConcellos.action?idConc="+codPostal+"&request_locale=gl").toURL();
            InputStream is = url.openConnection().getInputStream();
            BufferedReader urlReader = new BufferedReader(new InputStreamReader(is));
            Prediccion prediccion = gson.fromJson(urlReader, Prediccion.class);
            urlReader.close();
            return prediccion;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void showPrediccion(Prediccion prediccion) throws IOException, URISyntaxException { // prints prediction window
        JFrame mainFrame = new JFrame();
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(300,150);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainFrame.getContentPane().add(mainPanel);

        try {
            TablaTiempo.setPredicciones(mainPanel, prediccion); // creates tables
        } catch (Exception e) {
            e.printStackTrace();
        }

        mainFrame.setVisible(true);
    }
}
