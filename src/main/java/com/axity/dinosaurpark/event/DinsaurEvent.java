package com.axity.dinosaurpark.event;
import com.axity.dinosaurpark.model.Dinosaur;

public class DinsaurEvent {
    public void trigger(Dinosaur dinosaur) {
        System.out.println("\n [EVENTO] Chequeo médico de rutina para " + dinosaur.getName() + ".");
        dinosaur.setHealth(dinosaur.getHealth() - 10);
    }
}