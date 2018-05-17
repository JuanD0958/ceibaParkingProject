package com.ceiba.parking.application.repository.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ceiba.parking.application.domain.Vehicle;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, String> {
	
	 @Query("select u from Vehicle u where u.typeVehicle = 1")
	  List<Vehicle> findAllCars();
	 
	 @Query("select u from Vehicle u where u.typeVehicle = 2")
	 List<Vehicle> findAllMotorcycles();
	 
}
