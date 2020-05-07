package com.cisyapp.rest.servicio;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cisyapp.rest.modelo.Usuario;
import com.cisyapp.rest.repositorio.UsuarioRepositorio;


@Service
public class UsuarioServicio{
	
	@Autowired
	private UsuarioRepositorio UsuarioRepositorio;

	
	// Metodo utilizado para registrar un nuevo cliente.
	public Usuario registrarUsuario(Usuario usuario) {
		return UsuarioRepositorio.save(usuario);
	}

	/*public List<Usuario> loginUsuario(String contrasena, String dni) {
		return UsuarioRepositorio.loginUsuario(contrasena, dni);
	}*/

	//Metodo para consultar filtrando por email
		public Optional<Usuario> consultaUsuario(String email){
			return UsuarioRepositorio.findByemail(email);
		}
}
