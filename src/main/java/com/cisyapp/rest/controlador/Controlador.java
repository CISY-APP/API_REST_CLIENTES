package com.cisyapp.rest.controlador;


import java.math.BigDecimal;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

import com.cisyapp.rest.clasesaux.ReservaConIgnore;
import com.cisyapp.rest.clasesaux.UsuarioConIgnore;
import com.cisyapp.rest.clasesaux.VehiculoConIgnore;
import com.cisyapp.rest.clasesaux.ViajeConIgnore;
import com.cisyapp.rest.clasesviajespublicados.UsuarioPublicado;
import com.cisyapp.rest.clasesviajespublicados.ViajePublicado;
import com.cisyapp.rest.modelo.Reserva;
import com.cisyapp.rest.modelo.ReservaId;
import com.cisyapp.rest.modelo.Usuario;
import com.cisyapp.rest.modelo.Vehiculo;
import com.cisyapp.rest.modelo.Viaje;
import com.cisyapp.rest.servicio.ReservaServicio;
import com.cisyapp.rest.servicio.UsuarioServicio;
import com.cisyapp.rest.servicio.VehiculoServicio;
import com.cisyapp.rest.servicio.ViajeServicio;

@Controller
public class Controlador {
	//PRUEBA GITHUB

	@Autowired
	private UsuarioServicio UsuarioServicio;

	@Autowired
	private ViajeServicio ViajeServicio;
	
	@Autowired
	private VehiculoServicio VehiculoServicio;
	
	@Autowired
	private ReservaServicio ReservaServicio;
	
	// -------------------------------------------------------------USUARIOS----------------------------------------------------------------------- //
	//REGISTRO USUARIOS 
	@RequestMapping(value = "/registrarUsuario", method = RequestMethod.POST) //Definimos la URI a la que nos deben enviar las peticiones y el metodo http
	public ResponseEntity<Usuario> registraUsuario(@Valid @RequestBody Usuario usuario) { //Metodo que devuelve una entidad Usuario y recibe mediante el
																							//body un objeto de tipo usuario
		String auxClave=usuario.getClave(); 								//Comprobación de que la clave de usuario esta formada correctamente
		boolean letraMayuscula=false, letraMinuscula=false, numero=false;	//debe tener 1 mayusucula, 1 minuscula y un numero
		
			for(int i=0; i<auxClave.length();i++) {//Recorremos el String para comprobar
																														//que contiene el formato correcto
				if(auxClave.charAt(i)>='A' && auxClave.charAt(i)<='Z') {	//Si encontramos una mayuscula actualizamos el boolean a true
					letraMayuscula=true;
				}
				if(auxClave.charAt(i)>='a' && auxClave.charAt(i)<='z') {	//Si encontramos una minuscula actualizamos el boolean a true
					letraMinuscula=true;
				}
				
				if(auxClave.charAt(i)>='0' && auxClave.charAt(i)<='9') {	//Si encontramos un numero actualizamos el boolean a true
					numero=true;
				}
			}
			
			if(letraMayuscula==true && letraMinuscula==true && numero==true) {	//Si la clave tiene el formato correcto registramos el usuario
				return new ResponseEntity<Usuario>(UsuarioServicio.registraUsuario(usuario), HttpStatus.OK);
			}else {
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);	//Si la clave no tiene el formato correcto devolvemos un codigo de error
			}
		
	}
	
	//LOGIN DE USUARIO
	//Metodo que recibe un String con el email a consultar y la clave del usuario desde la ruta de la petición y devuelve la entidad que coincide
	@RequestMapping(value = "/loginUser/{email}/{clave}", method = RequestMethod.GET)//Asociamos la petición recibida la metodo de respuesta
	public ResponseEntity<UsuarioConIgnore> consultaUsuarioPorEmail(@PathVariable("email") String email, @PathVariable("clave") String clave) {
		Optional<Usuario> uOpt=UsuarioServicio.consultaUsuarioPorEmail(email);//Llamamos al metodo consultaUsuario del servicio
		if (uOpt.isPresent()) {//Si existe el usuario, comprobamos si la clave es correcta
			if (uOpt.get().getClave().equals(clave)){
				Usuario u= uOpt.get();
				//Creamos un usuario con jsonignore
				UsuarioConIgnore uI = new UsuarioConIgnore();
				uI.setApellidos(u.getApellidos());
				uI.setClave(u.getClave());
				uI.setDescripcion(u.getDescripcion());
				uI.setEmail(u.getEmail());
				uI.setEsconductor(u.getEsconductor());
				uI.setEspasajero(u.getEspasajero());
				uI.setFechadesconexion(u.getFechadesconexion());
				uI.setFechanacimiento(u.getFechanacimiento());
				uI.setFecharegistro(u.getFecharegistro());
				uI.setFotousuario(u.getFotousuario());
				uI.setIdusuario(u.getIdusuario());
				uI.setNombre(u.getNombre());
				uI.setPagosForIdusuarioconductor(u.getPagosForIdusuarioconductor());
				uI.setPagosForIdusuariopasajero(u.getPagosForIdusuariopasajero());
				uI.setReservas(u.getReservas());
				uI.setSesioniniciada(u.getSesioniniciada());
				uI.setTelefono(u.getTelefono());
				uI.setVehiculos(u.getVehiculos());
				uI.setViajes(u.getViajes());
		    	return new ResponseEntity<UsuarioConIgnore>(uI,HttpStatus.OK);//Si existe el usuario y la clave es correcta devolvemos el usuario
	    	}else {
	    		return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);
	    	}
	    }else {
	    		return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
	    }
	}
	
	//MOSTRAR USUARIO POR ID
	@RequestMapping(value = "/consultarUsuarioPorId/{id}", method = RequestMethod.GET)//Definimos la URI a la que nos deben enviar las peticiones y el metodo http
	public ResponseEntity<UsuarioConIgnore> consultaUsuarioPorId(@PathVariable("id") Integer id) {//Metodo que devuelve una entidad  
																						//Usuario y recibe mediante el body un objeto de tipo usuario
	 	Optional<Usuario>uOpt=UsuarioServicio.consultaUsuarioPorId(id);	//Llamamos al metodo del servicio consultaUsuarioPorId que nos devuelve un usuario
 		if(uOpt.isPresent()) {
 			Usuario u=uOpt.get();									//Si el usuario existe, lo devolvemos
 																	
			UsuarioConIgnore uI = new UsuarioConIgnore();	//Creamos un usuario con jsonignore
			uI.setApellidos(u.getApellidos());
			uI.setClave(u.getClave());
			uI.setDescripcion(u.getDescripcion());
			uI.setEmail(u.getEmail());
			uI.setEsconductor(u.getEsconductor());
			uI.setEspasajero(u.getEspasajero());
			uI.setFechadesconexion(u.getFechadesconexion());
			uI.setFechanacimiento(u.getFechanacimiento());
			uI.setFecharegistro(u.getFecharegistro());
			uI.setFotousuario(u.getFotousuario());
			uI.setIdusuario(u.getIdusuario());
			uI.setNombre(u.getNombre());
			uI.setPagosForIdusuarioconductor(u.getPagosForIdusuarioconductor());
			uI.setPagosForIdusuariopasajero(u.getPagosForIdusuariopasajero());
			uI.setReservas(u.getReservas());
			uI.setSesioniniciada(u.getSesioniniciada());
			uI.setTelefono(u.getTelefono());
			uI.setVehiculos(u.getVehiculos());
			uI.setViajes(u.getViajes());
 			return new ResponseEntity<UsuarioConIgnore>(uI, HttpStatus.OK);
 		}else {														//Si el usuario NO existe devolvemos un código de error
 			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
 		}
	}
	
	//ACTUALIZAR USUARIO
	@RequestMapping(value = "/actualizarUsuario", method = RequestMethod.PUT)//Definimos la URI a la que nos deben enviar las peticiones y el metodo http
   	public ResponseEntity<UsuarioConIgnore> actualizaUsuarioPorID(@RequestBody Map<String, String> param) {//Metodo que actualiza los datos del usuario y 
	 																								//devuelve el usuario actualizado
	 	Integer auxIdUsuario=Integer.parseInt(param.get("idUsuario"));	//Comprobamos que el usuario existe
	 	Optional<Usuario> uOpt=UsuarioServicio.consultaUsuarioPorId(auxIdUsuario);
    	
	 	if(uOpt.isPresent()) {											//Si el usuario existe creamos una entidad usuario con los datos de la BD
	 		Usuario u=uOpt.get();
	 		if(!param.get("telefono").equals("")) {						//Si el atributo telefono que nos han pasado tiene contenido lo verificamos
	 			try {
	 				Integer auxTelefono=Integer.parseInt(param.get("telefono"));	//Verificamos que el telefono sea de tipo Integer
	 				if(param.get("telefono").length()==9) {							//si es de tipo Integer, comprobamos que sean 9 digitos
	 					u.setTelefono(auxTelefono);									//si todo es correcto establecemos el nuevo telefono
	 				}else {
	 					return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);	//Si no tiene 9 digitos lanzamos un codigo de error
	 				}
	 			}catch(NumberFormatException n){	
	 				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);		//Si no es de tipo Integer lanzamos un codigo de error
	 			}
	 		}
	 		if(!param.get("fechaNac").equals("")) {									//Comprobamos que el atributo fecha Nacimiento tiene contenido
	 			u.setFechanacimiento(Date.valueOf(param.get("fechaNac")));			//si tiene contenido establecemos la nueva fecha
	 		}
	 		if(!param.get("descripcion").equals("") && !(param.get("descripcion").length()>300)) {								//Comprobamos que el altributo descripción tiene contenido
	 			u.setDescripcion(param.get("descripcion"));							//si tiene contenido establecemos la nueva descripción
	 		}
	 		if(!param.get("fotousuario").equals("")) {								//Comprobamos que el altributo descripción tiene contenido
	 			u.setFotousuario(param.get("fotousuario"));							//si tiene contenido establecemos la nueva descripción
	 		}
	 		Usuario uA = UsuarioServicio.actualizaUsuario(u);
	 		
	 		//Creamos un usuario con jsonignore
			UsuarioConIgnore uI = new UsuarioConIgnore();
			uI.setApellidos(uA.getApellidos());
			uI.setClave(uA.getClave());
			uI.setDescripcion(uA.getDescripcion());
			uI.setEmail(uA.getEmail());
			uI.setEsconductor(uA.getEsconductor());
			uI.setEspasajero(uA.getEspasajero());
			uI.setFechadesconexion(uA.getFechadesconexion());
			uI.setFechanacimiento(uA.getFechanacimiento());
			uI.setFecharegistro(uA.getFecharegistro());
			uI.setFotousuario(uA.getFotousuario());
			uI.setIdusuario(uA.getIdusuario());
			uI.setNombre(uA.getNombre());
			uI.setPagosForIdusuarioconductor(uA.getPagosForIdusuarioconductor());
			uI.setPagosForIdusuariopasajero(uA.getPagosForIdusuariopasajero());
			uI.setReservas(uA.getReservas());
			uI.setSesioniniciada(uA.getSesioniniciada());
			uI.setTelefono(uA.getTelefono());
			uI.setVehiculos(uA.getVehiculos());
			uI.setViajes(uA.getViajes());
	 		return ResponseEntity.ok(uI);			//Llamamos al metodo encargado de actualizar los datos
	 	}else {
	 		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);				//Si el usuario no existe devolvemos un código de error
	 	}
   	}

	//ACTUALIZAR CLAVE DE USUARIO POR ID USUARIO
	@RequestMapping(value = "/actualizarClaveUsuario", method = RequestMethod.PUT) //Definimos la URI a la que nos deben enviar las peticiones y el metodo http
   	public ResponseEntity<UsuarioConIgnore> actualizaClaveUsuario(@RequestBody Map<String, String> param) { //Metodo encargado de actualizar la clave
	 																								//del usuario
	 	Integer auxIdUsuario=Integer.parseInt(param.get("idUsuario"));								//Consultamos si el usuario existe mediante el
	 	Optional<Usuario> uOpt=UsuarioServicio.consultaUsuarioPorId(auxIdUsuario);					//id de usuario
	 	if(uOpt.isPresent()) {														//Si el usuario existe
	 		//Usuario u=uOpt.get();													//Establecemos un usuario con los datos de la BD
	 		String auxClaveActual=param.get("claveActual");							//Comprobamos que la clave actual es correcta
	 		if(uOpt.get().getClave().equals(auxClaveActual)) {								//Si la clave actual es correcta, comprobamos que la nueva
	 																				//clave tiene un formato correcto
	 			boolean letraMayuscula=false, letraMinuscula=false, numero=false;
	 			String auxNuevaClave=param.get("nuevaClave");						//Comprobamos que la clave tiene un formato correcto recorriendola
	 			for(int i=0; i<auxNuevaClave.length() || letraMayuscula==false || letraMinuscula==false || numero==false;i++) {
	 				if(auxNuevaClave.charAt(i)>'A' || auxNuevaClave.charAt(i)<'Z') { //Comprobamos que existe una letra mayuscula
	 					letraMayuscula=true;
	 				}
	 				if(auxNuevaClave.charAt(i)>'a' || auxNuevaClave.charAt(i)<'z') { //Comprobamos que existe una letra minuscula
	 					letraMinuscula=true;
	 				}
	 				
	 				if(auxNuevaClave.charAt(i)>'0' || auxNuevaClave.charAt(i)<'9') { //Comprobamos que existe un numero
	 					numero=true;
	 				}
	 			}
	 			if(letraMayuscula==true && letraMinuscula==true && numero==true) {	//Si todas las comprobaciones son correctas
	 				uOpt.get().setClave(param.get("nuevaClave"));							//Asignamos la nueva clave
	 		 		Usuario u = uOpt.get();
	 		 		UsuarioServicio.actualizaUsuario(u);
	 		 		
	 		 		
	 				UsuarioConIgnore uI = new UsuarioConIgnore();	//Creamos un usuario con jsonignore
	 				uI.setApellidos(u.getApellidos());
	 				uI.setClave(u.getClave());
	 				uI.setDescripcion(u.getDescripcion());
	 				uI.setEmail(u.getEmail());
	 				uI.setEsconductor(u.getEsconductor());
	 				uI.setEspasajero(u.getEspasajero());
	 				uI.setFechadesconexion(u.getFechadesconexion());
	 				uI.setFechanacimiento(u.getFechanacimiento());
	 				uI.setFecharegistro(u.getFecharegistro());
	 				uI.setFotousuario(u.getFotousuario());
	 				uI.setIdusuario(u.getIdusuario());
	 				uI.setNombre(u.getNombre());
	 				uI.setPagosForIdusuarioconductor(u.getPagosForIdusuarioconductor());
	 				uI.setPagosForIdusuariopasajero(u.getPagosForIdusuariopasajero());
	 				uI.setReservas(u.getReservas());
	 				uI.setSesioniniciada(u.getSesioniniciada());
	 				uI.setTelefono(u.getTelefono());
	 				uI.setVehiculos(u.getVehiculos());
	 				uI.setViajes(u.getViajes());
		 			return ResponseEntity.ok(uI);	//Llamamos al metodo encargado de actualizar los datos
	 			}else {
	 				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);		//Si falla alguna comprobación devolvemos un codigo de error
	 			}
	 		}else {
	 			return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);			//Si la clave introducida es incorrecta devolvemos un error
	 		}
	 	}else {
	 		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);				//Si el usuario no existe devolvemos un codigo de error
	 	}
   	}
	
	//ELIMINAR USUARIO POR ID
	@RequestMapping(value = "/eliminarUsuario/{id}", method = RequestMethod.DELETE)//Definimos la URI a la que nos deben enviar las peticiones y el metodo http
	public ResponseEntity<Usuario> eliminarUsuarioPorId(@PathVariable("id") Integer id) { //Metodo que elimina a un usuario por id
	    Optional<Usuario> uOpt=UsuarioServicio.consultaUsuarioPorId(id);				//Comprobams si el usuario existe
	    if (uOpt.isPresent()) {
	    	Usuario u= uOpt.get();														//Si existe establecemos a un objeto usuario los valores 
	    																				//asociados al id recibido
	    	return new ResponseEntity<>(UsuarioServicio.eliminaUsuario(u),HttpStatus.OK);//Si existe eliminamos el usuario solicitado
	    }else {	
	    	return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);						//Si el usuario no existe devolvemos un código de error
	    }
	}

	// ---------------------------------------------------------------VIAJES------------------------------------------------------------------------- //
		 
	//REGISTRAR VIAJE
	@RequestMapping(value = "/registrarViaje", method = RequestMethod.POST)
	public ResponseEntity<ViajeConIgnore> registraViaje(@RequestBody Map<String, String> param) {
 		Integer auxIdUsuario=Integer.parseInt(param.get("idUsuario"));
 		Optional<Usuario>uOpt=UsuarioServicio.consultaUsuarioPorId(auxIdUsuario);
 		ViajeConIgnore vError=new ViajeConIgnore();
 		if(uOpt.isPresent()) {
 			if(uOpt.get().getEsconductor()==true) {
 				String auxMatricula=param.get("matricula");
 				if(auxMatricula.length()==7) {
 	 				try {
 	 					Integer.parseInt(auxMatricula.substring(0, 4));
 	 					boolean pruebaLetras=true;
 	 					for(int i = 4 ; i < auxMatricula.length() && pruebaLetras ; i++ ) {
 	 						if(auxMatricula.charAt(i)<'A'||auxMatricula.charAt(i)>'Z') {
 	 							pruebaLetras=false;
 	 							vError.setMatriculaError("Caracter incorrecto en bloque de letras");
 	 							return new ResponseEntity<ViajeConIgnore>(vError,HttpStatus.FORBIDDEN);	
 	 						}
 	 					}
 	 				}catch (NumberFormatException n){
 	 					vError.setMatriculaError("Caracter incorrecto en bloque de numeros");
 	 					return new ResponseEntity<ViajeConIgnore>(vError,HttpStatus.FORBIDDEN);	
 	 				}
 	 			}else {
 	 				vError.setMatriculaError("La matricula tiene una LONGITUD incorrecta");
 	 				return new ResponseEntity<ViajeConIgnore>(vError,HttpStatus.FORBIDDEN);	
 	 			}
 				Optional<Vehiculo>vOpt=VehiculoServicio.consultaVehiculoPorMatricula(auxMatricula);
 				if(vOpt.isPresent()) {
 					if(vOpt.get().getUsuario().getIdusuario()==auxIdUsuario) {
 	 					String auxLocalidadOrigen=param.get("localidadOrigen");
 	 					String auxLugarSalida=param.get("lugarSalida");
 	 	 				String auxLocalidadDestino=param.get("localidadDestino");
 	 	 				String auxLugarLlegada=param.get("lugarLlegada");
 	 	 				int auxNumPlazas=Integer.parseInt(param.get("numPlazas"));
 	 	 				SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 	 	 				java.util.Date auxFecha=null;
						try {
							auxFecha = form.parse(param.get("fecha"));
						} catch (ParseException e) {
							e.printStackTrace();
						}
 	 	 				Double precioAux= Double.valueOf(param.get("precio"));
 	 	 				BigDecimal auxPrecio=BigDecimal.valueOf(precioAux);
 	 	 				Viaje v=new Viaje();
 	 	 				v.setUsuario(uOpt.get());
 	 	 				v.setVehiculo(vOpt.get());
 	 	 				v.setLocalidadOrigen(auxLocalidadOrigen);
 	 	 				v.setLugarSalida(auxLugarSalida);
 	 	 				v.setLocalidadDestino(auxLocalidadDestino);
 	 	 				v.setLugarLlegada(auxLugarLlegada);
 	 	 				v.setNumplazasdisponibles(auxNumPlazas);
 	 	 				v.setFechasalida(auxFecha);
 	 	 				v.setPrecio(auxPrecio);
 	 	 				ViajeServicio.registraViaje(v);
 	 	 				
 	 	 				ViajeConIgnore vI=new ViajeConIgnore();
 	 	 				vI.setUsuario(v.getUsuario());
 	 	 				vI.setVehiculo(v.getVehiculo());
 	 	 				vI.setLocalidadOrigen(v.getLocalidadOrigen());
 	 	 				vI.setLugarSalida(v.getLugarSalida());
 	 	 				vI.setLocalidadDestino(v.getLocalidadDestino());
 	 	 				vI.setLugarLlegada(v.getLugarLlegada());
 	 	 				vI.setNumplazasdisponibles(v.getNumplazasdisponibles());
 	 	 				vI.setFechasalida(v.getFechasalida());
 	 	 				vI.setPrecio(v.getPrecio());
 	 	 				return new ResponseEntity<>(vI, HttpStatus.OK);
 	 				}else {
 	 					vError.setVehiculoError("El vehiculo no corresponde al usuario");
 	 					return new ResponseEntity<ViajeConIgnore>(vError,HttpStatus.BAD_REQUEST);
 	 				}
 					
 				}else {
 					vError.setVehiculoError("El vehiculo no existe");
 					return new ResponseEntity<ViajeConIgnore>(vError,HttpStatus.NOT_FOUND);	
 				}
 				
 				
			}else {
				vError.setUsuarioError("El usuario no ha dado de alta ningún vehiculo");
				return new ResponseEntity<ViajeConIgnore>(vError,HttpStatus.CONFLICT);
			}
 		}else {
 			vError.setUsuarioError("El usuario no existe");
 			return new ResponseEntity<ViajeConIgnore>(vError,HttpStatus.NOT_FOUND);	
 		}
	}
	
	//CONSULTAR VIAJE FILTRANDO POR FECHA, LOCALIDAD ORIGEN Y DESTINO, PRECIO ...
 	@RequestMapping(value="/consultaViajesReservar", method=RequestMethod.POST)
	public ResponseEntity<List<Viaje>> consultaViajesReservar(@RequestBody Map<String, String> param){
 		
 		if(!param.get("idUsuario").equals(null)&&!param.get("localidadOrigen").equals(null) && !param.get("lugarSalida").equals(null) 
 			&& !param.get("localidadDestino").equals(null) && !param.get("lugarLlegada").equals(null)
 			&& !param.get("fechaSalida").equals(null) && !param.get("precioMax").equals(null)) {
 			
 			int id = Integer.parseInt(param.get("idUsuario"));
 			String auxLocalidadOrigen=param.get("localidadOrigen");
 			String auxLugarSalida=param.get("lugarSalida");
 			String auxLocalidadDestino=param.get("localidadDestino");
 			String auxLugarLlegada=param.get("lugarLlegada");
 			SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			java.util.Date auxFechaSalida=null;
			try {auxFechaSalida = form.parse(param.get("fechaSalida"));} catch (ParseException e) {e.printStackTrace();}
			Double precioAux= Double.valueOf(param.get("precioMax"));
			BigDecimal auxPrecio=BigDecimal.valueOf(precioAux);
			
 			List<Viaje> listaOriginal=ViajeServicio.muestraViajeDelDia(id,auxFechaSalida,auxPrecio);
 			//Filtramos la lista por localidad de origen y de destino:
 			for (int i = 0; i < listaOriginal.size(); i++) {
				if(!Biblioteca.compruebaSiContieneLocalidad(listaOriginal.get(i).getLocalidadOrigen(), auxLocalidadOrigen)||!Biblioteca.compruebaSiContieneLocalidad(listaOriginal.get(i).getLocalidadDestino(), auxLocalidadDestino)) {
					listaOriginal.remove(i);
					i--;
				}
			}
 			//Filtremos los viajes que el usuario ya ha reservado una vez
 			for (int i = 0; i < listaOriginal.size(); i++) {
 				Optional<Reserva>rOpt=ReservaServicio.muestraReservaPorIdUsuarioYidViaje(id,listaOriginal.get(i).getIdviaje());
				if(rOpt.isPresent()) {
					listaOriginal.remove(i);
					i--;
				}
			}
 			//Limpiamos ciertos atributos para evitar la recursividad y por privacidad:
 			for(Viaje v: listaOriginal) {
 				v.getUsuario().setClave(null);
 				v.getUsuario().setPagosForIdusuarioconductor(null);
 				v.getUsuario().setPagosForIdusuariopasajero(null);
 				v.getUsuario().setReservas(null);
 				v.getUsuario().setVehiculos(null);
 				v.getUsuario().setViajes(null);
 				v.getVehiculo().setUsuario(null);
 				v.getVehiculo().setViajes(null);
 			}
 			//Ordenamos la lista por lugar de origen y de destino:
 			int i = 0;
 			List<Viaje> listaFinal = new ArrayList<Viaje>();
 			while(i<listaOriginal.size()) {
 				if(Biblioteca.compruebaSiContieneLocalidad(listaOriginal.get(i).getLugarSalida(), auxLugarSalida)&&Biblioteca.compruebaSiContieneLocalidad(listaOriginal.get(i).getLugarLlegada(), auxLugarLlegada)) {
 					listaFinal.add(listaOriginal.remove(i));
 					i--;
 				}
 				i++;
 			}
 			while(!listaOriginal.isEmpty()) {
 				listaFinal.add(listaOriginal.remove(0));
 			}
 			return new ResponseEntity<List<Viaje>>(listaFinal,HttpStatus.OK);
 		}else {
 			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
 		}
	}
	
 	//ELIMINAR VIAJE POR ID VIAJE
	@RequestMapping(value = "/eliminarViaje/{id}", method = RequestMethod.DELETE)	//Definimos la URI a la que nos deben enviar las peticiones y el metodo http
	public ResponseEntity<Viaje> eliminarViaje(@PathVariable("id") Integer id) { 	//Metodo que elimina a un usuario por id
	    Optional<Viaje> vOpt=ViajeServicio.muestraViajePorId(id);					//Comprobams si el usuario existe
	    if (vOpt.isPresent()) {
	    	Viaje v= vOpt.get();														//Si existe establecemos a un objeto usuario los valores asociados al id recibido
	    	return new ResponseEntity<>(ViajeServicio.eliminaViaje(v),HttpStatus.OK);	//Si existe eliminamos el usuario solicitado
	    }else {	
	    	return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);						//Si el usuario no existe devolvemos un código de error
	    }
	}
 	
	//CONSULTAR LOS VIAJES QUE EL USUARIO HA RESERVADO A LO LARGO DEL TIEMPO(NO SE FILTRA POR PRESENTE O PASADO)
 	@RequestMapping(value="/consultarViajesReservadosPorIdUsuario/{idUsuario}", method=RequestMethod.GET)
	public ResponseEntity<List<Viaje>> consultaViajesReservados(@PathVariable("idUsuario") Integer idUsuario){
		Optional <List<Reserva>> listOpt=ReservaServicio.muestraReservasPorIdUsuario(idUsuario);
		if(listOpt.isPresent()) {
			List<Viaje> listFinal = new ArrayList<Viaje>();
			for (int i=0 ; i<listOpt.get().size() ; i++) {
				Optional<Viaje> vOpt = ViajeServicio.muestraViajePorId(listOpt.get().get(i).getId().getIdviaje());
				if(vOpt.isPresent()) {
					Viaje vF = new Viaje();
					vF.setFechacreacionviaje(vOpt.get().getFechacreacionviaje());
					vF.setFechasalida(vOpt.get().getFechasalida());
					vF.setIdviaje(vOpt.get().getIdviaje());
					vF.setLocalidadDestino(vOpt.get().getLocalidadDestino());
					vF.setLocalidadOrigen(vOpt.get().getLocalidadOrigen());
					vF.setLugarLlegada(vOpt.get().getLugarLlegada());
					vF.setLugarSalida(vOpt.get().getLugarSalida());
					vF.setNumplazasdisponibles(vOpt.get().getNumplazasdisponibles());
					vF.setPrecio(vOpt.get().getPrecio());
					vF.setReservas(null);
					vF.setUsuario(vOpt.get().getUsuario());;
					vF.getUsuario().setClave(null);
	 				vF.getUsuario().setPagosForIdusuarioconductor(null);
	 				vF.getUsuario().setPagosForIdusuariopasajero(null);
	 				vF.getUsuario().setReservas(null);
	 				vF.getUsuario().setVehiculos(null);
	 				vF.getUsuario().setViajes(null);
	 				vF.setVehiculo(vOpt.get().getVehiculo());
	 				vF.getVehiculo().setUsuario(null);
	 				vF.getVehiculo().setViajes(null);
	 				listFinal.add(vF);
				}
			}
			return new ResponseEntity<List<Viaje>>(listFinal, HttpStatus.OK);
		}else {
			return new ResponseEntity<List<Viaje>>(HttpStatus.NOT_FOUND);
		}
	}
 	
 	//CONSULTAR LOS VIAJES PUBLICADOS QUE EL USUARIO HA PUBLICADO A LO LARGO DEL TIEMPO(NO SE FILTRA POR PRESENTE O PASADO)
 	@RequestMapping(value="/consultarViajesPublicadosPorIdUsuario/{idUsuario}", method=RequestMethod.GET)
	public ResponseEntity<List<ViajePublicado>> consultaViajesPublicados(@PathVariable("idUsuario") Integer idUsuario){
		List<Viaje> listViajes = ViajeServicio.muestraViajesPublicados(idUsuario);
		List<ViajePublicado> listFinal = new ArrayList<ViajePublicado>();
		if(!listViajes.equals(null)&&!listViajes.isEmpty()) {
			for (int i=0 ; i<listViajes.size() ; i++) {
				Optional <List<Reserva>> listOptReser=ReservaServicio.muestraReservasPorIdViaje(listViajes.get(i).getIdviaje());
				if(listOptReser.isPresent()) {
					List<UsuarioPublicado> listUsuario = new ArrayList<UsuarioPublicado>();
					for(int j=0 ; j < listOptReser.get().size() ; j++) {
						Optional<Usuario> uOpt=UsuarioServicio.consultaUsuarioPorId(listOptReser.get().get(j).getId().getIdusuariopasajero());
						if(uOpt.isPresent()) {
							listUsuario.add(new UsuarioPublicado(uOpt.get().getIdusuario(),uOpt.get().getNombre(),uOpt.get().getApellidos(),uOpt.get().getTelefono(),
									uOpt.get().getEmail(),uOpt.get().getFechanacimiento(),uOpt.get().getFotousuario(),uOpt.get().getDescripcion()));
						}
					}
					listFinal.add(new ViajePublicado(listViajes.get(i).getIdviaje(), listViajes.get(i).getLocalidadOrigen(), listViajes.get(i).getLugarSalida(), listViajes.get(i).getLocalidadDestino(),
							listViajes.get(i).getLugarLlegada(), listViajes.get(i).getPrecio(), listViajes.get(i).getNumplazasdisponibles(), listViajes.get(i).getFechasalida(), listViajes.get(i).getFechacreacionviaje(),
							listUsuario, listViajes.get(i).getVehiculo().getMarca()+" - "+listViajes.get(i).getVehiculo().getModelo()+" - "+listViajes.get(i).getVehiculo().getMatricula()));
				}else {
					listFinal.add(new ViajePublicado(listViajes.get(i).getIdviaje(), listViajes.get(i).getLocalidadOrigen(), listViajes.get(i).getLugarSalida(), listViajes.get(i).getLocalidadDestino(),
							listViajes.get(i).getLugarLlegada(), listViajes.get(i).getPrecio(), listViajes.get(i).getNumplazasdisponibles(), listViajes.get(i).getFechasalida(), listViajes.get(i).getFechacreacionviaje(),
							new ArrayList<UsuarioPublicado>(),listViajes.get(i).getVehiculo().getMarca()+" - "+listViajes.get(i).getVehiculo().getModelo()+" - "+listViajes.get(i).getVehiculo().getMatricula()));
				}
			}
			return new ResponseEntity<List<ViajePublicado>>(listFinal, HttpStatus.OK);
		}else {
			return new ResponseEntity<List<ViajePublicado>>(HttpStatus.NOT_FOUND);
		}
	}
 	
 	//CONSULTAR VIAJE POR ID VIAJE
 	@RequestMapping(value="/consultarViajePorIdViaje/{idViaje}", method=RequestMethod.GET)
	public ResponseEntity<Viaje> consultarViajePorIdViaje(@PathVariable("idViaje") Integer idViaje){
		Optional<Viaje> vOpt = ViajeServicio.muestraViajePorId(idViaje);
		if(vOpt.isPresent()) {
			Viaje vF = new Viaje();
			vF.setFechacreacionviaje(vOpt.get().getFechacreacionviaje());
			vF.setFechasalida(vOpt.get().getFechasalida());
			vF.setIdviaje(vOpt.get().getIdviaje());
			vF.setLocalidadDestino(vOpt.get().getLocalidadDestino());
			vF.setLocalidadOrigen(vOpt.get().getLocalidadOrigen());
			vF.setLugarLlegada(vOpt.get().getLugarLlegada());
			vF.setLugarSalida(vOpt.get().getLugarSalida());
			vF.setNumplazasdisponibles(vOpt.get().getNumplazasdisponibles());
			vF.setPrecio(vOpt.get().getPrecio());
			vF.setReservas(null);
			vF.setUsuario(vOpt.get().getUsuario());;
			vF.getUsuario().setClave(null);
			vF.getUsuario().setPagosForIdusuarioconductor(null);
			vF.getUsuario().setPagosForIdusuariopasajero(null);
			vF.getUsuario().setReservas(null);
			vF.getUsuario().setVehiculos(null);
			vF.getUsuario().setViajes(null);
			vF.setVehiculo(vOpt.get().getVehiculo());
			vF.getVehiculo().setUsuario(null);
			vF.getVehiculo().setViajes(null);
			return new ResponseEntity<Viaje>(vF, HttpStatus.OK);
		}else {
			return new ResponseEntity<Viaje>(HttpStatus.NOT_FOUND);
		}
	}
 	
 	//--------------------------------------------------------------VEHICULOS------------------------------------------------------------------------
 	
 	//REGISTRAR VEHICULO
 	//MATRICULA, MARCA, MODELO, COMBUSTIBLE Y COLOR
 	@RequestMapping(value = "/registrarVehiculo", method = RequestMethod.POST)
	public ResponseEntity<VehiculoConIgnore> registraVehiculo(@Valid @RequestBody Map<String, String> param) {
 		Integer auxIdUsuario=Integer.parseInt(param.get("idUsuario"));
 		Optional<Usuario>uOpt=UsuarioServicio.consultaUsuarioPorId(auxIdUsuario);
 		if(uOpt.isPresent()) {
 			String auxMatricula=param.get("matricula");
 			auxMatricula=auxMatricula.replaceAll("\\s","").toUpperCase();
 			if(auxMatricula.length()==7) {
 				try {
 					Integer.parseInt(auxMatricula.substring(0, 4));
 					boolean pruebaLetras=true;
 					for(int i = 4 ; i < auxMatricula.length() && pruebaLetras ; i++ ) {
 						if(auxMatricula.charAt(i)<'A'||auxMatricula.charAt(i)>'Z') {
 							pruebaLetras=false;
 							return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);	
 						}
 					}
 				}catch (NumberFormatException n){
 					return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);	
 				}
 			}else {
 				return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);	
 			}
 			Vehiculo v=new Vehiculo();
 			v.setUsuario(uOpt.get());
 			v.setMatricula(auxMatricula);
 			v.setMarca(param.get("marca"));
 			v.setModelo(param.get("modelo"));
 			v.setCombustible(param.get("combustible"));
 			v.setColor(param.get("color"));
 			v.setFotovehiculo(param.get("fotovehiculo"));
 			Usuario u=uOpt.get();
 			u.setEsconductor(true);
			UsuarioServicio.actualizaUsuario(u);
			Vehiculo vR = VehiculoServicio.registraVehiculo(v);
	 		//Creamos un vehiculo con jsonignore
			VehiculoConIgnore vI = new VehiculoConIgnore();
			vI.setColor(vR.getColor());
			vI.setCombustible(vR.getCombustible());
			vI.setFechadealta(vR.getFechadealta());
			vI.setFotovehiculo(vR.getFotovehiculo());
			vI.setIdvehiculo(vR.getIdvehiculo());
			vI.setMarca(vR.getMarca());
			vI.setMatricula(vR.getMatricula());
			vI.setModelo(vR.getModelo());
			vI.setUsuario(vR.getUsuario());
			vI.setViajes(vR.getViajes());
 			return new ResponseEntity<>(vI, HttpStatus.OK);
 		}else {
 			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);	
 		}
	}
 	
 	//MOSTRAR VEHICULOS POR ID DE USUARIO
 	@RequestMapping(value = "/consultarVehiculoPorIdUsuario/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<VehiculoConIgnore>> consultaVehiculoPorIdUsuario(@PathVariable("id") Integer id) {
 		Optional<Usuario>uOpt=UsuarioServicio.consultaUsuarioPorId(id);
 		if(uOpt.isPresent()) {
 			List<Vehiculo> listaOriginal = VehiculoServicio.consultaVehiculosPorIdUsuario(id);
 		 	List<VehiculoConIgnore> listaAuxiliar = new ArrayList<VehiculoConIgnore>();
 		 	for (Vehiculo vl : listaOriginal) {
 		 		VehiculoConIgnore v = new VehiculoConIgnore();
 		 		v.setColor(vl.getColor());
 		 		v.setCombustible(vl.getCombustible());
 		 		v.setFechadealta(vl.getFechadealta());
 				v.setFotovehiculo(vl.getFotovehiculo());
 				v.setIdvehiculo(vl.getIdvehiculo());
 				v.setMarca(vl.getMarca());
 				v.setMatricula(vl.getMatricula());
 				v.setModelo(vl.getModelo());
 				v.setUsuario(vl.getUsuario());
 				v.setViajes(vl.getViajes());
				listaAuxiliar.add(v);
 			}
 			return new ResponseEntity<List<VehiculoConIgnore>>(listaAuxiliar, HttpStatus.OK);
 		}else {
 			return new ResponseEntity<List<VehiculoConIgnore>>(HttpStatus.NOT_FOUND);
 		}	
	}
 	
 	//MOSTRAR VEHICULO POR MATRICULA
 	@RequestMapping(value = "/consultarVehiculoPorMatricula/{matricula}", method = RequestMethod.GET)
 	public ResponseEntity<VehiculoConIgnore>consultaVehiculoPorMatricula(@PathVariable("matricula") String matricula) {
 	 	
 		matricula=matricula.replaceAll("\\s","").toUpperCase();
			if(matricula.length()==7) {
				try {
					Integer.parseInt(matricula.substring(0, 4));
					boolean pruebaLetras=true;
					for(int i = 4 ; i < matricula.length() && pruebaLetras ; i++ ) {
						if(matricula.charAt(i)<'A'||matricula.charAt(i)>'Z') {
							pruebaLetras=false;
							return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);	
						}
					}
				}catch (NumberFormatException n){
					return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);	
				}
				
			}else {
				return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);	
			}
			
			Optional<Vehiculo> vOpt=VehiculoServicio.consultaVehiculoPorMatricula(matricula);
 	 		if(vOpt.isPresent()) {
 	 			VehiculoConIgnore vI=new VehiculoConIgnore();
 	 			vI.setUsuario(vOpt.get().getUsuario());
 	 			vI.setMatricula(matricula);
 	 			vI.setMarca(vOpt.get().getMarca());
 	 			vI.setModelo(vOpt.get().getModelo());
 	 			vI.setColor(vOpt.get().getColor());
 	 			vI.setCombustible(vOpt.get().getCombustible());
 	 			return new ResponseEntity<VehiculoConIgnore>(vI, HttpStatus.OK);
 	 		}else {
 	 			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
 	 		}
 	 		
 	}
 	
 	//ACTUALIZAR VEHICULO POR MATRICULA
 	@RequestMapping(value = "/actualizarVehiculo", method = RequestMethod.PUT)
	public ResponseEntity<VehiculoConIgnore> actualizaVehiculo(@Valid @RequestBody Map<String, String> param) {
 		try {
 			String auxMatriculaVehiculo=param.get("matricula");
 			Optional<Vehiculo>vOpt=VehiculoServicio.consultaVehiculoPorMatricula(auxMatriculaVehiculo);
 	 		if(vOpt.isPresent()) {
 	 			if(!param.get("marca").equals("")) {
 	 				vOpt.get().setMarca(param.get("marca"));
 	 			}
 	 			if(!param.get("modelo").equals("")) {
 	 				vOpt.get().setModelo(param.get("modelo"));
 	 			}
 	 			if(!param.get("combustible").equals("")) {
 	 				vOpt.get().setCombustible(param.get("combustible"));
 	 			}
 	 			if(!param.get("color").equals("")) {
 	 				vOpt.get().setColor(param.get("color"));
 	 			}
 	 			if(!param.get("fotovehiculo").equals("")) {
 	 				vOpt.get().setFotovehiculo(param.get("fotovehiculo"));
 	 			}
 	 			Vehiculo v=vOpt.get();
 	 			v = VehiculoServicio.actualizaVehiculo(v);
 	 			VehiculoConIgnore vI = new VehiculoConIgnore();
 		 		vI.setColor(v.getColor());
 		 		vI.setCombustible(v.getCombustible());
 		 		vI.setFechadealta(v.getFechadealta());
 				vI.setFotovehiculo(v.getFotovehiculo());
 				vI.setIdvehiculo(v.getIdvehiculo());
 				vI.setMarca(v.getMarca());
 				vI.setMatricula(v.getMatricula());
 				vI.setModelo(v.getModelo());
 				vI.setUsuario(v.getUsuario());
 				vI.setViajes(v.getViajes());
 	 			return ResponseEntity.ok(vI);
 	 		}else {
 	 			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
 	 		}
 		}catch(NumberFormatException n) {
 			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
 		}
 	}
 	
 	//ELIMINAR VEHICULO POR MATRICULA
 	//Metodo que recibe un Integer con el Id a del Producto a eliminar desde la ruta de la petición y lo elimina si existe
	@RequestMapping(value = "/eliminarVehiculoPorMatricula/{matricula}", method = RequestMethod.DELETE)//Asociamos la petición recibida la metodo de respuesta
	public ResponseEntity<Vehiculo> eliminarVehiculoPorId(@PathVariable("matricula") String matricula) {
		
		matricula=matricula.replaceAll("\\s","").toUpperCase();
		if(matricula.length()==7) {
			try {
				Integer.parseInt(matricula.substring(0, 4));
				boolean pruebaLetras=true;
				for(int i = 4 ; i < matricula.length() && pruebaLetras ; i++ ) {
					if(matricula.charAt(i)<'A'||matricula.charAt(i)>'Z') {
						pruebaLetras=false;
						return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);	
					}
				}
			}catch (NumberFormatException n){
				return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);	
			}
			
		}else {
			return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);	
		}
		
	    Optional<Vehiculo> vOpt=VehiculoServicio.consultaVehiculoPorMatricula(matricula);
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
	
	//--------------------------------------------------------------RESERVAS------------------------------------------------------------------------
	
	//REGISTRAR UNA RESERVA
	@RequestMapping(value = "/registrarReserva", method = RequestMethod.POST)
	public ResponseEntity<ReservaConIgnore> registraReserva(@RequestBody Map<String, String> param) {
 		Integer auxIdUsuario=Integer.parseInt(param.get("idUsuario"));
 		Optional<Usuario>uOpt=UsuarioServicio.consultaUsuarioPorId(auxIdUsuario);
 		Integer auxIdViaje=Integer.parseInt(param.get("idViaje"));
 		Optional<Viaje>vOpt=ViajeServicio.muestraViajePorId(auxIdViaje);
 		if(uOpt.isPresent()&&vOpt.isPresent()&&vOpt.get().getNumplazasdisponibles()>0) {
 			Reserva r = new Reserva();
 			ReservaId ri = new ReservaId();
 			ri.setIdusuariopasajero(uOpt.get().getIdusuario());
 			ri.setIdviaje(vOpt.get().getIdviaje());
 			r.setId(ri);
 			r.setUsuario(uOpt.get());
 			r.setViaje(vOpt.get());
			r=ReservaServicio.registraReserva(r);
			
			vOpt.get().setNumplazasdisponibles(vOpt.get().getNumplazasdisponibles()-1);
			ViajeServicio.actualizaViaje(vOpt.get());
			
			ReservaConIgnore rIg = new ReservaConIgnore();
			rIg.setFechareserva(r.getFechareserva());
			rIg.setId(r.getId());
			rIg.setUsuario(r.getUsuario());
			rIg.setViaje(r.getViaje());
			return new ResponseEntity<>(rIg, HttpStatus.OK);
			
 		}else {
 			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);	
 		}
	}
	
	//MOSTRAR RESERVA POR IDRESERVA
 	@RequestMapping(value = "/consultarReservaPorIdReserva/{id}", method = RequestMethod.GET)
	public ResponseEntity<ReservaConIgnore> consultaReservaPorIdReserva(@PathVariable("id") Integer id) {
 		Optional<Reserva>rOpt=ReservaServicio.muestraReservaPorIdReserva(id);
 		if(rOpt.isPresent()) {
			ReservaConIgnore rIg = new ReservaConIgnore();
			rIg.setFechareserva(rOpt.get().getFechareserva());
			rIg.setId(rOpt.get().getId());
			rIg.setUsuario(rOpt.get().getUsuario());
			rIg.setViaje(rOpt.get().getViaje());
 			return new ResponseEntity<ReservaConIgnore>(rIg, HttpStatus.OK);
 		}else {
 			return new ResponseEntity<ReservaConIgnore>(HttpStatus.NOT_FOUND);
 		}	
	}
 	
 	//MOSTRAR RESERVA POR IDUSUARIO e IDVIAJE
 	@RequestMapping(value = "/consultarReservaPorIdUsuarioYidViaje/{idUsuario}/{idViaje}", method = RequestMethod.GET)
	public ResponseEntity<ReservaConIgnore> consultaReservaPorIdUsuarioYidViaje(@PathVariable("idUsuario") Integer idUsuario, @PathVariable("idViaje") Integer idViaje) {
 		Optional<Reserva>rOpt=ReservaServicio.muestraReservaPorIdUsuarioYidViaje(idUsuario,idViaje);
 		if(rOpt.isPresent()) {
			ReservaConIgnore rIg = new ReservaConIgnore();
			rIg.setFechareserva(rOpt.get().getFechareserva());
			rIg.setId(rOpt.get().getId());
			rIg.setUsuario(rOpt.get().getUsuario());
			rIg.setViaje(rOpt.get().getViaje());
 			return new ResponseEntity<ReservaConIgnore>(rIg, HttpStatus.OK);
 		}else {
 			return new ResponseEntity<ReservaConIgnore>(HttpStatus.NOT_FOUND);
 		}	
	}
 	
 	//MOSTRAR RESERVAS POR IDUSUARIO
 	@RequestMapping(value = "/consultarReservasPorIdUsuario/{idUsuario}", method = RequestMethod.GET)
	public ResponseEntity <List<ReservaConIgnore>> consultaReservasPorIdUsuario(@PathVariable("idUsuario") Integer idUsuario) {
 		Optional <List<Reserva>> listOpt=ReservaServicio.muestraReservasPorIdUsuario(idUsuario);
 		if(listOpt.isPresent()) {
			List<ReservaConIgnore> listFinal = new ArrayList<ReservaConIgnore>();
			for (int i=0 ; i<listOpt.get().size() ; i++) {
				ReservaConIgnore rIg = new ReservaConIgnore();
				rIg.setFechareserva(listOpt.get().get(i).getFechareserva());
				rIg.setId(listOpt.get().get(i).getId());
				rIg.setUsuario(listOpt.get().get(i).getUsuario());
				rIg.setViaje(listOpt.get().get(i).getViaje());
				listFinal.add(rIg);
			}
 			return new ResponseEntity<List<ReservaConIgnore>>(listFinal, HttpStatus.OK);
 		}else {
 			return new ResponseEntity<List<ReservaConIgnore>>(HttpStatus.NOT_FOUND);
 		}	
	}
 	
 	//MOSTRAR RESERVAS POR IDVIAJE
 	@RequestMapping(value = "/consultarReservasPorIdViaje/{idViaje}", method = RequestMethod.GET)
	public ResponseEntity <List<ReservaConIgnore>> consultaReservasPorIdViaje(@PathVariable("idViaje") Integer idViaje) {
 		Optional <List<Reserva>> listOpt=ReservaServicio.muestraReservasPorIdViaje(idViaje);
 		if(listOpt.isPresent()) {
			List<ReservaConIgnore> listFinal = new ArrayList<ReservaConIgnore>();
			for (int i=0 ; i<listOpt.get().size() ; i++) {
				ReservaConIgnore rIg = new ReservaConIgnore();
				rIg.setFechareserva(listOpt.get().get(i).getFechareserva());
				rIg.setId(listOpt.get().get(i).getId());
				rIg.setUsuario(listOpt.get().get(i).getUsuario());
				rIg.setViaje(listOpt.get().get(i).getViaje());
				listFinal.add(rIg);
			}
 			return new ResponseEntity<List<ReservaConIgnore>>(listFinal, HttpStatus.OK);
 		}else {
 			return new ResponseEntity<List<ReservaConIgnore>>(HttpStatus.NOT_FOUND);
 		}	
	}
 	
 	//ELIMINAR RESERVA POR IDVIAJE E IDPASAJERO
	@RequestMapping(value = "/eliminarReservaIdViajeIdPasajero/{idViaje}/{idPasajero}", method = RequestMethod.DELETE)//Asociamos la petición recibida la metodo de respuesta
	public ResponseEntity<Reserva> eliminarReservaIdViajeIdPasajero(@PathVariable("idViaje") Integer idViaje, @PathVariable("idPasajero") Integer idPasajero) {
		//Obtenemos el viaje e usuario por el cual borrar:
 		Optional<Usuario>uOpt=UsuarioServicio.consultaUsuarioPorId(idPasajero);
 		Optional<Viaje>vOpt=ViajeServicio.muestraViajePorId(idViaje);
 		//Si estan presentes seguimos, si no devolvemos error de mal solicitud:
 		if(vOpt.isPresent()&&vOpt.isPresent()) {
 			//Comprobamos que el viaje del cual se va a cancelar la reserva no este caducado, sino se devuelve error 404:
            java.util.Date fechaViaje = vOpt.get().getFechasalida();
            java.util.Date fechaActual = new java.util.Date(Calendar.getInstance().getTime().getTime());
            if (fechaViaje.compareTo(fechaActual) > 0) {
                //Borramos la reserva de ese viaje y de ese usuario:
            	ReservaServicio.eliminarReservaIdViajeIdPasajero(idViaje, idPasajero);
            	//Liberamos la plaza no reservada, aumentando una mas:
            	int plazas = vOpt.get().getNumplazasdisponibles()+1;
            	vOpt.get().setNumplazasdisponibles(plazas);
            	ViajeServicio.actualizaViaje(vOpt.get());
            	return new ResponseEntity<>(null,HttpStatus.OK);
            }else {
            	return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
            }
 		}else {
 			return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
 		}
 		
	}
}
