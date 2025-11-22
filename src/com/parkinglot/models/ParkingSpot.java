package src.com.parkinglot.models;

public class ParkingSpot {

    private final int spotId;
    private final SpotType spotType;
    private boolean occupied;
    private final double hourlyRate;

    public ParkingSpot(int spotId, SpotType spotType, double hourlyRate) {
        this.spotId = spotId;
        this.spotType = spotType;
        this.hourlyRate = hourlyRate;
        this.occupied = false;
    }

    public int getSpotId() {
        return spotId;
    }

    public SpotType getSpotType() {
        return spotType;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void occupy() {
        this.occupied = true;
    }

    public void free() {
        this.occupied = false;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    @Override
    public String toString() {
        return "ParkingSpot{" +
                "spotId=" + spotId +
                ", spotType=" + spotType +
                ", occupied=" + occupied +
                ", hourlyRate=" + hourlyRate +
                '}';
    }
}