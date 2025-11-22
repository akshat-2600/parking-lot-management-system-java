// package src.com.parkinglot.services;

// import src.com.parkinglot.exceptions.InvalidTicketException;
// import src.com.parkinglot.exceptions.NoSpotAvailableException;
// import src.com.parkinglot.models.ParkingSpot;
// import src.com.parkinglot.models.SpotType;
// import src.com.parkinglot.models.Ticket;
// import src.com.parkinglot.models.Vehicle;

// import java.time.LocalDateTime;
// import java.util.*;
// import java.util.logging.Logger;

// public class ParkingLotManager {
//     private static final Logger logger = Logger.getLogger(ParkingLotManager.class.getName());

//     private final Map<Integer, ParkingSpot> spots = new HashMap<>();
//     private final Map<String, Ticket> activeTickets = new HashMap<>();
//     private final Map<String, Ticket> allTickets = new HashMap<>(); 
//     private double totalRevenue = 0.0;

//     private final BillingService billingService;
//     private final PaymentService paymentService;

//     public ParkingLotManager(BillingService billingService, PaymentService paymentService) {
//         this.billingService = billingService;
//         this.paymentService = paymentService;
//     }

//     public void addSpot(ParkingSpot spot) {
//         spots.put(spot.getSpotId(), spot);
//     }

//     public synchronized Ticket assignSpot(Vehicle vehicle) throws NoSpotAvailableException {
//         SpotType type = vehicle.getVehicleType();
//         Optional<ParkingSpot> opt = spots.values().stream()
//                 .filter(s -> s.getSpotType() == type && !s.isOccupied())
//                 .sorted(Comparator.comparingInt(ParkingSpot::getSpotId))
//                 .findFirst();

//         if (!opt.isPresent()) {
//             logger.warning("No free spot available for vehicle type: " + type);
//             throw new NoSpotAvailableException("No free spot available for " + type);
//         }

//         ParkingSpot spot = opt.get();
//         spot.occupy();

//         Ticket ticket = new Ticket(vehicle, spot.getSpotId());
//         activeTickets.put(ticket.getTicketId(), ticket);
//         allTickets.put(ticket.getTicketId(), ticket);

//         logger.info("Assigned spot " + spot.getSpotId() + " to vehicle " + vehicle.getRegistrationNumber()
//                 + " ticket " + ticket.getTicketId());
//         return ticket;
//     }

//     public synchronized Ticket releaseSpot(String ticketId) throws InvalidTicketException {
//         Ticket ticket = activeTickets.get(ticketId);
//         if (ticket == null) {
//             throw new InvalidTicketException("Ticket not found or already closed: " + ticketId);
//         }

//         ticket.setExitTime(LocalDateTime.now());

//         ParkingSpot spot = spots.get(ticket.getSpotId());
//         if (spot == null) {
//             throw new InvalidTicketException("Assigned spot not found for ticket: " + ticketId);
//         }

//         double amount = billingService.calculateBill(ticket, spot);
//         ticket.setAmount(amount);

//         try {
//             paymentService.processPayment(ticket);
//         } catch (Exception e) {
//             logger.severe("Payment failed for ticket " + ticketId + " : " + e.getMessage());
//             ticket.setAmount(0.0);
//         }

//         spot.free();
//         totalRevenue += ticket.getAmount();

//         activeTickets.remove(ticketId);
//         allTickets.put(ticketId, ticket);

//         logger.info("Released spot " + spot.getSpotId() + " for ticket " + ticketId + ", amount: " + ticket.getAmount());
//         return ticket;
//     }

//     public int getAvailableSpotsCount() {
//         return (int) spots.values().stream().filter(s -> !s.isOccupied()).count();
//     }

//     public List<ParkingSpot> getAvailableSpots() {
//         List<ParkingSpot> list = new ArrayList<>();
//         for (ParkingSpot s : spots.values()) {
//             if (!s.isOccupied()) list.add(s);
//         }
//         list.sort(Comparator.comparingInt(ParkingSpot::getSpotId));
//         return list;
//     }

//     public List<ParkingSpot> getOccupiedSpots() {
//         List<ParkingSpot> list = new ArrayList<>();
//         for (ParkingSpot s : spots.values()) {
//             if (s.isOccupied()) list.add(s);
//         }
//         list.sort(Comparator.comparingInt(ParkingSpot::getSpotId));
//         return list;
//     }

//     public Ticket searchActiveTicketByVehicle(String registrationNumber) {
//         for (Ticket t : activeTickets.values()) {
//             if (t.getVehicle().getRegistrationNumber().equalsIgnoreCase(registrationNumber)) {
//                 return t;
//             }
//         }
//         return null;
//     }

//     public double getTotalRevenue() {
//         return totalRevenue;
//     }

//     public Collection<Ticket> getAllTickets() {
//         return Collections.unmodifiableCollection(allTickets.values());
//     }
// }


package src.com.parkinglot.services;

import src.com.parkinglot.exceptions.InvalidTicketException;
import src.com.parkinglot.exceptions.NoSpotAvailableException;
import src.com.parkinglot.models.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

public class ParkingLotManager {
    private static final Logger logger = Logger.getLogger(ParkingLotManager.class.getName());

    // per-type lists to speed up search
    private final Map<SpotType, List<ParkingSpot>> spotsByType = new EnumMap<>(SpotType.class);

    // ticket maps
    private final Map<String, Ticket> activeTickets = new ConcurrentHashMap<>();
    private final Map<String, Ticket> allTickets = new ConcurrentHashMap<>();

    private double totalRevenue = 0.0;

    private final BillingService billingService;
    private final PaymentService paymentService;

    public ParkingLotManager(BillingService billingService, PaymentService paymentService) {
        this.billingService = billingService;
        this.paymentService = paymentService;
        for (SpotType t : SpotType.values()) spotsByType.put(t, new ArrayList<>());
    }

    public synchronized void addSpot(ParkingSpot spot) {
        spotsByType.get(spot.getSpotType()).add(spot);
    }

    public synchronized Ticket assignSpot(Vehicle vehicle) throws NoSpotAvailableException {
        List<ParkingSpot> list = spotsByType.get(vehicle.getVehicleType());
        Optional<ParkingSpot> opt = list.stream().filter(s -> !s.isOccupied()).sorted(Comparator.comparingInt(ParkingSpot::getSpotId)).findFirst();
        if (!opt.isPresent()) {
            logger.warning("No free spot for " + vehicle.getVehicleType());
            throw new NoSpotAvailableException("No free spot available for " + vehicle.getVehicleType());
        }
        ParkingSpot spot = opt.get();
        spot.occupy();
        Ticket ticket = new Ticket(vehicle, spot.getSpotId());
        activeTickets.put(ticket.getTicketId(), ticket);
        allTickets.put(ticket.getTicketId(), ticket);
        logger.info("Assigned spot " + spot.getSpotId() + " to vehicle " + vehicle.getRegistrationNumber());
        return ticket;
    }

    public synchronized Ticket releaseSpot(String ticketId) throws InvalidTicketException {
        Ticket ticket = activeTickets.get(ticketId);
        if (ticket == null) throw new InvalidTicketException("Ticket not found or already closed: " + ticketId);

        ticket.setExitTime(LocalDateTime.now());
        ParkingSpot spot = findSpotById(ticket.getSpotId());
        if (spot == null) throw new InvalidTicketException("Assigned spot not found for ticket: " + ticketId);

        double amount = billingService.calculateBill(ticket, spot);
        ticket.setAmount(amount);
        try {
            paymentService.processPayment(ticket);
            ticket.setAmount(amount);
        } catch (Exception e) {
            logger.severe("Payment failed for ticket " + ticketId + ": " + e.getMessage());
            ticket.setAmount(0.0); // design choice: mark unpaid
        }

        spot.free();
        totalRevenue += ticket.getAmount();

        activeTickets.remove(ticketId);
        allTickets.put(ticketId, ticket);
        logger.info("Released spot " + spot.getSpotId() + " for ticket " + ticketId);
        return ticket;
    }

    private ParkingSpot findSpotById(int id) {
        for (List<ParkingSpot> list : spotsByType.values()) {
            for (ParkingSpot s : list) if (s.getSpotId() == id) return s;
        }
        return null;
    }

    public int getAvailableSpotsCount() {
        return spotsByType.values().stream().flatMap(Collection::stream).mapToInt(s -> s.isOccupied() ? 0 : 1).sum();
    }

    public List<ParkingSpot> getAvailableSpots() {
        List<ParkingSpot> res = new ArrayList<>();
        for (List<ParkingSpot> list : spotsByType.values()) {
            list.stream().filter(s -> !s.isOccupied()).sorted(Comparator.comparingInt(ParkingSpot::getSpotId)).forEach(res::add);
        }
        return res;
    }

    public List<ParkingSpot> getOccupiedSpots() {
        List<ParkingSpot> res = new ArrayList<>();
        for (List<ParkingSpot> list : spotsByType.values()) {
            list.stream().filter(ParkingSpot::isOccupied).sorted(Comparator.comparingInt(ParkingSpot::getSpotId)).forEach(res::add);
        }
        return res;
    }

    public Ticket searchActiveTicketByVehicle(String registrationNumber) {
        return activeTickets.values().stream()
                .filter(t -> t.getVehicle().getRegistrationNumber().equalsIgnoreCase(registrationNumber))
                .findFirst()
                .orElse(null);
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public Collection<Ticket> getAllTickets() {
        return Collections.unmodifiableCollection(allTickets.values());
    }
}