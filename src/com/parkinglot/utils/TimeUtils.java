package src.com.parkinglot.utils;

import java.time.Duration;
import java.time.LocalDateTime;

public final class TimeUtils {
    private TimeUtils(){}

    public static long minutesBetween(LocalDateTime start, LocalDateTime end) {
        return Math.max(0, Duration.between(start, end).toMinutes());
    }

    /** rounds up minutes to full hours */
    public static double hoursRoundedUp(long minutes) {
        if (minutes <= 0) return Config.MINIMUM_HOURS;
        return Math.ceil(minutes / 60.0);
    }
}