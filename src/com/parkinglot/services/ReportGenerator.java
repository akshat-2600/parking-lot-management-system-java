package com.parkinglot.services;

import com.parkinglot.models.Ticket;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.logging.Logger;

public class ReportGenerator {
    private static final Logger logger = Logger.getLogger(ReportGenerator.class.getName());

    public void generateDailyReport(Collection<Ticket> tickets, LocalDate date) {
        String filename = "reports/daily-" + date.toString() + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("Daily Parking Report for " + date.toString() + "\n");
            writer.write("====================================\n");
            for (Ticket t : tickets) {
                if (t.getEntryTime().toLocalDate().equals(date) || (t.getExitTime() != null && t.getExitTime().toLocalDate().equals(date))) {
                    writer.write(t.toString() + "\n");
                }
            }
            logger.info("Generated daily report: " + filename);
        } catch (IOException e) {
            logger.severe("Failed to write report: " + e.getMessage());
        }
    }
}