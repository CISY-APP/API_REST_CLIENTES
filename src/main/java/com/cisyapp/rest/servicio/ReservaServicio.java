package com.cisyapp.rest.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cisyapp.rest.modelo.Reserva;
import com.cisyapp.rest.repositorio.ReservaRepositorio;

@Service
public class ReservaServicio {
	
	@Autowired
	private ReservaRepositorio ReservaRepositorio;
	
	// Metodo utilizado para registrar una nueva reserva en la cuenta de un usuario:
	public Reserva registraReserva(Reserva reserva) {
		return ReservaRepositorio.save(reserva);
	}
	
	// Metodo que muestra una reserva por id:
	public Optional <Reserva> muestraReservaPorIdReserva(Integer idReserva) {
		return ReservaRepositorio.findById(idReserva);
	}

	// Metodo que muestra una reserva por idusuario e idviaje:
	public Optional <Reserva> muestraReservaPorIdUsuarioYidViaje(Integer idUsuario, Integer idViaje) {
		return ReservaRepositorio.findByIdUsuarioYidViaje(idUsuario,idViaje);
	}
	
	// Metodo que muestra las reservas por idusuario:
	public Optional <List<Reserva>> muestraReservasPorIdUsuario(Integer idUsuario) {
		return ReservaRepositorio.obtenerReservasUsuarioByIdUsuario(idUsuario);
	}
	
	// Metodo que muestra las reservas por idviaje:
	public Optional <List<Reserva>> muestraReservasPorIdViaje(Integer idViaje) {
		return ReservaRepositorio.obtenerReservasUsuarioByIdViaje(idViaje);
	}
	
	// Metodo utilizado para eliminar una reserva por idUsuario e idViaje:
	public Integer eliminarReservaIdViajeIdPasajero(Integer idViaje, Integer idPasajero) {
		return ReservaRepositorio.eliminarReservaIdViajeIdPasajero(idViaje,idPasajero);
	}
}