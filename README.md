# Dinosaur Park - Proyecto de Simulación

## Descripción del Proyecto
Este proyecto es un simulador básico para la administración de un parque temático de dinosaurios. El sistema funciona de manera automática a través de ciclos de tiempo ( turnos ).

En cada ciclo, el programa calcula cuánta energía gasta la planta eléctrica, procesa la fila de los turistas en la taquilla controlando que no se pase de la capacidad máxima, y simula las actividades de los visitantes (ir al baño/SPA, comprar recuerdos o ver a los dinosaurios). También incluye eventos aleatorios como apagones, tormentas o fallas en los carros de safari para ver cómo reacciona el parque.

---

## Herramientas y Tecnologías
* **Lenguaje:** Java 17
* **Entorno base:** Spring Boot 3.x
* **Gestión de dependencias:** Maven
* **Pruebas unitarias:** JUnit 5
* **Reporte de cobertura:** JaCoCo

---

## Patrones de Diseño Aplicados

### Patrón Singleton
* **Clase:** `ParkConfig`
* **¿Por qué se usó?:** Necesitábamos que los datos del archivo `park.properties` (precios, capacidades, cantidad de dinosaurios) se leyeran una sola vez al arrancar el programa. Con este patrón, aseguramos que exista una única instancia global de la configuración en todo el sistema, evitando leer el archivo de texto a cada rato y ahorrando memoria.

---

## Organización del Código (Paquetes)
El proyecto está dividido en carpetas dentro de `com.axity.dinosaurpark` para que sea fácil de mantener:

* **`config`:** Contiene la lectura del archivo de propiedades (`ParkConfig`).
* **`model`:** Las clases de los objetos del parque (`Dinosaur`, `Tourist`, `Vehicle`, `Worker`).
* **`zone`:** Las áreas del parque que manejan el dinero y las visitas (`ArrivalZone`, `CentralHub`, `ObservationEnclosure`, `BathroomZone`, `PowerPlant`).
* **`event`:** La lógica de las cosas inesperadas que pueden pasar (fugas, tormentas, ofertas).
* **`monitoring`:** Una clase (`ParkMonitor`) que revisa si la energía bajó demasiado o si hay peligro en cada turno.
* **`persistence`:** Clases simuladas (`DatabaseConnection`, `EventRepository`, `FinancialRepository`) que imprimen mensajes en consola simulando cómo se guardarían los datos de dinero y bitácoras en una base de datos.
* **`simulation`:** La clase `SimulationEngine` que une todo y corre el ciclo principal.

---

## Cómo Configurar y Ejecutar el Programa

### Requisitos
* Tener instalado Java 17 o superior.
* Tener configurado Maven.

### Pasos para ejecutar:
1. Abre tu terminal en la carpeta raíz del proyecto (donde está el archivo `pom.xml`).
2. Limpia y compila el código con el siguiente comando:
   ```bash
   mvn clean compile