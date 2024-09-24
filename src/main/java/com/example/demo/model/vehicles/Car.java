package com.example.demo.model.vehicles;

import com.example.demo.model.spots.CompactSpot;
import com.example.demo.model.spots.LargeSpot;
import com.example.demo.model.spots.ParkingSpot;
import com.example.demo.model.VehicleType;

public class Car extends Vehicle {
    public Car(String licensePlate) {
        super(licensePlate, VehicleType.CAR);
    }

    @Override
    public boolean canFitInSpot(ParkingSpot parkingSpot){
        return (parkingSpot instanceof LargeSpot) || (parkingSpot instanceof CompactSpot);
    }
}
