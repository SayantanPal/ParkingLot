package com.example.demo.model.vehicles;

import com.example.demo.model.spots.CompactSpot;
import com.example.demo.model.spots.LargeSpot;
import com.example.demo.model.spots.ParkingSpot;
import com.example.demo.model.VehicleType;
import com.example.demo.model.spots.TwoWheelerSpot;

public class Motorcycle extends Vehicle {
    public Motorcycle(String licensePlate) {
        super(licensePlate, VehicleType.MOTORCYCLE);
    }

    @Override
    public boolean canFitInSpot(ParkingSpot parkingSpot){
//        return true;
        return (parkingSpot instanceof TwoWheelerSpot) || (parkingSpot instanceof CompactSpot) || (parkingSpot instanceof LargeSpot);
    }

}
