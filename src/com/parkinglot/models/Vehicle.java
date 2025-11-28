package com.parkinglot.models;

public class Vehicle {
    private final String registrationNumber;
    private final SpotType vehicleType;
    private final String color;

    public Vehicle(String registrationNumber, SpotType vehicleType, String color) {
        this.registrationNumber = registrationNumber;
        this.vehicleType = vehicleType;
        this.color = color;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public SpotType getVehicleType() {
        return vehicleType;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "registrationNumber='" + registrationNumber + '\'' +
                ", vehicleType=" + vehicleType +
                ", color='" + color + '\'' +
                '}';
    }
}