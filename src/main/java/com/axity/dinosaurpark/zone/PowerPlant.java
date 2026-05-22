package com.axity.dinosaurpark.zone;
import com.axity.dinosaurpark.config.ParkConfig;

public class PowerPlant {
    private double currentEnergy;
    private double consumptionPerStep;
    private double maintenanceCost;
    private double repairCost;
    private double totalExpenses; // Guardará los egresos/gastos

    public PowerPlant() {
        ParkConfig config = ParkConfig.getInstance();
        this.currentEnergy = config.getDouble("powerplant.initialEnergy");
        this.consumptionPerStep = config.getDouble("powerplant.consumptionPerStep");
        this.maintenanceCost = config.getDouble("powerplant.maintenanceCost");
        this.repairCost = config.getDouble("powerplant.repairCost");
        this.totalExpenses = 0.0;
    }

    //*Baja la energía en cada paso de simulación
    public void consumeEnergy() {
        this.currentEnergy = Math.max(0, this.currentEnergy - consumptionPerStep);
    }

    //*Realizar mantenimiento preventivo(gasto regular)
    public void performMaintenance() {
        this.currentEnergy = 100.0; // Recarga al máximo
        this.totalExpenses += maintenanceCost;
        System.out.println("-> [Energía] Mantenimiento realizado. Costo: $" + maintenanceCost);
    }

    //*Reparación de emergencia (gasto fuerte por falla)
    public void repairEmergency() {
        this.currentEnergy = 100.0;
        this.totalExpenses += repairCost;
        System.out.println("-> [Energía] ¡REPARACIÓN DE EMERGENCIA REALIZADA! Costo: $" + repairCost);
    }
    public double getCurrentEnergy() { return currentEnergy; }
    public void setCurrentEnergy(double energy) { this.currentEnergy = Math.max(0, Math.min(100, energy)); }
    public double getTotalExpenses() { return totalExpenses; }
}