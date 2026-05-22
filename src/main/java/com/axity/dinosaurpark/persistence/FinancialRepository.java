package com.axity.dinosaurpark.persistence;

public class FinancialRepository {

    public void guardarReporteFinanciero(double ingresosTotales, double egresosTotales) {
        double balanceNeto = ingresosTotales - egresosTotales;
        System.out.println(" [SQL Guardado] INSERT INTO balance_diario (ingresos, egresos, balance) VALUES ($"
                + ingresosTotales + ", $" + egresosTotales + ", $" + balanceNeto + ");");
        System.out.println(" [Persistencia] El libro contable del día ha sido cerrado en la base de datos.");
    }
}