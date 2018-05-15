package com.ceibaParking.application.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ceibaParking.application.domain.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, String> {

}
