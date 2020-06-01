package com.cisyapp.rest.servicio;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
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
	
	//Metodo para obtener una lista con los viajes de ese dia y luego tratarla:
	public List<Viaje> muestraViajeDelDia(java.util.Date fecha, BigDecimal precio){
		SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String auxFechaIni=form.format(fecha);
		String auxFechaFin=form.format(fecha).substring(0,10)+" 23:59:59";
		return ViajeRepositorio.mostrarViajesDelDia(auxFechaIni,auxFechaFin,precio);
	}
	
	//Metodo para eliminar un Viaje:
	public Viaje eliminaViaje(Viaje v) {
		ViajeRepositorio.delete(v);
		return null;
	}

}
