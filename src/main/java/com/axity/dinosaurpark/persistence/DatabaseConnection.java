package com.axity.dinosaurpark.persistence;
import com.axity.dinosaurpark.config.ParkConfig;

public class DatabaseConnection {

    public static void verificarConexion() {
        ParkConfig config = ParkConfig.getInstance();
        String url = config.getString("datasource.url");
        String user = config.getString("datasource.username");

        System.out.println("\n [Persistencia] Conectando a base de datos simulada...");
        System.out.println(" [Persistencia] URL: " + (url != null ? url : "jdbc:mysql://localhost:3306/dinosaur_park"));
        System.out.println(" [Persistencia] Usuario: " + (user != null ? user : "root"));
        System.out.println(" [Persistencia] ¡Conexión establecida exitosamente!");
    }
}