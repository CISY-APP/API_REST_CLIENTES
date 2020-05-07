package com.cisyapp.rest.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cisyapp.rest.modelo.Viaje;

public interface ViajeRepositorio extends JpaRepository<Viaje, Integer>{

	//Consulta para buscar ventas entre fecha y fecha
	@Query(value = "SELECT * FROM viajes WHERE origen = ?1 AND destino = ?2 AND fecha = ?3 AND hora = ?4", nativeQuery = true)
	List<Viaje> buscarViajeReservar(@Param("origen") String origen, @Param("destino") String destino, @Param("fecha") String fecha , @Param("hora") String hora  );

}
