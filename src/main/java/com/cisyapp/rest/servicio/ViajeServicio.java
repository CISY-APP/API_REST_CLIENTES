package com.cisyapp.rest.servicio;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

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
	//public List<Viaje> consultaViajesReservar(String origen, String destino, String fecha, String hora) {
		//return ViajeRepositorio.buscarViajeReservar(origen, destino, fecha, hora);
	//}
	
	public Optional <Viaje> muestraViajePorId(Integer id) {
		return ViajeRepositorio.findById(id);
	}
	
	public List<Viaje> muestraViajeSinHora(String origen, String destino, Date fecha){
		return ViajeRepositorio.mostrarViajesSinHora(origen, destino, fecha);
	}
	
	public List<Viaje> muestraViajeConHora(String origen, String destino, Date fecha, Date hora){
		return ViajeRepositorio.mostrarViajesConHora(origen, destino, fecha, hora);
	}
	
	//Metodo para eliminar un Viaje
		public Viaje eliminaViaje(Viaje v) {
			ViajeRepositorio.delete(v);
			return null;
			}

}
