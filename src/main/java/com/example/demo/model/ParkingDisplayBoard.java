package com.example.demo.model;

import com.example.demo.ParkingLevel;
import com.example.demo.ParkingLot;
import com.example.demo.model.spots.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
//@Builder
public class ParkingDisplayBoard {

    public static void displayAvailability(ParkingLevel parkingLevel) {
        List<ParkingSpot> parkingSpots = parkingLevel.getParkingSpots();

        List<ParkingSpot> compactSpots = parkingSpots.stream().filter(parkingSpot -> parkingSpot.getParkingSpotType().equals(ParkingSpotType.COMPACT)).toList();
        List<ParkingSpot> handicappedSpots = parkingSpots.stream().filter(parkingSpot -> parkingSpot.getParkingSpotType().equals(ParkingSpotType.HANDICAPPED)).toList();
        List<ParkingSpot> electricSpots = parkingSpots.stream().filter(parkingSpot -> parkingSpot.getParkingSpotType().equals(ParkingSpotType.ELECTRIC)).toList();
        List<ParkingSpot> twoWheelerSpots = parkingSpots.stream().filter(parkingSpot -> parkingSpot.getParkingSpotType().equals(ParkingSpotType.TWO_WHEELER)).toList();
        List<ParkingSpot> largeSpots = parkingSpots.stream().filter(parkingSpot -> parkingSpot.getParkingSpotType().equals(ParkingSpotType.LARGE)).toList();

        List<ParkingSpot> freeCompactSpots = compactSpots.stream().filter(ParkingSpot::isEmpty).toList();
        List<ParkingSpot> freeHandicappedSpots = handicappedSpots.stream().filter(ParkingSpot::isEmpty).toList();
        List<ParkingSpot> freeElectricSpots = electricSpots.stream().filter(ParkingSpot::isEmpty).toList();
        List<ParkingSpot> freeTwoWheelerSpots = twoWheelerSpots.stream().filter(ParkingSpot::isEmpty).toList();
        List<ParkingSpot> freeLargeSpots = largeSpots.stream().filter(ParkingSpot::isEmpty).toList();

//        List<ParkingSpot> occupiedCompactSpots = compactSpots.stream().filter(parkingSpot -> !parkingSpot.isEmpty()).toList();
//        List<ParkingSpot> occupiedHandicappedSpots = handicappedSpots.stream().filter(parkingSpot -> !parkingSpot.isEmpty()).toList();
//        List<ParkingSpot> occupiedElectricSpots = electricSpots.stream().filter(parkingSpot -> !parkingSpot.isEmpty()).toList();
//        List<ParkingSpot> occupiedTwoWheelerSpots = twoWheelerSpots.stream().filter(parkingSpot -> !parkingSpot.isEmpty()).toList();
//        List<ParkingSpot> occupiedLargeSpots = largeSpots.stream().filter(parkingSpot -> !parkingSpot.isEmpty()).toList();



        System.out.printf("\nAvailable Compact Parking Spots(%d): %s" +
                        "\nAvailable Handicapped Parking Spots(%d): %s" +
                        "\nAvailable Electric Parking Spots(%d): %s" +
                        "\nAvailable Large Parking Spots(%d): %s" +
                        "\nAvailable Two Wheeler Parking Spots(%d): %s\n",
                freeCompactSpots.size(), printArrayForParkingSpots(freeCompactSpots),
                freeHandicappedSpots.size(), printArrayForParkingSpots(freeHandicappedSpots),
                freeElectricSpots.size(), printArrayForParkingSpots(freeElectricSpots),
                freeTwoWheelerSpots.size(), printArrayForParkingSpots(freeTwoWheelerSpots),
                freeLargeSpots.size(), printArrayForParkingSpots(freeLargeSpots));
    }

    public static void displayAvailability(ParkingLot parkingLot) {
        for (ParkingLevel parkingLevel : parkingLot.getParkingLevels()) {
            System.out.printf("\nParking Level of floor %d Availability:\n", parkingLevel.getFloor());
            displayAvailability(parkingLevel);
        }
    }

    private static String printArrayForParkingSpots(List<ParkingSpot> list){
        return Arrays.toString(list.stream().mapToInt(ParkingSpot::getSpotNumber).toArray());
    }
}
