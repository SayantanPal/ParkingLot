package com.example.demo.strategy;

import com.example.demo.ParkingLevel;
import com.example.demo.model.spots.ParkingSpot;
import com.example.demo.model.vehicles.Vehicle;

import java.util.List;
import java.util.Optional;

public interface ParkingStrategy {
    Optional<ParkingSpot> findParkingSpot(List<ParkingSpot> parkings, Vehicle vehicle);
    List<ParkingLevel> findParkingLevel(List<ParkingLevel> parkingLevels);
}
