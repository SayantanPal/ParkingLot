package com.example.demo.model.spots;

import com.example.demo.model.ParkingSpotType;

// future scope
public class HandicappedSpot extends ParkingSpot  {
    public HandicappedSpot(int spotNumber) {
        super(spotNumber, ParkingSpotType.HANDICAPPED);
    }
}
