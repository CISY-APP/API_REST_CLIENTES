package com.cisyapp.rest.repositorio;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cisyapp.rest.modelo.Vehiculo;


public interface VehiculoRepositorio extends JpaRepository<Vehiculo, Integer>{
	
	//Metodo personalizado para contar el numero de vehiculos que tiene asociado un usuario
	@Query(
		value = "SELECT COUNT(idvehiculo) FROM vehiculo where idusuario = :id",
		nativeQuery = true)
		Integer cuentaVehiculosPorIdUsuario(@Param("id") Integer auxIdUsuario);
		
	//Metodo personalizado para consultar el numero de vehiculos que tiene asociado un usuario
	@Query(
		value = "SELECT * FROM vehiculo where idusuario = :id",
		nativeQuery = true)
		List<Vehiculo> consultaVehiculosPorIdUsuario(@Param("id") Integer auxIdUsuario);
	
		
	//Metodo encargado de consultar vehiculos por matricula
	Optional<Vehiculo> findBymatricula(String matricula);
	
}