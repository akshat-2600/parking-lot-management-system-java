package src.com.parkinglot.models;

import java.time.LocalDateTime;
import java.util.UUID;

public class Ticket {
    private final String ticketId;
    private final Vehicle vehicle;
    private final int spotId;
    private final LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private double amount; // computed on exit

    public Ticket(Vehicle vehicle, int spotId) {
        this.ticketId = UUID.randomUUID().toString();
        this.vehicle = vehicle;
        this.spotId = spotId;
        this.entryTime = LocalDateTime.now();
        this.exitTime = null;
        this.amount = 0.0;
    }

    public String getTicketId() {
        return ticketId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public int getSpotId() {
        return spotId;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public LocalDateTime getExitTime() {
        return exitTime;
    }

    public void setExitTime(LocalDateTime exitTime) {
        this.exitTime = exitTime;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public boolean isActive() {
        return exitTime == null;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId='" + ticketId + '\'' +
                ", vehicle=" + vehicle +
                ", spotId=" + spotId +
                ", entryTime=" + entryTime +
                ", exitTime=" + exitTime +
                ", amount=" + amount +
                '}';
    }
}