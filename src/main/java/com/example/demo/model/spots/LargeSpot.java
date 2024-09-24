package com.example.demo.model.spots;

import com.example.demo.model.ParkingSpotType;

public class LargeSpot extends ParkingSpot {

    public LargeSpot(int spotNumber) {
        super(spotNumber, ParkingSpotType.LARGE);
    }
}
