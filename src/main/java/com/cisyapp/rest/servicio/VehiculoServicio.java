package com.cisyapp.rest.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cisyapp.rest.modelo.Vehiculo;
import com.cisyapp.rest.repositorio.VehiculoRepositorio;


@Service
public class VehiculoServicio{
	
	@Autowired
	private VehiculoRepositorio VehiculoRepositorio;

	
	// Metodo utilizado para registrar un nuevo cliente.
	public Vehiculo registrarVehiculo(Vehiculo vehiculo) {
		return VehiculoRepositorio.save(vehiculo);
	}

	
}
