package com.axity.dinosaurpark;
import com.axity.dinosaurpark.config.ParkConfig;
import com.axity.dinosaurpark.model.Dinosaur;
import com.axity.dinosaurpark.model.Tourist;
import com.axity.dinosaurpark.model.Vehicle;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Pruebas Unitarias del Sistema - Dinosaur Park")
class DinosaurParkApplicationTests {

    @Test
    @DisplayName("Patrón Singleton: Validar instancia única de ParkConfig")
    void testParkConfigSingleton() {
        ParkConfig instance1 = ParkConfig.getInstance();
        ParkConfig instance2 = ParkConfig.getInstance();
        assertNotNull(instance1, "La configuración del parque no debería ser nula");
        assertSame(instance1, instance2, "Ambas instancias deberían ser exactamente la misma (Singleton)");
    }

    @Test
    @DisplayName("Modelo Dinosaurio: Validar ciclo de vida, salud y escapes")
    void testDinosaurModel() {
        Dinosaur rex = new Dinosaur("T-Rex", "CARNIVORE");
        assertEquals("T-Rex", rex.getName());
        assertEquals("CARNIVORE", rex.getType());
        assertEquals(100, rex.getHealth(), "Debe iniciar con 100% de salud");
        assertFalse(rex.isEscaped(), "No debe iniciar escapado");
        rex.setEscaped(true);
        assertTrue(rex.isEscaped());
    }

    @Test
    @DisplayName("Modelo Turista: Validar control de billetera y límites de satisfacción")
    void testTouristSpendingAndSatisfaction() {
        Tourist tourist = new Tourist("T-01", 100.0);
        assertEquals("T-01", tourist.getId());
        assertEquals(100.0, tourist.getMoney());
        assertEquals(100, tourist.getSatisfaction());
        tourist.spendMoney(40.0);
        assertEquals(60.0, tourist.getMoney());
        tourist.spendMoney(200.0);
        assertEquals(0.0, tourist.getMoney(), "El dinero nunca debe ser negativo");
        tourist.setSatisfaction(150);
        assertEquals(100, tourist.getSatisfaction());
    }

    @Test
    @DisplayName("Modelo Vehículo: Validar reportes de fallas y autoreparación por ciclos")
    void testVehicleBreakdownAndRepair() {
        Vehicle safari = new Vehicle("Safari-Test");
        assertEquals("AVAILABLE", safari.getStatus());
        assertEquals(0, safari.getRepairTicksLeft());
        safari.breakVehicle(2);
        assertEquals("BROKEN", safari.getStatus());
        assertEquals(2, safari.getRepairTicksLeft());
        safari.updateRepairProgress();
        assertEquals("BROKEN", safari.getStatus(), "Aún debería estar roto");
        safari.updateRepairProgress();
        assertEquals("AVAILABLE", safari.getStatus(), "Debería estar disponible de nuevo");
        assertEquals(0, safari.getRepairTicksLeft());
    }
}