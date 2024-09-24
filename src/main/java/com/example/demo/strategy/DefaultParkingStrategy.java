package com.example.demo.strategy;

import com.example.demo.ParkingLevel;
import com.example.demo.model.spots.ParkingSpot;
import com.example.demo.model.vehicles.Vehicle;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DefaultParkingStrategy implements ParkingStrategy {

    @Override
    public Optional<ParkingSpot> findParkingSpot(List<ParkingSpot> parkings, Vehicle vehicle){
        return parkings.stream()
                .filter(parkingSpot -> parkingSpot.checkIfParkingPossible(vehicle) && vehicle.canFitInSpot(parkingSpot))
                .findFirst();
//        for(ParkingSpot parkingSpot: parkings){
//            if(parkingSpot.checkIfParkingPossible(vehicle) && vehicle.canFitInSpot(parkingSpot)){
//                return Optional.of(parkingSpot);
//            }
//        }
//        return Optional.empty();
    }

public List<ParkingLevel> findParkingLevel(List<ParkingLevel> parkingLevels) {
    return parkingLevels.stream()
        .sorted(Comparator.comparingInt(ParkingLevel::getFloor))
        .filter(parkingLevel -> !parkingLevel.areAllParkingSpotsFull())
        .collect(Collectors.toList());
    }
}
