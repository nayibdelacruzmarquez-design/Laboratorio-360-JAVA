package com.axity.dinosaurpark.model;

public class Dinosaur {
    private String name;
    private String type; // "CARNIVORE" o "HERBIVORE"
    private int health;  // 0 a 100
    private boolean isEscaped;

    //*Constructor para inicializar un dinosaurio sano y en su recinto*//
    public Dinosaur(String name, String type) {
        this.name = name;
        this.type = type;
        this.health = 100; // Inicia al 100% de salud
        this.isEscaped = false; // Inicia seguro dentro de su jaula
    }
    public String getName() { return name; }
    public String getType() { return type; }
    public int getHealth() { return health; }
    public void setHealth(int health) {
        //*Validación simple para que la salud no baje de 0 ni suba de 100*//
        this.health = Math.max(0, Math.min(100, health));
    }

    public boolean isEscaped() { return isEscaped; }
    public void setEscaped(boolean escaped) { this.isEscaped = escaped; }

    @Override
    public String toString() {
        return "Dinosaurio{" + "Nombre='" + name + '\'' + ", Tipo='" + type + '\'' +
                ", Salud=" + health + "%, Escapado=" + isEscaped + '}';
    }
}