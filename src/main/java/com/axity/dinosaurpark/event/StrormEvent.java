package com.axity.dinosaurpark.event;
import com.axity.dinosaurpark.zone.PowerPlant;
public class StrormEvent {
    public void trigger(PowerPlant powerPlant) {
        System.out.println("\n [EVENTO] ¡Una tormenta tropical azota el parque! El consumo eléctrico aumenta.");
        powerPlant.consumeEnergy();
    }
}
