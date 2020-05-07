package com.cisyapp.rest.controlador;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cisyapp.rest.modelo.Usuario;
import com.cisyapp.rest.modelo.Viaje;
import com.cisyapp.rest.servicio.UsuarioServicio;
import com.cisyapp.rest.servicio.ViajeServicio;




@Controller
public class Controlador {

	@Autowired
	private UsuarioServicio UsuarioServicio;

	@Autowired
	private ViajeServicio ViajeServicio;

	
	// ------------------------------USUARIOS------------------------------
	/* Método utilizado para registrar un cliente en la aplicación.
	 * Se realizara unicamente con los campos obligatorios.
	 * - Nombre
	 * - Apellidos
	 * - Email
	 * - Contraseña
	 */
	@RequestMapping(value = "/registrarUsuario", method = RequestMethod.POST)
	public ResponseEntity<Usuario> registrarUsuario(@RequestBody Usuario usuario) {
		return new ResponseEntity<Usuario>(UsuarioServicio.registrarUsuario(usuario), HttpStatus.OK);
	}
	
	 //Método utilizado para realizar el login de usuario
		//Metodo que recibe un String con el email a consultar desde la ruta de la petición y devuelve la entidad que coincide
		 @RequestMapping(value = "/loginUser/{email}", method = RequestMethod.GET)//Asociamos la petición recibida la metodo de respuesta
			public ResponseEntity<Usuario> consultarUsuario(@PathVariable("email") String email) {
		    	Optional<Usuario> uOpt=UsuarioServicio.consultaUsuario(email);//Llamamos al metodo consultarUsuario del servicio
		    	if (uOpt.isPresent()) {
		    		Usuario u= uOpt.get();
		    		return new ResponseEntity<Usuario>(u,HttpStatus.OK);//Si existe devolvemos el usuario solicitado
		    	}
		    	else
		    		return null;
			}
	
	
	
	// ------------------------------VIAJES------------------------------
	/* Metodo utilizado para registrar un viaje 
	 * Campos necesario para poder registrar un viaje
	 * - IDUsuario que publica el viaje.
	 * - IDVehiculo que realiza el viaje
	 * - Numero plazas disponibles
	 * - Origen
	 * - Destino 
	 * - Hora de salida
	 * - Precio (El sistema recomendará un precio en función del origen y el destino por plaza) 
	 * - Fecha de creacion del viaje (la coje del sistema)
	 */
	@RequestMapping(value = "/registrarViaje", method = RequestMethod.POST)
	public ResponseEntity<Viaje> registrarViaje(@RequestBody Viaje viaje) {
		return new ResponseEntity<Viaje>(ViajeServicio.registrarViaje(viaje), HttpStatus.OK);
	}
	
   /* Metodo para consultar los viajes disponibles, este mnétodo se utiliza para mostrar los viajes al usuario que quiere realizar una reserva
    * en uno de los coches
	* Campos necesarios por los cuales se realiza la busqueda.
	* - Origen
	* - Destino
	* - Fecha (Dia/mes/año)
	* - Hora
   */
 	@RequestMapping(value="/consultaViajesReservar/{origen}/{destino}/{fecha}/{hora}", method=RequestMethod.GET)
	public ResponseEntity<List<Viaje>> consultaViajesReservar(@PathVariable("origen") String origen, @PathVariable("destino") String destino, @PathVariable("fecha") String fecha, @PathVariable("hora") String hora){
		return new ResponseEntity<List<Viaje>>(ViajeServicio.consultaViajesReservar(origen, destino, fecha, hora),HttpStatus.OK);		
	}
	
}
