package com.cisyapp.rest.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cisyapp.rest.modelo.Viaje;
import com.cisyapp.rest.repositorio.ViajeRepositorio;

@Service
public class ViajeServicio {
	
	@Autowired
	private ViajeRepositorio ViajeRepositorio;
	
	// Metodo utilizado para registrar un nuevo viaje en la cuenta de un usuario.
	public Viaje registraViaje(Viaje viaje) {
		return ViajeRepositorio.save(viaje);
	}

	/*Este metodo es utilizado para devolver los viajes que cumplan con unos requisitos, estos requisitos son:
	 * - Origen
	 * - Destino
	 * - Fecha
	 *  -Hora
	 */
	public List<Viaje> consultaViajesReservar(String origen, String destino, String fecha, String hora) {
		return ViajeRepositorio.buscarViajeReservar(origen, destino, fecha, hora);
	}

}
