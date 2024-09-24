package com.example.demo.model.vehicles;

import com.example.demo.model.spots.ParkingSpot;
import com.example.demo.model.VehicleType;
import lombok.Getter;

@Getter
public abstract class Vehicle {
    protected String licensePlate;
    protected VehicleType type;

    public Vehicle(String licensePlate, VehicleType type) {
        this.licensePlate = licensePlate;
        this.type = type;
    }

    public abstract boolean canFitInSpot(ParkingSpot parkingSpot);
}
