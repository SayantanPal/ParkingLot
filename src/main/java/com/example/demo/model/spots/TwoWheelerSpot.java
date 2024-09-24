package com.example.demo.model.spots;

import com.example.demo.model.ParkingSpotType;

public class TwoWheelerSpot extends ParkingSpot  {
    public TwoWheelerSpot(int spotNumber) {
        super(spotNumber, ParkingSpotType.TWO_WHEELER);
    }
}
