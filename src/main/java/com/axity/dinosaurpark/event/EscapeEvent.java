package com.axity.dinosaurpark.event;
import com.axity.dinosaurpark.model.Dinosaur;

public class EscapeEvent {
    public void trigger(Dinosaur dinosaur) {
        System.out.println("\n[EVENTO] ¡ALERTA CRÍTICA! El dinosaurio " + dinosaur.getName() + " (" + dinosaur.getType() + ") está rompiendo las contenciones.");
        dinosaur.setEscaped(true);
    }
}