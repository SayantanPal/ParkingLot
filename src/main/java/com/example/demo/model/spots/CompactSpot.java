package com.example.demo.model.spots;

import com.example.demo.model.ParkingSpotType;

public class CompactSpot extends ParkingSpot  {
    public CompactSpot(int spotNumber) {
        super(spotNumber, ParkingSpotType.COMPACT);
    }
}
