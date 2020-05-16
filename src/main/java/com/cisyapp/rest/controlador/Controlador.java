package com.cisyapp.rest.controlador;


import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cisyapp.rest.modelo.Usuario;
import com.cisyapp.rest.modelo.Vehiculo;
import com.cisyapp.rest.modelo.Viaje;
import com.cisyapp.rest.servicio.IncidenciaServicio;
import com.cisyapp.rest.servicio.UsuarioServicio;
import com.cisyapp.rest.servicio.VehiculoServicio;
import com.cisyapp.rest.servicio.ViajeServicio;




@Controller
public class Controlador {

	@Autowired
	private UsuarioServicio UsuarioServicio;

	@Autowired
	private ViajeServicio ViajeServicio;
	
	@Autowired
	private IncidenciaServicio IncidenciaServicio;
	
	@Autowired
	private VehiculoServicio VehiculoServicio;

	
	// ------------------------------USUARIOS------------------------------
	
	
	//REGISTRO USUARIOS
	@RequestMapping(value = "/registrarUsuario", method = RequestMethod.POST)
	public ResponseEntity<Usuario> registraUsuario(@Valid @RequestBody Usuario usuario) {
		return new ResponseEntity<Usuario>(UsuarioServicio.registraUsuario(usuario), HttpStatus.OK);
	}
	
	
	 //LOGIN DE USUARIO
	//Metodo que recibe un String con el email a consultar y la clave del usuario desde la ruta de la petición y devuelve la entidad que coincide
	@RequestMapping(value = "/loginUser/{email}/{clave}", method = RequestMethod.GET)//Asociamos la petición recibida la metodo de respuesta
	public ResponseEntity<Usuario> consultaUsuarioPorEmail(@PathVariable("email") String email, @PathVariable("clave") String clave) {
		Optional<Usuario> uOpt=UsuarioServicio.consultaUsuarioPorEmail(email);//Llamamos al metodo consultaUsuario del servicio
			if (uOpt.isPresent()) {//Si existe el usuario, comprobamos si la clave es correcta
				if (uOpt.get().getClave().equals(clave)){
					Usuario u= uOpt.get();
			    	return new ResponseEntity<Usuario>(u,HttpStatus.OK);//Si existe el usuario y la clave es correcta devolvemos el usuario
		    	}else {
		    		return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);
		    		}
		    }else {
		    		return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		    }
		    		
	}
	
	
	//MOSTRAR USUARIO POR ID
	 	@RequestMapping(value = "/consultarUsuarioPorId/{id}", method = RequestMethod.GET)
		public ResponseEntity<Usuario> consultaUsuarioPorId(@PathVariable("id") Integer id) {
	 		Optional<Usuario>uOpt=UsuarioServicio.consultaUsuarioPorId(id);
	 		if(uOpt.isPresent()) {
	 			Usuario u=uOpt.get();
	 			return new ResponseEntity<Usuario>(u, HttpStatus.OK);
	 		}else {
	 			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	 		}	
		}
	
	
	//ACTUALIZAR USUARIO
	 @RequestMapping(value = "/actualizarUsuario", method = RequestMethod.PUT)//Asociamos la petición recibida la metodo de respuesta
	   	public ResponseEntity<Usuario> actualizaUsuarioPorID(@RequestBody Map<String, String> param) {
		 
		 	Integer auxIdUsuario=Integer.parseInt(param.get("idUsuario"));
		 	Optional<Usuario> uOpt=UsuarioServicio.consultaUsuarioPorId(auxIdUsuario);
	    	
		 	if(uOpt.isPresent()) {
		 		Usuario u=uOpt.get();
		 		
		 		if(!param.get("telefono").equals("")) {
		 			u.setTelefono(Integer.parseInt(param.get("telefono")));
		 		}
		 		if(!param.get("fechaNac").equals("")) {
		 			u.setFechanacimiento(Date.valueOf(param.get("fechaNac")));
		 		}
		 		if(!param.get("descripcion").equals("")) {
		 			u.setDescripcion(param.get("descripcion"));
		 		}
		 		
		 		return ResponseEntity.ok(UsuarioServicio.actualizaUsuario(u));
		 	}else {
		 		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		 	}
	    
	    		
	   	}
	 
	 
	 //ACTUALIZAR CLAVE DE USUARIO POR ID USUARIO
	 @RequestMapping(value = "/actualizarClaveUsuario", method = RequestMethod.PUT)//Asociamos la petición recibida la metodo de respuesta
	   	public ResponseEntity<Usuario> actualizaClaveUsuario(@RequestBody Map<String, String> param) {
		 
		 	Integer auxIdUsuario=Integer.parseInt(param.get("idUsuario"));
		 	Optional<Usuario> uOpt=UsuarioServicio.consultaUsuarioPorId(auxIdUsuario);
	    	
		 	if(uOpt.isPresent()) {
		 		Usuario u=uOpt.get();
		 		
		 		String auxClaveActual=param.get("claveActual");
		 		if(u.getClass().equals(auxClaveActual)) {
		 			u.setClave(param.get("nuevaClave"));
		 			return ResponseEntity.ok(UsuarioServicio.actualizaUsuario(u));
		 		}else {
		 			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		 		}
		 		
		 	}else {
		 		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		 	}
	    
	    		
	   	}
	 
	 
	 //ELIMINAR USUARIO POR ID
	 @RequestMapping(value = "/eliminarUsuario/{id}", method = RequestMethod.DELETE)//Asociamos la petición recibida la metodo de respuesta
		public ResponseEntity<Usuario> eliminarUsuarioPorId(@PathVariable("id") Integer id) {
		    Optional<Usuario> uOpt=UsuarioServicio.consultaUsuarioPorId(id);
		    if (uOpt.isPresent()) {
		    	Usuario u= uOpt.get();
		    	return new ResponseEntity<>(UsuarioServicio.eliminaUsuario(u),HttpStatus.OK);//Si existe eliminamos el vehiculo solicitado
		    }else {	
		    	return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		    }
		   
		}

	
 
	
	// ------------------------------VIAJES------------------------------
		 
	/* 
	// REGISTRO VIAJES OBJETOS
		@RequestMapping(value = "/registrarViaje", method = RequestMethod.POST)
		public ResponseEntity<Viaje> registraViaje(@Valid @RequestBody Viaje viaje) {
			Optional<Usuario>uOpt=UsuarioServicio.consultaUsuarioPorId(viaje.getUsuario().getIdusuario());
			if(uOpt.isPresent()){
				if(uOpt.get().getEsconductor()==true) {
					Optional<Vehiculo>vOpt=VehiculoServicio.consultarVehiculoPorMatricula(viaje.getVehiculo().getMatricula());
					if(vOpt.isPresent()) {
						return new ResponseEntity<Viaje>(ViajeServicio.registraViaje(viaje), HttpStatus.OK);
					} else {
						return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
					}
				} else {
					return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
				}
			} else {
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}
		}
		 
		 */
		 
	//REGISTRAR VIAJE
	@RequestMapping(value = "/registrarViaje", method = RequestMethod.POST)
	public ResponseEntity<Viaje> registraViaje(@RequestBody Map<String, String> param) {
 		Integer auxIdUsuario=Integer.parseInt(param.get("idUsuario"));
 		Optional<Usuario>uOpt=UsuarioServicio.consultaUsuarioPorId(auxIdUsuario);
 		if(uOpt.isPresent()) {
 			if(uOpt.get().getEsconductor()==true) {
 				String auxMatricula=param.get("matricula");
 				Optional<Vehiculo>vOpt=VehiculoServicio.consultarVehiculoPorMatricula(auxMatricula);
 				if(vOpt.get().getUsuario().getIdusuario()==auxIdUsuario) {
 					String auxOrigen=param.get("origen");
 	 				String auxDestino=param.get("destino");
 	 				int auxNumPlazas=Integer.parseInt(param.get("numPlazas"));
 	 				Date auxFecha=Date.valueOf(param.get("fecha"));
 	 				Date auxHora=Date.valueOf(param.get("horaSalida"));
 	 				Long precioAux=Long.valueOf(param.get("precio"));
 	 				BigDecimal auxPrecio=BigDecimal.valueOf(precioAux);
 	 				Viaje v=new Viaje(uOpt.get(), vOpt.get(), auxOrigen, auxDestino, auxNumPlazas, auxFecha);
 	 				return new ResponseEntity<Viaje>(ViajeServicio.registraViaje(v), HttpStatus.OK);
 				}else {
 					return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
 				}
 				
			}else {
				return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);
			}
 		}else {
 			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);	
 		}
	}
	
	
	
	//CONSULTAR VIAJE FILTRANDO
 	@RequestMapping(value="/consultaViajesReservar/{origen}/{destino}/{fecha}/{hora}", method=RequestMethod.GET)
	public ResponseEntity<List<Viaje>> consultaViajesReservar(@PathVariable("origen") String origen, @PathVariable("destino") String destino, @PathVariable("fecha") String fecha, @PathVariable("hora") String hora){
		return new ResponseEntity<List<Viaje>>(ViajeServicio.consultaViajesReservar(origen, destino, fecha, hora),HttpStatus.OK);		
	}
	
 	
 	
 	
 // ------------------------------INCIDENCIAS------------------------------
 	
 	
 	
 	
 // ------------------------------VEHICULOS------------------------------
 	
 	
 	//REGISTRAR VEHICULO
 	@RequestMapping(value = "/registrarVehiculo", method = RequestMethod.POST)
	public ResponseEntity<Vehiculo> registraVehiculo(@RequestBody Map<String, String> param) {
 		Integer auxIdUsuario=Integer.parseInt(param.get("idUsuario"));
 		Optional<Usuario>uOpt=UsuarioServicio.consultaUsuarioPorId(auxIdUsuario);
 		if(uOpt.isPresent()) {
 			String matricula=param.get("matricula");
 			Date fecha=Date.valueOf(param.get("fecha_alta"));
 			Vehiculo v=new Vehiculo(uOpt.get(),matricula, fecha);
 			Usuario u=uOpt.get();
 			u.setEsconductor(true);
			UsuarioServicio.actualizaUsuario(u);
 			
 			return new ResponseEntity<Vehiculo>(VehiculoServicio.registraVehiculo(v), HttpStatus.OK);
 		}else {
 			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);	
 		}
	}
 	
 	
 	
 	
 	//MOSTRAR VEHICULOS POR ID DE USUARIO
 	@RequestMapping(value = "/consultarVehiculoPorIdUsuario/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Vehiculo>> consultaVehiculoPorIdUsuario(@PathVariable("id") Integer id) {
 		Optional<Usuario>uOpt=UsuarioServicio.consultaUsuarioPorId(id);
 		if(uOpt.isPresent()) {
 			return new ResponseEntity<List<Vehiculo>>(VehiculoServicio.consultaVehiculosPorIdUsuario(id), HttpStatus.OK);
 		}else {
 			return new ResponseEntity<List<Vehiculo>>(HttpStatus.NOT_FOUND);
 		}	
	}
 	
 	
 	//MOSTRAR VEHICULO POR MATRICULA
 	 	@RequestMapping(value = "/consultarVehiculoPorMatricula/{matricula}", method = RequestMethod.GET)
 		public ResponseEntity<Vehiculo>consultaVehiculoPorMatricula(@PathVariable("matricula") String matricula) {
 	 		Optional<Vehiculo>vOpt=VehiculoServicio.consultarVehiculoPorMatricula(matricula);
 	 		if(vOpt.isPresent()) {
 	 			Vehiculo v=vOpt.get();
 	 			return new ResponseEntity<Vehiculo>(v, HttpStatus.OK);
 	 		}else {
 	 			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
 	 		}	
 		}
 	
 	
 	
 	
 	//ELIMINAR VEHICULO POR ID
 	//Metodo que recibe un Integer con el Id a del Producto a eliminar desde la ruta de la petición y lo elimina si existe
 		@RequestMapping(value = "/eliminarVehiculo/{id}", method = RequestMethod.DELETE)//Asociamos la petición recibida la metodo de respuesta
 		public ResponseEntity<Vehiculo> eliminarVehiculoPorId(@PathVariable("id") Integer id) {
 		    Optional<Vehiculo> vOpt=VehiculoServicio.consultaVehiculoPorId(id);
 		    if (vOpt.isPresent()) {
 		    	Vehiculo v= vOpt.get();
 		    	Integer auxIdUsuario=v.getUsuario().getIdusuario();
 		    	Optional<Usuario> uOpt=UsuarioServicio.consultaUsuarioPorId(auxIdUsuario);
 		    	Usuario u=uOpt.get();
 		    	VehiculoServicio.eliminaVehiculo(v);//Si el vehiculo existe, llamamos al metodo eliminar del servicio
 		    	Integer numVehiculos=VehiculoServicio.cuentaVehiculoPorIdUsuario(auxIdUsuario);
 		    	if(numVehiculos==0) {
 		    		u.setEsconductor(false);
 		    		UsuarioServicio.actualizaUsuario(u);
 		    	}
 		    	
 		    	return new ResponseEntity<>(null,HttpStatus.OK);//Si existe eliminamos el vehiculo solicitado
 		    }
 		    else {
 		    	return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
 		    }
 		 }


 
 	
 	
}
