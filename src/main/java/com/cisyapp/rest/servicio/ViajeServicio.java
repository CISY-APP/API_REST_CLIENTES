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
	
	// Metodo utilizado para registrar un nuevo viaje en la cuenta de un usuario:
	public Viaje registraViaje(Viaje viaje) {
		return ViajeRepositorio.save(viaje);
	}
	
	// Metodo que muestra viaje por id:
	public Optional <Viaje> muestraViajePorId(Integer id) {
		return ViajeRepositorio.findById(id);
	}
	
	// Metodo para obtener una lista con los viajes de ese dia y luego tratarla, no obtiene los viajes que pertenecen al mismo usuario:
	public List<Viaje> muestraViajeDelDia(int id, java.util.Date fecha, BigDecimal precio){
		SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String auxFechaIni=form.format(fecha);
		String auxFechaFin=form.format(fecha).substring(0,10)+" 23:59:59";
		return ViajeRepositorio.mostrarViajesDelDia(auxFechaIni,auxFechaFin,precio,id);
	}
	
	// Metodo para obtener una lista con los viajes publicados por el usuario:
	public List<Viaje> muestraViajesPublicados(Integer idUsuario){
		return ViajeRepositorio.mostrarViajesPublicados(idUsuario);
	}
	
	// Metodo para eliminar un Viaje:
	public Viaje eliminaViaje(Viaje v) {
		ViajeRepositorio.delete(v);
		return null;
	}
	// Metodo para actualizar viaje por id (Num plazas)
	public Viaje actualizaViaje(Viaje v) {
		return ViajeRepositorio.save(v);
	}

}
