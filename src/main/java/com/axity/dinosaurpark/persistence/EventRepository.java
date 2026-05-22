package com.axity.dinosaurpark.persistence;

public class EventRepository {

    public void registrarEventoEnHistorial(String tipoEvento, String descripcion) {
        System.out.println(" [SQL Guardado] INSERT INTO log_eventos (tipo, fecha, status) VALUES ('"
                + tipoEvento + "', NOW(), 'REGISTRADO'); -> " + descripcion);
    }
}