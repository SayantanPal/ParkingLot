package com.example.demo.model.spots;

import com.example.demo.model.ParkingSpotType;

public class TwoWheelerSpot extends ParkingSpot  {
    @Deprecated
    public TwoWheelerSpot(int spotNumber) {
        super(spotNumber, ParkingSpotType.TWO_WHEELER);
    }

    public TwoWheelerSpot() {
        super(ParkingSpotType.TWO_WHEELER);
    }
}
