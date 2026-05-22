package com.axity.dinosaurpark.event;
import com.axity.dinosaurpark.model.Vehicle;

public class VehicleFailureEvent {
    public void trigger(Vehicle vehicle) {
        System.out.println("\n [EVENTO] ¡Falla mecánica! El vehículo de safari " + vehicle.getId() + " se ha averiado.");
        vehicle.breakVehicle(3);
    }
}