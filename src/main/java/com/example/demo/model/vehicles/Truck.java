package com.example.demo.model.vehicles;

import com.example.demo.model.spots.LargeSpot;
import com.example.demo.model.spots.ParkingSpot;
import com.example.demo.model.VehicleType;

public class Truck extends Vehicle {
    public Truck(String licensePlate) {
        super(licensePlate, VehicleType.TRUCK);
    }

    @Override
    public boolean canFitInSpot(ParkingSpot parkingSpot){
        return (parkingSpot instanceof LargeSpot);
    }
}
