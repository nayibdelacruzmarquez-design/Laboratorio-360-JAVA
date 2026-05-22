package com.axity.dinosaurpark.zone;
import com.axity.dinosaurpark.config.ParkConfig;
import com.axity.dinosaurpark.model.Tourist;
import java.util.Random;

public class CentralHub {
    private double souvenirPrice;
    private double souvenirProbability;
    private double totalRevenue;
    private Random random;

    public CentralHub() {
        ParkConfig config = ParkConfig.getInstance();
        this.souvenirPrice = config.getDouble("hub.souvenirPrice");
        this.souvenirProbability = config.getDouble("hub.souvenirPurchaseProbability");
        this.totalRevenue = 0.0;
        this.random = new Random();
    }

    //*Simula la interaccion de un turista en el hub central*//
    public void interact(Tourist tourist, boolean isDealsHour) {
        double currentPrice = isDealsHour ? (souvenirPrice * 0.7) : souvenirPrice;

        if (random.nextDouble() < souvenirProbability) {
            if (tourist.getMoney() >= currentPrice) {
                tourist.spendMoney(currentPrice);
                this.totalRevenue += currentPrice;
                //*Aumenta su satisfacción por comprar un souvenir
                tourist.setSatisfaction(tourist.getSatisfaction() + 10);
                System.out.println("-> [CentralHub] Turista " + tourist.getId() + " compró un souvenir por $" + currentPrice);
            }
        }
    }
    public double getTotalRevenue() { return totalRevenue; }
}