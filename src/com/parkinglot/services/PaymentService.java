package src.com.parkinglot.services;

import src.com.parkinglot.exceptions.PaymentFailedException;
import src.com.parkinglot.models.Ticket;

import java.util.Random;
import java.util.logging.Logger;

public class PaymentService {
    private static final Logger logger = Logger.getLogger(PaymentService.class.getName());
    private final Random random = new Random();

    public boolean processPayment(Ticket ticket) throws PaymentFailedException {
        double failureChance = 0.03; 
        if (random.nextDouble() < failureChance) {
            logger.warning("Payment processing simulated failure for ticket " + ticket.getTicketId());
            throw new PaymentFailedException("Payment failed due to gateway error. Try again.");
        }
        logger.info("Payment processed for ticket " + ticket.getTicketId() + " amount: " + ticket.getAmount());
        return true;
    }

    public String generateReceipt(Ticket ticket) {
        StringBuilder sb = new StringBuilder();
        sb.append("---------- Receipt ----------\n");
        sb.append("Ticket ID: ").append(ticket.getTicketId()).append("\n");
        sb.append("Vehicle: ").append(ticket.getVehicle().getRegistrationNumber()).append("\n");
        sb.append("Entry: ").append(ticket.getEntryTime()).append("\n");
        sb.append("Exit: ").append(ticket.getExitTime()).append("\n");
        sb.append("Amount Paid: ₹").append(String.format("%.2f", ticket.getAmount())).append("\n");
        sb.append("-----------------------------\n");
        return sb.toString();
    }
}