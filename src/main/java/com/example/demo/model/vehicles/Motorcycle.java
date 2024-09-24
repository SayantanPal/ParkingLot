package com.example.demo.model.vehicles;

import com.example.demo.model.spots.ParkingSpot;
import com.example.demo.model.VehicleType;

public class Motorcycle extends Vehicle {
    public Motorcycle(String licensePlate) {
        super(licensePlate, VehicleType.MOTORCYCLE);
    }

    @Override
    public boolean canFitInSpot(ParkingSpot parkingSpot){
        return true;
    }
}
