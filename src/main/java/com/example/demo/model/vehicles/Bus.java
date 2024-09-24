package com.example.demo.model.vehicles;

import com.example.demo.model.VehicleType;
import com.example.demo.model.spots.LargeSpot;
import com.example.demo.model.spots.ParkingSpot;

// future scope
public class Bus extends Vehicle{
    public Bus(String licensePlate) {
        super(licensePlate, VehicleType.BUS);
    }

    @Override
    public boolean canFitInSpot(ParkingSpot parkingSpot){
        return (parkingSpot instanceof LargeSpot);
    }
}
