package com.axity.dinosaurpark.model;

public class Vehicle {
    private String id;
    private String status;
    private int repairTicksLeft;

    public Vehicle(String id) {
        this.id = id;
        this.status = "AVAILABLE";
        this.repairTicksLeft = 0;
    }
    // *Metodo para romper el vehículo (usado por los eventos aleatorios)*//
    public void breakVehicle(int repairStepsNeeded) {
        this.status = "BROKEN";
        this.repairTicksLeft = repairStepsNeeded;
    }
    // *Metodo que se ejecutará en cada paso de la simulación para actualizar el tiempo de reparación*//
    public void updateRepairProgress() {
        if ("BROKEN".equals(this.status)) {
            this.repairTicksLeft--;
            if (this.repairTicksLeft <= 0) {
                this.status = "AVAILABLE";
                this.repairTicksLeft = 0;
                System.out.println("-> [Vehículo] El " + id + " ha sido reparado automáticamente y está disponible.");
            }
        }
    }
    public String getId() { return id; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public int getRepairTicksLeft() { return repairTicksLeft; }

    @Override
    public String toString() {
        return "Vehículo{" + "ID='" + id + '\'' + ", Estado='" + status + '\'' + ", TicksReparación=" + repairTicksLeft + '}';
    }
}