package com.axity.dinosaurpark;
import com.axity.dinosaurpark.simulation.SimulationEngine;

public class Main {
    public static void main(String[] args) {
        System.out.println("=======================================================");
        System.out.println(" INICIANDO SISTEMA DE CONTROL - DINOSAUR PARK v1.0");
        System.out.println("=======================================================");

        //*
        //Instanciamos el motor de simulación estructurado
        SimulationEngine engine = new SimulationEngine();

        //*
        // Corremos la simulación diaria por 5 ciclos continuos
        engine.iniciarSimulacion(5);

        System.out.println("\n=======================================================");
        System.out.println(" SIMULACIÓN FINALIZADA Y RESPALDADA EN BASE DE DATOS");
        System.out.println("=======================================================");
    }
}