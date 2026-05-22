package com.axity.dinosaurpark.monitoring;

import com.axity.dinosaurpark.zone.PowerPlant;
import com.axity.dinosaurpark.model.Dinosaur;
import java.util.List;

public class ParkMonitor {
    public void monitorearEstadoCritico(PowerPlant powerPlant, List<Dinosaur> dinosaurs) {
        System.out.println("\n [MONITOR] Ejecutando análisis de seguridad en tiempo real...");
        //*1. Monitoreo de Energía*//
        if (powerPlant.getCurrentEnergy() < 25.0) {
            System.err.println(" [MONITOR ALERTA] ¡Nivel de energía peligrosamente bajo!: " + powerPlant.getCurrentEnergy() + "%");
        } else {
            System.out.println(" [MONITOR] Infraestructura eléctrica estable.");
        }
        //* 2. Monitoreo de Escapes*//
        long escapados = dinosaurs.stream().filter(Dinosaur::isEscaped).count();
        if (escapados > 0) {
            System.err.println(" [MONITOR CRÍTICO] ¡Se detectaron " + escapados + " dinosaurios fuera de sus recintos!");
        } else {
            System.out.println(" [MONITOR] Perímetro biológico 100% asegurado.");
        }
    }
}