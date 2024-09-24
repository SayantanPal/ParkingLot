package com.example.demo.model;

import lombok.Getter;

@Getter
public enum VehicleType {
    CAR("Car"),
    MOTORCYCLE("Motorcycle"),
    BUS("Bus"),
    TRUCK("Truck");

    private final String value;

    VehicleType(String value) {
        this.value = value;
    }
}