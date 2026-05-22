package com.axity.dinosaurpark.config;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ParkConfig {
    private static ParkConfig instance;
    private Properties properties;
    private ParkConfig() {
        properties = new Properties();
        loadProperties();
    }
    public static ParkConfig getInstance() {
        if (instance == null) {
            instance = new ParkConfig();
        }
        return instance;
    }
    //*Metodo que lee el archivo park.properties*//
    private void loadProperties() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("park.properties")) {
            if (input == null) {
                System.out.println("Lo siento, no se pudo encontrar el archivo park.properties");
                return;
            }
            //*Cargamos las propiedades en memoria*//
            properties.load(input);
        } catch (IOException ex) {
            System.out.println("Error al cargar el archivo de configuración: " + ex.getMessage());
        }
    }

    //*Metodos utilitarios para sacar los datos como Texto, Entero o Double*//
    public String getString(String key) {
        return properties.getProperty(key);
    }

    public int getInt(String key) {
        return Integer.parseInt(properties.getProperty(key, "0"));
    }

    public double getDouble(String key) {
        return Double.parseDouble(properties.getProperty(key, "0.0"));
    }
}
