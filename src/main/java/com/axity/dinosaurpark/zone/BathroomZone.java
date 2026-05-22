package com.axity.dinosaurpark.zone;
import com.axity.dinosaurpark.config.ParkConfig;
import com.axity.dinosaurpark.model.Tourist;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BathroomZone {
    private int maxCapacity;
    private double spaPrice;
    private double spaProbability;
    private double totalRevenue;
    private List<Tourist> currentUsers;
    private Random random;

    public BathroomZone() {
        ParkConfig config = ParkConfig.getInstance();
        this.maxCapacity = config.getInt("bathroom.maxCapacity");
        this.spaPrice = config.getDouble("bathroom.spaPrice");
        this.spaProbability = config.getDouble("bathroom.spaPurchaseProbability");
        this.totalRevenue = 0.0;
        this.currentUsers = new ArrayList<>();
        this.random = new Random();
    }

    public void useBathroom(Tourist tourist, boolean isDealsHour) {
        if (currentUsers.size() < maxCapacity) {
            currentUsers.add(tourist);

            //*Simular opción de pagar SPA*//
            double currentSpaPrice = isDealsHour ? (spaPrice * 0.7) : spaPrice;
            if (random.nextDouble() < spaProbability && tourist.getMoney() >= currentSpaPrice) {
                tourist.spendMoney(currentSpaPrice);
                this.totalRevenue += currentSpaPrice;
                tourist.setSatisfaction(tourist.getSatisfaction() + 20); // SPA relaja mucho
                System.out.println("-> [Baños] Turista " + tourist.getId() + " pagó servicio de SPA por $" + currentSpaPrice);
            }
        } else {
            //*Si está lleno, el turista se aguanta y baja su satisfacción*//
            tourist.setSatisfaction(tourist.getSatisfaction() - 15);
            System.out.println("-> [Baños] ¡Capacidad llena! El turista " + tourist.getId() + " se molestó.");
        }
    }

    //*Vacía los baños en cada ciclo (para simular uso por tiempo corto)*//
    public void clearBathroom() {
        currentUsers.clear();
    }

    public double getTotalRevenue() { return totalRevenue; }
    public int getCurrentVisitors() { return currentUsers.size(); }
}