package com.axity.dinosaurpark.model;

public class Worker {
    private String name;
    private String role; // "GUARD" o "TECHNICIAN"
    private double dailySalary;

    public Worker(String name, String role, double dailySalary) {
        this.name = name;
        this.role = role;
        this.dailySalary = dailySalary;
    }
    public String getName() { return name; }
    public String getRole() { return role; }
    public double getDailySalary() { return dailySalary; }

    @Override
    public String toString() {
        return "Empleado{" + "Nombre='" + name + '\'' + ", Rol='" + role + '\'' + ", Salario=$" + dailySalary + '}';
    }
}