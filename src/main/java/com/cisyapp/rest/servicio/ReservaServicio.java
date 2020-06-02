package com.cisyapp.rest.servicio;

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

}