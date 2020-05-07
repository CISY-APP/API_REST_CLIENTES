package com.cisyapp.rest.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cisyapp.rest.modelo.Vehiculo;


public interface VehiculoRepositorio extends JpaRepository<Vehiculo, Integer>{
	
	
}