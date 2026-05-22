package com.axity.dinosaurpark.zone;
import com.axity.dinosaurpark.config.ParkConfig;
import com.axity.dinosaurpark.model.Tourist;
import java.util.LinkedList;
import java.util.Queue;

public class ArrivalZone {
    private Queue<Tourist> waitingLine;
    private int maxCapacity;
    private double ticketPrice;
    private double totalRevenue;

    public ArrivalZone() {
        this.waitingLine = new LinkedList<>();
        ParkConfig config = ParkConfig.getInstance();
        this.maxCapacity = config.getInt("arrival.maxCapacity");
        this.ticketPrice = config.getDouble("arrival.ticketprice");
        this.totalRevenue = 0.0;
    }

    public void addToLine(Tourist tourist) {
        waitingLine.add(tourist);
    }

    public int processEntries(int currentActiveTourists) {
        int entered = 0;
        while (!waitingLine.isEmpty() && (currentActiveTourists + entered) < maxCapacity) {
            Tourist tourist = waitingLine.poll();
            if (tourist.getMoney() >= ticketPrice) {
                tourist.spendMoney(ticketPrice);
                totalRevenue += ticketPrice;
                entered++;
            } else {
                tourist.setActive(false);
                tourist.setSatisfaction(20);
            }
        }
        return entered;
    }
    public Queue<Tourist> getWaitingLine() { return waitingLine; }
    public double getTotalRevenue() { return totalRevenue; }
    public int getMaxCapacity() { return maxCapacity; }
}