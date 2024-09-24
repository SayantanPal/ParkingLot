package com.example.demo;

import com.example.demo.model.vehicles.Car;
import com.example.demo.model.vehicles.Motorcycle;
import com.example.demo.model.vehicles.Truck;
import com.example.demo.model.vehicles.Vehicle;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ParkingLotApplication {

	public static void main(String[] args) {
//		SpringApplication.run(ParkingLotApplication.class, args);

		ParkingLot parkingLot = ParkingLot.getInstance();
		parkingLot.addParkingLevel(new ParkingLevel(2, 20));
		parkingLot.addParkingLevel(new ParkingLevel(1, 10));

		Vehicle car = new Car("ABC123");
		Vehicle truck = new Truck("XYZ789");
		Vehicle motorcycle = new Motorcycle("M1234");

		// Park vehicles
		parkingLot.parkVehicle(car);
		parkingLot.parkVehicle(truck);
		parkingLot.parkVehicle(motorcycle);
		parkingLot.parkVehicle(truck);

		// Display availability
		parkingLot.displayAvailability();

		// Unpark vehicle
		parkingLot.unparkVehicle(motorcycle);

		// Display updated availability
		parkingLot.displayAvailability();
	}

}
