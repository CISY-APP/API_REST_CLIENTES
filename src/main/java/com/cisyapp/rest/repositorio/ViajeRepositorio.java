package com.cisyapp.rest.repositorio;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cisyapp.rest.modelo.Usuario;
import com.cisyapp.rest.modelo.Viaje;

public interface ViajeRepositorio extends JpaRepository<Viaje, Integer>{

	//Metodo para consultar los viajes sin hora
	@Query(value = "SELECT * FROM viaje WHERE origen =  ?1 AND destino = ?2 AND fecha = '?3'", nativeQuery = true)
	List<Viaje> mostrarViajesSinHora(@Param("origen") String origen, @Param("destino") String destino, @Param("fecha") Date fecha );
	
	
	//Metodo para consultar los viajes con hora
	@Query(value = "SELECT * FROM viaje WHERE origen =  ?1 AND destino = ?2 AND fecha = ?3 AND hora = '?4'", nativeQuery = true)
	List<Viaje> mostrarViajesConHora(@Param("origen") String origen, @Param("destino") String destino, @Param("fecha") Date fecha, @Param("hora") Date hora );
}