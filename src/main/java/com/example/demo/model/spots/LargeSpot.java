package com.example.demo.model.spots;

import com.example.demo.model.ParkingSpotType;

public class LargeSpot extends ParkingSpot {
    @Deprecated
    public LargeSpot(int spotNumber) {
        super(spotNumber, ParkingSpotType.LARGE);
    }

    public LargeSpot() {
        super(ParkingSpotType.LARGE);
    }
}
