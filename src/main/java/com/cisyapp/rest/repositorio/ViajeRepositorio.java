package com.cisyapp.rest.repositorio;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cisyapp.rest.modelo.Viaje;

public interface ViajeRepositorio extends JpaRepository<Viaje, Integer>{

	//Metodo para consultar los viajes de ese dia:
	@Query(value = "SELECT * FROM viaje WHERE fechasalida >=  ?1 AND fechasalida <= ?2 AND precio <= ?3 AND numplazasdisponibles >=1 AND idusuarioconductor != ?4 ORDER BY fechasalida ASC", nativeQuery = true)
	List<Viaje> mostrarViajesDelDia(@Param("fechasalida") String fechaIni, @Param("fechasalida") String fechaFin, @Param("precio") BigDecimal precioMax, @Param("idusuarioconductor") int id);
	
}