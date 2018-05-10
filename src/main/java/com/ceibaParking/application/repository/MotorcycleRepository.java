package com.ceibaParking.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ceibaParking.application.domain.Motorcycle;

@Repository
public interface MotorcycleRepository extends JpaRepository<Motorcycle, String>{

}
