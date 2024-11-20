package org.example.App;

import org.example.Meteo.Prediccion;
import org.example.Meteo.PrediccionDia;
import org.example.Meteo.VariableFranxa;
import org.example.Meteo.VariableMeteoroloxica;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.imageio.ImageIO;

public class TablaTiempo {
    public static void setPredicciones(JPanel mainPanel, Prediccion prediccion) throws URISyntaxException, IOException {
        JLabel nombreConcello = new JLabel(prediccion.getConcello().getNome());
        mainPanel.add(nombreConcello);

        JLabel codPostal = new JLabel(String.valueOf(prediccion.getConcello().getIdConcello()));
        mainPanel.add(codPostal);

        // Custom Table Model
        WeatherTableModel tableModel = new WeatherTableModel(prediccion);
        JTable weatherTable = new JTable(tableModel);
        weatherTable.setBackground(Color.GRAY);
        // Custom Renderers
        weatherTable.getColumnModel().getColumn(1).setCellRenderer(new ImageRenderer());
        weatherTable.getColumnModel().getColumn(2).setCellRenderer(new ImageRenderer());

        JScrollPane scrollPane = new JScrollPane(weatherTable);
        //weatherTable.setFillsViewportHeight(true);

        // Add to the main panel
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(scrollPane, BorderLayout.CENTER);
    }

    // Custom Table Model for Weather Data
    static class WeatherTableModel extends AbstractTableModel {
        private final String[] columnNames = {"Franxa", "Vento", "Ceo", "Chuvia"};
        private final Object[][] data;

        public WeatherTableModel(Prediccion prediccion) throws IOException, URISyntaxException {
            int rowCount = prediccion.getListaPredDiaConcello().size() * 3; // 3 rows per Prediction
            data = new Object[rowCount][4];

            int rowIndex = 0;
            for (PrediccionDia pd : prediccion.getListaPredDiaConcello()) {
                VariableFranxa vfViento = null;
                VariableFranxa vfCielo = null;
                VariableFranxa vfLluvia = null;
                for (VariableFranxa vf : pd.getListaVariableFranxa()) {
                    if (vf.getVariableMeteorologica() == VariableMeteoroloxica.TEMPERATURA_MAXIMA || vf.getVariableMeteorologica() == VariableMeteoroloxica.TEMPERATURA_MINIMA)
                        continue;
                    if (vf.getVariableMeteorologica() == VariableMeteoroloxica.VIENTO) {
                        vfCielo = vf;
                    }
                    if (vf.getVariableMeteorologica() == VariableMeteoroloxica.CIELO){
                        vfViento = vf;
                    }
                    if (vf.getVariableMeteorologica() == VariableMeteoroloxica.LLUVIA){
                        vfLluvia = vf;
                    }
                    if (vfViento != null && vfCielo != null && vfLluvia != null){
                        BufferedImage imgVientoManha = ImageIO.read( new URI(vfViento.getImgManha()).toURL() );
                        BufferedImage imgVientoTarde = ImageIO.read( new URI(vfViento.getImgTarde()).toURL() );
                        BufferedImage imgVientoNoche = ImageIO.read( new URI(vfViento.getImgNoche()).toURL() );

                        BufferedImage imgCeoManha = ImageIO.read( new URI(vfCielo.getImgManha()).toURL() );
                        BufferedImage imgCeoTarde = ImageIO.read( new URI(vfCielo.getImgTarde()).toURL() );
                        BufferedImage imgCeoNoche = ImageIO.read( new URI(vfCielo.getImgNoche()).toURL() );

                        data[rowIndex++] = new Object[] {"Mañá",  new ImageIcon(imgCeoManha), new ImageIcon(imgVientoManha), vfLluvia.getValorManha()+"%"};
                        data[rowIndex++] = new Object[] {"Tarde", new ImageIcon(imgCeoTarde), new ImageIcon(imgVientoTarde), vfLluvia.getValorTarde()+"%"};
                        data[rowIndex++] = new Object[] {"Noite", new ImageIcon(imgCeoNoche), new ImageIcon(imgVientoNoche), vfLluvia.getValorNoche()+"%"};
                    }
                }
            }
        }

        // Necessary Overrides
        @Override
        public int getRowCount() { return data.length; }
        @Override
        public int getColumnCount() { return columnNames.length; }
        @Override
        public String getColumnName(int column) { return columnNames[column]; }
        @Override
        public Object getValueAt(int rowIndex, int columnIndex) { return data[rowIndex][columnIndex]; }
        @Override
        public Class<?> getColumnClass(int columnIndex) { return columnIndex == 1 || columnIndex == 2 ? ImageIcon.class : String.class; }
    }

    // Custom Renderer for Image Icons
    static class ImageRenderer extends JLabel implements TableCellRenderer {
        public ImageRenderer() {
            setHorizontalAlignment(CENTER);
            setVerticalAlignment(CENTER);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            if (value instanceof ImageIcon) {
                setIcon((ImageIcon) value);
            } else {
                setText(value != null ? value.toString() : "");
                setIcon(null);
            }
            return this;
        }
    }
}
