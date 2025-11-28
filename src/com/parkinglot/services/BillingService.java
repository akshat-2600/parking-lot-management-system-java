package com.parkinglot.services;

import com.parkinglot.models.Ticket; 
import com.parkinglot.models.ParkingSpot;

import java.time.Duration;
import java.time.LocalDateTime;

public class BillingService {

    public double calculateBill(Ticket ticket, ParkingSpot spot) {
        LocalDateTime entry = ticket.getEntryTime();
        LocalDateTime exit = ticket.getExitTime();
        if (exit == null) {
            exit = LocalDateTime.now();
        }
        Duration duration = Duration.between(entry, exit);
        long minutes = Math.max(1, duration.toMinutes()); 
        double hours = Math.ceil(minutes / 60.0); 
        double rate = spot.getHourlyRate();
        return hours * rate;
    }
}