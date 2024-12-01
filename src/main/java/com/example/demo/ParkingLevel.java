package com.example.demo;

import com.example.demo.model.ParkingDisplayBoard;
import com.example.demo.model.ParkingSpotType;
import com.example.demo.model.spots.*;
import com.example.demo.model.vehicles.Vehicle;
import com.example.demo.strategy.DefaultParkingStrategy;
import com.example.demo.strategy.ParkingStrategy;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
public class ParkingLevel {
    private final int floor; // each level corresponds to a single floor in the parking lot
    private final List<ParkingSpot> parkingSpots;
    private ParkingDisplayBoard parkingDisplayBoard;

    public ParkingLevel(int floor, int initialNumOfSpots) {
        this.floor = floor;
        this.parkingSpots = new ArrayList<>(initialNumOfSpots);
        this.initializeParkingSpot(initialNumOfSpots);
    }

    private void initializeParkingSpot(int initialNumOfSpots) {
        // Assign spots in ration of 5:4:1 for bikes, cars and trucks
        double spotsForTwoWheeler = 0.5;
        double spotsForCompact = 0.4;

        int numOfTwoWheelerSlots = (int) (initialNumOfSpots * spotsForTwoWheeler);
        int numOfCompactSlots = (int) (initialNumOfSpots * spotsForCompact);

        ParkingSpot parkingSpot;
        for (int i = 1; i <= numOfTwoWheelerSlots; i++) {
            parkingSpot = new TwoWheelerSpot(i);
            parkingSpots.add(parkingSpot);
            this.updateDisplayBoard(parkingSpot);
        }
        for (int i = numOfTwoWheelerSlots + 1; i <= numOfTwoWheelerSlots + numOfCompactSlots; i++) {
            parkingSpot = new CompactSpot(i);
            parkingSpots.add(parkingSpot);
            this.updateDisplayBoard(parkingSpot);
        }
        for (int i = numOfTwoWheelerSlots + numOfCompactSlots + 1; i <= initialNumOfSpots; i++) {
            parkingSpot = new LargeSpot(i);
            parkingSpots.add(parkingSpot);
            this.updateDisplayBoard(parkingSpot);
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
            this.updateDisplayBoard(parkingSpot.get());
            return true;
        }
        return false;
    }

    public synchronized boolean freeSpotFromVehicle(Vehicle vehicle) throws IllegalArgumentException {
        for (ParkingSpot spot : parkingSpots) {
            if (spot.checkIfAlreadyParked(vehicle)) {
                spot.vacate();
                this.updateDisplayBoard(spot);
                return true;
            }
        }
        return false;
    }

//    private void updateDisplayBoard(){
//        // update the display board here
//        ParkingDisplayBoard parkingDisplayBoard = ParkingDisplayBoard.builder()
//                .compactFreeSpots(parkingSpots.stream().filter(p -> p instanceof CompactSpot && p.isEmpty()).map(p -> (CompactSpot)p).collect(Collectors.toList()))
//                .electricFreeSpots(parkingSpots.stream().filter(p -> p instanceof ElectricSpot && p.isEmpty()).map(p -> (ElectricSpot)p).collect(Collectors.toList()))
//                .largeFreeSpots(parkingSpots.stream().filter(p -> p instanceof LargeSpot && p.isEmpty()).map(p -> (LargeSpot)p).collect(Collectors.toList()))
//                .handicappedFreeSpots(parkingSpots.stream().filter(p -> p instanceof HandicappedSpot && p.isEmpty()).map(p -> (HandicappedSpot)p).collect(Collectors.toList()))
//                .twoWheelerFreeSpot(parkingSpots.stream().filter(p -> p instanceof TwoWheelerSpot && p.isEmpty()).map(p -> (TwoWheelerSpot)p).collect(Collectors.toList()))
//                .build();
//
//    }

    private void updateDisplayBoard(ParkingSpot parkingSpot) {
        this.parkingDisplayBoard = new ParkingDisplayBoard();
        if(parkingSpot.isEmpty()) {
            if(parkingSpot instanceof CompactSpot) {
                this.parkingDisplayBoard.addCompactFreeSpots((CompactSpot) parkingSpot);
            } else if (parkingSpot instanceof HandicappedSpot) {
                this.parkingDisplayBoard.addHandicappedFreeSpots((HandicappedSpot) parkingSpot);
            } else if (parkingSpot instanceof LargeSpot) {
                this.parkingDisplayBoard.addLargeFreeSpots((LargeSpot) parkingSpot);
            } else if (parkingSpot instanceof ElectricSpot) {
                this.parkingDisplayBoard.addElectricFreeSpots((ElectricSpot) parkingSpot);
            } else if (parkingSpot instanceof TwoWheelerSpot) {
                this.parkingDisplayBoard.addTwoWheelerFreeSpots((TwoWheelerSpot) parkingSpot);
            }
        }
    }

// Setter Injection
//    @Autowired
//    public void displayAvailability(ParkingDisplayBoard parkingDisplayBoard) {
    public void displayAvailability() {
        System.out.printf("\nParking Level of floor %d Availability:\n", this.floor);
//        for (ParkingSpot spot : parkingSpots) {
//            System.out.println("Parking Spot " + spot.getSpotNumber() + ": " + (spot.isEmpty() ? ("Available For " + spot.getParkingSpotType().getValue())  : ("Occupied By " + spot.getParkedVehicle().getType().getValue())));
//        }
        ParkingDisplayBoard parkingDisplayBoard = new ParkingDisplayBoard();
        parkingDisplayBoard.showEmptySpotNumber();
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
