package com.example.demo;

import com.example.demo.model.ParkingDisplayBoard;
import com.example.demo.model.spots.*;
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
    private int twoWheelerSpots;
    private int compactSpots;
    private int largeSpots;

    @Deprecated
    public ParkingLevel(int floor, int initialNumOfSpots) {
        this.floor = floor;
        this.parkingSpots = new ArrayList<>(initialNumOfSpots);
        this.initializeParkingSpot(initialNumOfSpots);
    }

    public ParkingLevel(int floor, int twoWheelerSpots, int compactSpots, int largeSpots) {
        this.floor = floor;
        this.parkingSpots = new ArrayList<>();
        this.twoWheelerSpots = twoWheelerSpots;
        this.compactSpots = compactSpots;
        this.largeSpots = largeSpots;
        this.createParkingSpots();
    }

    private void createParkingSpots(){
        for(int i = 1; i <= this.twoWheelerSpots; i++){
            ParkingSpot parkingSpot = new TwoWheelerSpot(i);
            parkingSpots.add(parkingSpot);
        }

        for(int i = 1; i <= this.compactSpots; i++){
            ParkingSpot parkingSpot = new CompactSpot(i);
            parkingSpots.add(parkingSpot);
        }

        for(int i = 1; i <= this.largeSpots; i++){
            ParkingSpot parkingSpot = new LargeSpot(i);
            parkingSpots.add(parkingSpot);
        }
    }

    @Deprecated
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
        }
        for (int i = numOfTwoWheelerSlots + 1; i <= numOfTwoWheelerSlots + numOfCompactSlots; i++) {
            parkingSpot = new CompactSpot(i);
            parkingSpots.add(parkingSpot);
        }
        for (int i = numOfTwoWheelerSlots + numOfCompactSlots + 1; i <= initialNumOfSpots; i++) {
            parkingSpot = new LargeSpot(i);
            parkingSpots.add(parkingSpot);
        }
    }

    // admin will use: future scope
    public void addParkingSpot(ParkingSpot parkingSpot){
        if(parkingSpots.stream().anyMatch(ps -> ps.getParkingSpotType().equals(parkingSpot.getParkingSpotType()) && ps.getSpotNumber() == parkingSpot.getSpotNumber()))
            throw new IllegalArgumentException("Parking spot number already exists for this " + parkingSpot.getParkingSpotType() + "parking spot type");
        else {
            parkingSpots.add(parkingSpot);
            if(parkingSpot instanceof TwoWheelerSpot){
                this.twoWheelerSpots++;
            } else if(parkingSpot instanceof CompactSpot){
                this.compactSpots++;
            } else if(parkingSpot instanceof LargeSpot){
                this.largeSpots++;
            }
        }
    }

    public synchronized ParkingSpot assignVehicleToSpot(Vehicle vehicle) {
        ParkingStrategy parkingStrategy = new DefaultParkingStrategy();
        Optional<ParkingSpot> parkingSpot = parkingStrategy.findParkingSpot(parkingSpots, vehicle);
        if(parkingSpot.isPresent()){
            parkingSpot.get().occupy(vehicle);
//            this.updateDisplayBoard(parkingSpot.get());
            return parkingSpot.get();
        }
        return null;
    }

    public synchronized boolean freeSpotFromVehicle(Vehicle vehicle) throws IllegalArgumentException {
        for (ParkingSpot spot : parkingSpots) {
            if (spot.checkIfAlreadyParked(vehicle)) {
                spot.vacate();
                return true;
            }
        }
        return false;
    }

    public void displayAvailability() {
        ParkingDisplayBoard.displayAvailability(this);
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
