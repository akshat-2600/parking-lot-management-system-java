package src.com.parkinglot;

import src.com.parkinglot.exceptions.InvalidTicketException;
import src.com.parkinglot.exceptions.NoSpotAvailableException;
import src.com.parkinglot.models.*;
import src.com.parkinglot.services.*;
import src.com.parkinglot.utils.ConsoleUtils;

import java.util.List;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        // Initialize services
        BillingService billingService = new BillingService();
        PaymentService paymentService = new PaymentService();
        ParkingLotManager manager = new ParkingLotManager(billingService, paymentService);
        ReportGenerator reportGenerator = new ReportGenerator();

        // Initialize parking spots (sample)
        initializeSampleSpots(manager);

        System.out.println("=== Parking Lot Management System ===");

        boolean exit = false;
        while (!exit) {
            printMenu();
            String choice = ConsoleUtils.SCANNER.nextLine().trim();
            switch (choice) {
                case "1":
                    handleParkVehicle(manager);
                    break;
                case "2":
                    handleExitVehicle(manager, reportGenerator);
                    break;
                case "3":
                    showAvailableSpots(manager);
                    break;
                case "4":
                    searchVehicle(manager);
                    break;
                case "5":
                    showRevenue(manager);
                    break;
                case "6":
                    System.out.println("Exiting... Goodbye!");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void initializeSampleSpots(ParkingLotManager manager) {
        double carRate = 20.0; 
        double bikeRate = 10.0;
        double truckRate = 40.0;

        for (int i = 1; i <= 10; i++) {
            manager.addSpot(new ParkingSpot(i, SpotType.CAR, carRate));
        }
        for (int i = 11; i <= 15; i++) {
            manager.addSpot(new ParkingSpot(i, SpotType.BIKE, bikeRate));
        }
        for (int i = 16; i <= 18; i++) {
            manager.addSpot(new ParkingSpot(i, SpotType.TRUCK, truckRate));
        }
    }

    private static void printMenu() {
        System.out.println("\nSelect an option:");
        System.out.println("1. Park Vehicle");
        System.out.println("2. Exit Vehicle");
        System.out.println("3. Show Available Spots");
        System.out.println("4. Search Vehicle (Active)");
        System.out.println("5. Show Parking Lot Revenue");
        System.out.println("6. Exit System");
        System.out.print("Choice: ");
    }

    private static void handleParkVehicle(ParkingLotManager manager) {
        try {
            System.out.print("Enter vehicle registration number: ");
            String reg = ConsoleUtils.SCANNER.nextLine().trim();
            System.out.print("Enter vehicle type (CAR/BIKE/TRUCK): ");
            String typeStr = ConsoleUtils.SCANNER.nextLine().trim().toUpperCase();
            SpotType type;
            try {
                type = SpotType.valueOf(typeStr);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid vehicle type. Defaulting to CAR.");
                type = SpotType.CAR;
            }
            System.out.print("Enter vehicle color (optional): ");
            String color = ConsoleUtils.SCANNER.nextLine().trim();
            Vehicle vehicle = new Vehicle(reg, type, color);
            // assign
            try {
                var ticket = manager.assignSpot(vehicle);
                System.out.println("Vehicle parked. Ticket ID: " + ticket.getTicketId());
                System.out.println("Allocated spot: " + ticket.getSpotId());
                System.out.println("Entry time: " + ticket.getEntryTime());
            } catch (NoSpotAvailableException e) {
                System.out.println("Parking Full for this vehicle type. Try later.");
            }
        } catch (Exception e) {
            logger.severe("Error in parking: " + e.getMessage());
        } finally {
            ConsoleUtils.pauseForEnter();
        }
    }

    private static void handleExitVehicle(ParkingLotManager manager, ReportGenerator reportGenerator) {
        try {
            System.out.print("Enter Ticket ID: ");
            String ticketId = ConsoleUtils.SCANNER.nextLine().trim();
            try {
                var ticket = manager.releaseSpot(ticketId);
                PaymentService ps = new PaymentService();
                String receipt = ps.generateReceipt(ticket);
                System.out.println(receipt);
            } catch (InvalidTicketException e) {
                System.out.println("Invalid ticket: " + e.getMessage());
            }
        } catch (Exception e) {
            logger.severe("Error in exit flow: " + e.getMessage());
        } finally {
            ConsoleUtils.pauseForEnter();
        }
    }

    private static void showAvailableSpots(ParkingLotManager manager) {
        List<ParkingSpot> available = manager.getAvailableSpots();
        System.out.println("Available Spots (" + available.size() + "):");
        available.forEach(s -> System.out.println("SpotId: " + s.getSpotId() + " Type: " + s.getSpotType() + " Rate: $" + s.getHourlyRate()));
        ConsoleUtils.pauseForEnter();
    }

    private static void searchVehicle(ParkingLotManager manager) {
        System.out.print("Enter registration number to search (active only): ");
        String reg = ConsoleUtils.SCANNER.nextLine().trim();
        var ticket = manager.searchActiveTicketByVehicle(reg);
        if (ticket == null) {
            System.out.println("Vehicle not found in active tickets.");
        } else {
            System.out.println("Active Ticket: " + ticket.getTicketId());
            System.out.println("Spot: " + ticket.getSpotId());
            System.out.println("Entry: " + ticket.getEntryTime());
        }
        ConsoleUtils.pauseForEnter();
    }

    private static void showRevenue(ParkingLotManager manager) {
        System.out.println("Total Revenue collected: $" + String.format("%.2f", manager.getTotalRevenue()));
        ConsoleUtils.pauseForEnter();
    }
}