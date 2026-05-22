package com.axity.dinosaurpark.zone;
import com.axity.dinosaurpark.config.ParkConfig;
import com.axity.dinosaurpark.model.Tourist;
import java.util.Random;

public class ObservationEnclosure {
    private int maxBasic, maxPremium, maxVip;
    private double feeBasic, feePremium, feeVip;
    private int currentBasic, currentPremium, currentVip;
    private double totalRevenue;
    private Random random;

    public ObservationEnclosure() {
        ParkConfig config = ParkConfig.getInstance();

        this.maxBasic = config.getInt("enclosure.basic.maxVisitors");
        this.feeBasic = config.getDouble("enclosure.basic.entryFee");

        this.maxPremium = config.getInt("enclosure.premium.maxVisitors");
        this.feePremium = config.getDouble("enclosure.premium.entryFee");

        this.maxVip = config.getInt("enclosure.vip.maxVisitors");
        this.feeVip = config.getDouble("enclosure.vip.entryFee");

        this.totalRevenue = 0.0;
        this.random = new Random();
    }

    public void visitEnclosure(Tourist tourist) {
        int choice = random.nextInt(3); // 0 = Basic, 1 = Premium, 2 = VIP
        if (choice == 2 && currentVip < maxVip && tourist.getMoney() >= feeVip) {
            tourist.spendMoney(feeVip);
            this.totalRevenue += feeVip;
            currentVip++;
            tourist.setSatisfaction(tourist.getSatisfaction() + 30); // Experiencia VIP da mucha felicidad
        } else if (choice == 1 && currentPremium < maxPremium && tourist.getMoney() >= feePremium) {
            tourist.spendMoney(feePremium);
            this.totalRevenue += feePremium;
            currentPremium++;
            tourist.setSatisfaction(tourist.getSatisfaction() + 15);
        } else if (currentBasic < maxBasic && tourist.getMoney() >= feeBasic) {
            tourist.spendMoney(feeBasic);
            this.totalRevenue += feeBasic;
            currentBasic++;
            tourist.setSatisfaction(tourist.getSatisfaction() + 5);
        } else {
            //*Si no le alcanzó o estaba lleno, se frustra un poco
            tourist.setSatisfaction(tourist.getSatisfaction() - 5);
        }
    }
    //*Resetea los contadores en cada paso para simular que entran y salen flujos de personas
    public void resetStepCounters() {
        this.currentBasic = 0;
        this.currentPremium = 0;
        this.currentVip = 0;
    }

    public double getTotalRevenue() { return totalRevenue; }
    public int getTotalActiveVisitors() { return currentBasic + currentPremium + currentVip; }
}