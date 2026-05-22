package com.axity.dinosaurpark.simulation;
import com.axity.dinosaurpark.config.ParkConfig;
import com.axity.dinosaurpark.event.*;
import com.axity.dinosaurpark.model.*;
import com.axity.dinosaurpark.zone.*;
import com.axity.dinosaurpark.monitoring.ParkMonitor;
import com.axity.dinosaurpark.persistence.DatabaseConnection;
import com.axity.dinosaurpark.persistence.EventRepository;
import com.axity.dinosaurpark.persistence.FinancialRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SimulationEngine {

    private final Random random = new Random();

    //*
    // Zonas
    // *
    private final ArrivalZone arrivalZone = new ArrivalZone();
    private final BathroomZone bathroomZone = new BathroomZone();
    private final CentralHub centralHub = new CentralHub();
    private final ObservationEnclosure observationEnclosure = new ObservationEnclosure();
    private final PowerPlant powerPlant = new PowerPlant();

    //*
    // Modelos
    // *
    private final List<Tourist> activeTourists = new ArrayList<>();
    private final List<Dinosaur> dinosaurs = new ArrayList<>();
    private final List<Vehicle> vehicles = new ArrayList<>();

    //*Componentes de Monitoreo y Persistencia
    private final ParkMonitor monitor = new ParkMonitor();
    private final EventRepository eventRepo = new EventRepository();
    private final FinancialRepository financialRepo = new FinancialRepository();

    public SimulationEngine() {
        DatabaseConnection.verificarConexion();
        inicializarParque();
    }

    private void inicializarParque() {
        ParkConfig config = ParkConfig.getInstance();

        int turistasIniciales = config.getInt("tourists");
        for (int i = 1; i <= turistasIniciales; i++) {
            arrivalZone.addToLine(new Tourist("T-" + i, 150.0));
        }
        int carnivoros = config.getInt("dinosaurs.carnivores");
        for (int i = 1; i <= carnivoros; i++) {
            dinosaurs.add(new Dinosaur("Rex-" + i, "CARNIVORE"));
        }
        int herbivoros = config.getInt("dinosaurs.herbivores");
        for (int i = 1; i <= herbivoros; i++) {
            dinosaurs.add(new Dinosaur("Bronto-" + i, "HERBIVORE"));
        }
        vehicles.add(new Vehicle("Safari-01"));
        vehicles.add(new Vehicle("Safari-02"));
        System.out.println("-> [Motor] Inicialización completa. Zonas listas y " + dinosaurs.size() + " dinosaurios cargados.");
    }

    public void iniciarSimulacion(int totalCiclos) {
        System.out.println("\n=======================================================");
        System.out.println(" COMENZANDO LA SIMULACIÓN DIARIA DE DINOSAUR PARK");
        System.out.println("=======================================================");
        boolean isDealsHour = false;
        for (int ciclo = 1; ciclo <= totalCiclos; ciclo++) {
            System.out.println("\n️ [CICLO #" + ciclo + " / " + totalCiclos + "]");
            powerPlant.consumeEnergy();
            System.out.println(" Energía actual de la planta: " + powerPlant.getCurrentEnergy() + "%");
            if (powerPlant.getCurrentEnergy() <= 10) {
                powerPlant.repairEmergency();
                eventRepo.registrarEventoEnHistorial("MANTENIMIENTO", "Reparación de emergencia automática de la PowerPlant.");
            }
            int nuevosIngresos = arrivalZone.processEntries(activeTourists.size());
            for (int i = 0; i < nuevosIngresos; i++) {
                if (!arrivalZone.getWaitingLine().isEmpty()) {
                    activeTourists.add(arrivalZone.getWaitingLine().poll());
                }
            }

            for (Vehicle v : vehicles) {
                v.updateRepairProgress();
            }

            int probabilidad = random.nextInt(100);
            if (probabilidad < 10) {
                new BlackoutEvent().trigger(powerPlant);
                eventRepo.registrarEventoEnHistorial("BLACKOUT", "Falla eléctrica general provocada por un rayo.");
            } else if (probabilidad < 20 && !dinosaurs.isEmpty()) {
                Dinosaur dinoSuelto = dinosaurs.get(random.nextInt(dinosaurs.size()));
                new EscapeEvent().trigger(dinoSuelto);
                eventRepo.registrarEventoEnHistorial("ESCAPE", "El dinosaurio " + dinoSuelto.getName() + " vulneró el perímetro.");
            } else if (probabilidad < 35) {
                isDealsHour = new DealsHourEvent().trigger();
                eventRepo.registrarEventoEnHistorial("MARKETING", "Se activaron descuentos especiales para los visitantes.");
            } else if (probabilidad < 45) {
                new StrormEvent().trigger(powerPlant);
                eventRepo.registrarEventoEnHistorial("CLIMA", "Tormenta tropical incrementó el gasto energético.");
            } else if (probabilidad < 55 && !vehicles.isEmpty()) {
                Vehicle v = vehicles.get(random.nextInt(vehicles.size()));
                new VehicleFailureEvent().trigger(v);
                eventRepo.registrarEventoEnHistorial("VEHÍCULO", "Falla mecánica en la unidad: " + v.getId());
            } else {
                isDealsHour = false;
            }

            for (Tourist tourist : activeTourists) {
                if (!tourist.isActive()) continue;

                int destino = random.nextInt(3);
                switch (destino) {
                    case 0 -> bathroomZone.useBathroom(tourist, isDealsHour);
                    case 1 -> centralHub.interact(tourist, isDealsHour);
                    case 2 -> {
                        boolean hayPeligro = dinosaurs.stream().anyMatch(Dinosaur::isEscaped);
                        if (hayPeligro) {
                            tourist.setSatisfaction(tourist.getSatisfaction() - 30);
                            System.out.println("  [Alerta] Turista " + tourist.getId() + " entró en pánico por un dinosaurio suelto.");
                        } else {
                            observationEnclosure.visitEnclosure(tourist);
                        }
                    }
                }
            }

            monitor.monitorearEstadoCritico(powerPlant, dinosaurs);

            bathroomZone.clearBathroom();
            observationEnclosure.resetStepCounters();
        }
        mostrarReporteFinal();
    }

    private void mostrarReporteFinal() {
        double ingresosTotales = arrivalZone.getTotalRevenue() + centralHub.getTotalRevenue()
                + bathroomZone.getTotalRevenue() + observationEnclosure.getTotalRevenue();
        double egresosTotales = 500.0;

        System.out.println("\n=======================================================");
        System.out.println(" REPORTE DE CIERRE FINANCIERO Y OPERATIVO");
        System.out.println("=======================================================");
        System.out.println(" Ingresos Taquilla (Arrival): $" + arrivalZone.getTotalRevenue());
        System.out.println(" Ingresos Souvenirs (CentralHub): $" + centralHub.getTotalRevenue());
        System.out.println(" Ingresos Adicionales (Baños/SPA): $" + bathroomZone.getTotalRevenue());
        System.out.println(" Ingresos Recinto (Observation): $" + observationEnclosure.getTotalRevenue());
        System.out.println(" TOTAL INGRESOS: $" + ingresosTotales);
        System.out.println(" Estado de Contención: " + (dinosaurs.stream().anyMatch(Dinosaur::isEscaped) ? " ¡Bajo Alerta de Fuga!" : " Seguro"));
        System.out.println("=======================================================");

        //*GUARDADO FINAL EN REPOSITORIO DE PERSISTENCIA
        financialRepo.guardarReporteFinanciero(ingresosTotales, egresosTotales);
    }
}