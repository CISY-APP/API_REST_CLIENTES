package com.cisyapp.rest.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cisyapp.rest.modelo.Reserva;

public interface ReservaRepositorio extends JpaRepository<Reserva, Integer>{
	
	//Metodo para obtener la reserva por idreserva:
	@Query(value = "SELECT * FROM reserva WHERE idreserva = ?1", nativeQuery = true)
	Optional <Reserva> findById(@Param("idreserva") Integer idReserva );
	
	//Metodo para obtener la reserva por idusuario e idviaje:
	@Query(value = "SELECT * FROM reserva WHERE idusuariopasajero=?1 AND idviaje=?2 LIMIT 1", nativeQuery = true)
	Optional <Reserva> findByIdUsuarioYidViaje(@Param("idusuariopasajero") Integer idUsuario, @Param("idviaje") Integer idViaje);
	
	//Metodo para obtener las reservas por idusuario:
	@Query(value = "SELECT * FROM reserva WHERE idusuariopasajero=?1", nativeQuery = true)
	Optional <List<Reserva>> obtenerReservasUsuarioByIdUsuario(@Param("idusuariopasajero") Integer idUsuario);
	
	//Metodo para obtener las reservas por idviaje:
	@Query(value = "SELECT * FROM reserva WHERE idviaje=?1", nativeQuery = true)
	Optional <List<Reserva>> obtenerReservasUsuarioByIdViaje(@Param("idviaje") Integer idviaje);
}
