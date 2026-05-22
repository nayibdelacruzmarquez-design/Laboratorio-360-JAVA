package com.axity.dinosaurpark.model;

public class Tourist {
    private String id;
    private double money;
    private int satisfaction; // De 0 a 100
    private boolean isActive;  // true si está en el parque, false si ya se fue

    public Tourist(String id, double initialMoney) {
        this.id = id;
        this.money = initialMoney;
        this.satisfaction = 100; // Todos entran felices al parque
        this.isActive = true;    // Entran activos
    }
    public void spendMoney(double amount) {
        if (this.money >= amount) {
            this.money -= amount;
        } else {
            this.money = 0;
        }
    }
    public String getId() { return id; }
    public double getMoney() { return money; }
    public int getSatisfaction() { return satisfaction; }
    public void setSatisfaction(int satisfaction) {
        this.satisfaction = Math.max(0, Math.min(100, satisfaction));
    }
    public boolean isActive() { return isActive; }
    public void setActive(boolean active) { this.isActive = active; }

    @Override
    public String toString() {
        return "Turista{" + "ID='" + id + '\'' + ", Dinero=$" + money +
                ", Satisfacción=" + satisfaction + "%, Activo=" + isActive + '}';
    }
}