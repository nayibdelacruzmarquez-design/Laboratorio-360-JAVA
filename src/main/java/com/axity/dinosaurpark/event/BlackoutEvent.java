package com.axity.dinosaurpark.event;
import com.axity.dinosaurpark.zone.PowerPlant;

public class BlackoutEvent {
    public void trigger(PowerPlant powerPlant) {
        System.out.println("\n [EVENTO] ¡Apagón general! Un rayo golpeó la central eléctrica.");
        powerPlant.setCurrentEnergy(0.0);
    }
}