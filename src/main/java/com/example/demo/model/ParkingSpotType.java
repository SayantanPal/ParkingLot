package com.example.demo.model;

import lombok.Getter;

@Getter
public enum ParkingSpotType {
    COMPACT("Compact size vehicle"),
    ELECTRIC("Any Electric driven Vehicle"),
    LARGE("Large sized vehicle"),
    HANDICAPPED("Handicapped type vehicle"),
    TWO_WHEELER("Any Two Wheeler vehicle");

    private final String value;

    ParkingSpotType(String value) {
        this.value = value;
    }
}
