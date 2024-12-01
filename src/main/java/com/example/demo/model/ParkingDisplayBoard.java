package com.example.demo.model;

import com.example.demo.model.spots.*;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
//@Builder
public class ParkingDisplayBoard {

    private final List<CompactSpot> compactFreeSpots;
    private final List<HandicappedSpot> handicappedFreeSpots;
    private final List<ElectricSpot> electricFreeSpots;
    private final List<LargeSpot> largeFreeSpots;
    private final List<TwoWheelerSpot> twoWheelerFreeSpot;

    public ParkingDisplayBoard(){
        this.compactFreeSpots = new ArrayList<>();
        this.handicappedFreeSpots = new ArrayList<>();
        this.electricFreeSpots = new ArrayList<>();
        this.largeFreeSpots = new ArrayList<>();
        this.twoWheelerFreeSpot = new ArrayList<>();
    }

    public void addCompactFreeSpots(CompactSpot compactSpot){
        compactFreeSpots.add(compactSpot);
    }

    public void addHandicappedFreeSpots(HandicappedSpot handicappedSpot){
        handicappedFreeSpots.add(handicappedSpot);
    }

    public void addElectricFreeSpots(ElectricSpot electricSpot){
        electricFreeSpots.add(electricSpot);
    }

    public void addLargeFreeSpots(LargeSpot largeSpot){
        largeFreeSpots.add(largeSpot);
    }

    public void addTwoWheelerFreeSpots(TwoWheelerSpot twoWheelerSpot){
        twoWheelerFreeSpot.add(twoWheelerSpot);
    }

    public void showEmptySpotCount() {
        System.out.printf("\nAvailable Compact Parking Spots: %d" +
                          "\nAvailable Handicapped Parking Spots: %d" +
                          "\nAvailable Electric Parking Spots: %d" +
                          "\nAvailable Large Parking Spots: %d" +
                          "\nAvailable Two Wheeler Parking Spots: %d\n",
                          compactFreeSpots.size(),
                          handicappedFreeSpots.size(),
                          electricFreeSpots.size(),
                          largeFreeSpots.size(),
                          twoWheelerFreeSpot.size());
    }

    public void showEmptySpotNumber() {
        System.out.printf("\nAvailable Compact Parking Spots(%d): %s" +
                        "\nAvailable Handicapped Parking Spots(%d): %s" +
                        "\nAvailable Electric Parking Spots(%d): %s" +
                        "\nAvailable Large Parking Spots(%d): %s" +
                        "\nAvailable Two Wheeler Parking Spots(%d): %s\n",
                compactFreeSpots.size(), Arrays.toString(compactFreeSpots.stream().mapToInt(CompactSpot::getSpotNumber).toArray()),
                handicappedFreeSpots.size(), Arrays.toString(handicappedFreeSpots.stream().mapToInt(HandicappedSpot::getSpotNumber).toArray()),
                electricFreeSpots.size(), Arrays.toString(electricFreeSpots.stream().mapToInt(ElectricSpot::getSpotNumber).toArray()),
                largeFreeSpots.size(), Arrays.toString(largeFreeSpots.stream().mapToInt(LargeSpot::getSpotNumber).toArray()),
                twoWheelerFreeSpot.size(), Arrays.toString(twoWheelerFreeSpot.stream().mapToInt(TwoWheelerSpot::getSpotNumber).toArray()));
    }

}
