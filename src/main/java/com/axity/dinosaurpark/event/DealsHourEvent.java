package com.axity.dinosaurpark.event;
public class DealsHourEvent {
    public boolean trigger(){
        System.out.println("\n [EVENTO] ¡Hora de Ofertas Activa! 30% de descuento en SPA y Souvenirs.");
        return true;
    }
}
