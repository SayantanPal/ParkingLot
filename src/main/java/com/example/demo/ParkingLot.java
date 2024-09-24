package com.example.demo;

import com.example.demo.model.vehicles.Vehicle;
import com.example.demo.strategy.DefaultParkingStrategy;
import com.example.demo.strategy.ParkingStrategy;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

// parking lot/spot manager class to manage parking lots and parkingLevels
public class ParkingLot {
    private static ParkingLot instance;

    @Getter
    private final List<ParkingLevel> parkingLevels;

    // singleton pattern implementation to ensure only one instance of ParkingLot is created
    private ParkingLot() {
        parkingLevels = new ArrayList<>();
    }

    public static synchronized ParkingLot getInstance() {
        if (instance == null) {
            instance = new ParkingLot();
        }
        return instance;
    }

    // properly behaviour-driven mutator method instead of simply setter method
    public void addParkingLevel(ParkingLevel level) {
        parkingLevels.add(level);
    }

    public boolean parkVehicle(Vehicle vehicle) {
        ParkingStrategy parkingStrategy = new DefaultParkingStrategy();
        List<ParkingLevel> parkingLevels = parkingStrategy.findParkingLevel(this.parkingLevels);
        for(ParkingLevel parkingLevel: parkingLevels){
            if(parkingLevel.assignVehicleToSpot(vehicle)){
                 System.out.printf("%s(license plate number: %s) parked safely at floor: %d\n", vehicle.getType().getValue(), vehicle.getLicensePlate(), parkingLevel.getFloor());
                 return true;
            }
        }
        System.out.println("Could not find any level and slots to park your vehicle.");
        return false;
//        for (ParkingLevel parkingLevel : parkingLevels) {
//            if (parkingLevel.parkVehicle(vehicle)) {
//                System.out.println("Vehicle parked safely.");
//                return true;
//            }
//        }
//        System.out.println("Could not find any level and slots to park your vehicle.");
//        return false;
    }

    public boolean unparkVehicle(Vehicle vehicle) {
        for (ParkingLevel parkingLevel : parkingLevels) {
            if (parkingLevel.freeSpot(vehicle)) {
                System.out.printf("\n%s with License Number %s unparked safely.\n", vehicle.getType().getValue(), vehicle.getLicensePlate());
                return true;
            }
        }
        System.out.println("Could not find your vehicle parked in any slot of any level.");
        return false;
    }

    public void displayAvailability() {
        for (ParkingLevel parkingLevel : parkingLevels) {
            parkingLevel.displayAvailability();
        }
    }

    // future scope
    public void displayAvailability(ParkingLevel parkingLevel){
        parkingLevel.displayAvailability();
    }
}
