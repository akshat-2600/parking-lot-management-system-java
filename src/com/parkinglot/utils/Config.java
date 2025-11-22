package src.com.parkinglot.utils;

public final class Config {
    private Config() {}

    public static final double CAR_RATE = 20.0;
    public static final double BIKE_RATE = 10.0;
    public static final double TRUCK_RATE = 40.0;

    public static final long MINIMUM_CHARGE_MINUTES = 1; // minimum 1 minute (or 60 for min hour)
    public static final double MINIMUM_HOURS = 1.0; // charge at least 1 hour
}