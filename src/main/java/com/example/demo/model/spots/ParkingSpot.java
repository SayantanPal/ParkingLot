package com.example.demo.model.spots;

import com.example.demo.model.ParkingSpotType;
import com.example.demo.model.vehicles.Vehicle;
import lombok.Getter;

@Getter
public abstract class ParkingSpot {
    private final int spotNumber;
    private final ParkingSpotType parkingSpotType;
    private Vehicle parkedVehicle;

    public ParkingSpot(int spotNumber, ParkingSpotType parkingSpotType) {
        this.spotNumber = spotNumber;
        this.parkingSpotType = parkingSpotType;
    }

    public boolean isEmpty() {
        return this.parkedVehicle == null;
    }

    public boolean hasAnyVehicleParked(){
        return this.parkedVehicle != null;
    }

//    public boolean withTypeMatch(Vehicle vehicle) {
//        return this.vehicleType == vehicle.getType();
//    }

    public boolean hasSameVehicleParked(Vehicle vehicle){
        return this.parkedVehicle.equals(vehicle);
    }

    public boolean checkIfParkingPossible(Vehicle toBeParkedvehicle) {
//        if(this.isAvailable()){
//            if(this.withTypeMatch(toBeParkedvehicle)){
//                return true;
//            }
//            throw new IllegalArgumentException("Not eligible for this vehicle type.");
//        }
//        throw new IllegalArgumentException("Spot already occupied with other vehicle.");
        return this.isEmpty();
//                && this.withTypeMatch(toBeParkedvehicle);
    }

    public boolean checkIfAlreadyParked(Vehicle toBeUnparkedVehicle) {
//        if(this.hasAnyVehicleParked()){
//            if(this.hasSameVehicleParked(toBeUnparkedVehicle)){
//                return true;
//            }
//            throw new IllegalArgumentException("Currently some other vehicle is already parked here.");
//        }
//        throw new IllegalArgumentException("Spot is vacant. No vehicle is parked here.");
            return this.hasAnyVehicleParked() && this.hasSameVehicleParked(toBeUnparkedVehicle);
    }

    // controlled behaviour driven setter method
    public synchronized void occupy(Vehicle vehicle) {
        this.parkedVehicle = vehicle;
    }

    public synchronized void vacate() {
        this.parkedVehicle = null;
    }
}
