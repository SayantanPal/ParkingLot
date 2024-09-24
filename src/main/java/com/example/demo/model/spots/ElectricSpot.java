package com.example.demo.model.spots;

import com.example.demo.model.ParkingSpotType;

// future scope
public class ElectricSpot extends ParkingSpot  {
    public ElectricSpot(int spotNumber) {
        super(spotNumber, ParkingSpotType.ELECTRIC);
    }
}
