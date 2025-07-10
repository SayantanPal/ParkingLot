package com.example.demo;

import com.example.demo.model.vehicles.Car;
import com.example.demo.model.vehicles.Motorcycle;
import com.example.demo.model.vehicles.Truck;
import com.example.demo.model.vehicles.Vehicle;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Driver class
@SpringBootApplication
public class ParkingLotApplication {

	public static void main(String[] args) {
//		SpringApplication.run(ParkingLotApplication.class, args);

		ParkingLot parkingLot = ParkingLot.getInstance();
//		parkingLot.addParkingLevel(new ParkingLevel(2, 20));
//		parkingLot.addParkingLevel(new ParkingLevel(1, 10));

		// Admin
		parkingLot.addParkingLevel(new ParkingLevel(2, 5,4, 1));
		parkingLot.addParkingLevel(new ParkingLevel(1, 5, 4, 1));

		// Admin/User
		parkingLot.displayAvailability();

		Vehicle car = new Car("ABC123");
		Vehicle truck1 = new Truck("XYZ789");
		Vehicle truck2 = new Truck("XYZ123");
		Vehicle motorcycle = new Motorcycle("M1234");

		// Park vehicles
		parkingLot.parkVehicle(car);
		parkingLot.parkVehicle(new Truck("XYZ789"));//parkingLot.parkVehicle("Truck", "XYZ789")
		parkingLot.parkVehicle(motorcycle);
		parkingLot.parkVehicle(truck2);

		// Display availability
		parkingLot.displayAvailability();

		// Unpark vehicle
		parkingLot.unparkVehicle(motorcycle);

		// Display updated availability
		parkingLot.displayAvailability();
	}

}
