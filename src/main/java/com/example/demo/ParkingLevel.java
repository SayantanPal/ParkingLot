package com.example.demo;

import com.example.demo.model.spots.CompactSpot;
import com.example.demo.model.spots.LargeSpot;
import com.example.demo.model.spots.ParkingSpot;
import com.example.demo.model.spots.TwoWheelerSpot;
import com.example.demo.model.vehicles.Vehicle;
import com.example.demo.strategy.DefaultParkingStrategy;
import com.example.demo.strategy.ParkingStrategy;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
public class ParkingLevel {
    private final int floor; // each level corresponds to a single floor in the parking lot
    private final List<ParkingSpot> parkingSpots;

    public ParkingLevel(int floor, int initialNumOfSpots) {
        this.floor = floor;
        parkingSpots = new ArrayList<>(initialNumOfSpots);

        // Assign spots in ration of 5:4:1 for bikes, cars and trucks
        double spotsForTwoWheeler = 0.5;
        double spotsForCompact = 0.4;

        int numOfTwoWheelerSlots = (int) (initialNumOfSpots * spotsForTwoWheeler);
        int numOfCompactSlots = (int) (initialNumOfSpots * spotsForCompact);

        for (int i = 1; i <= numOfTwoWheelerSlots; i++) {
            parkingSpots.add(new TwoWheelerSpot(i));
        }
        for (int i = numOfTwoWheelerSlots + 1; i <= numOfTwoWheelerSlots + numOfCompactSlots; i++) {
            parkingSpots.add(new CompactSpot(i));
        }
        for (int i = numOfTwoWheelerSlots + numOfCompactSlots + 1; i <= initialNumOfSpots; i++) {
            parkingSpots.add(new LargeSpot(i));
        }
    }

    // admin will use: future scope
    public void addParkingSpot(ParkingSpot parkingSpot){
        parkingSpots.add(parkingSpot);
    }

    public synchronized boolean assignVehicleToSpot(Vehicle vehicle) {
        ParkingStrategy parkingStrategy = new DefaultParkingStrategy();
        Optional<ParkingSpot> parkingSpot = parkingStrategy.findParkingSpot(parkingSpots, vehicle);
//        for (ParkingSpot spot : parkingSpots) {
//            if (spot.checkIfParkingPossible(vehicle)) {
//                spot.occupy(vehicle);
//                return true;
//            }
//        }
        if(parkingSpot.isPresent()){
            parkingSpot.get().occupy(vehicle);
            return true;
        }
        return false;
    }

    public synchronized boolean freeSpot(Vehicle vehicle) throws IllegalArgumentException {
        for (ParkingSpot spot : parkingSpots) {
            if (spot.checkIfAlreadyParked(vehicle)) {
                spot.vacate();
                return true;
            }
        }
        return false;
    }

    public void displayAvailability() {
        System.out.printf("\nParking Level of floor %d Availability:\n", this.floor);
        for (ParkingSpot spot : parkingSpots) {
            System.out.println("Parking Spot " + spot.getSpotNumber() + ": " + (spot.isEmpty() ? ("Available For " + spot.getParkingSpotType().getValue())  : ("Occupied By " + spot.getParkedVehicle().getType().getValue())));
        }
    }

    public boolean areAllParkingSpotsFull() {
        for (ParkingSpot spot : parkingSpots) {
            if (spot.isEmpty()) {
                return false;
            }
        }
        return true;
    }
}
