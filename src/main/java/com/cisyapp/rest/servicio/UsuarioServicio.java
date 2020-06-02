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
	
	// Metodo utilizado para registrar un nuevo usuario.
	public Usuario registraUsuario(Usuario usuario) {
		return UsuarioRepositorio.save(usuario);
	}

	//Metodo para consultar filtrando por email
	public Optional<Usuario> consultaUsuarioPorEmail(String email){
		return UsuarioRepositorio.findByEmail(email);
	}
		
	//Metodo para consultar filtrando por id
	public Optional<Usuario> consultaUsuarioPorId(Integer id){
		return UsuarioRepositorio.findById(id);
	}
	
	public Usuario actualizaUsuario(Usuario u) {
		return UsuarioRepositorio.save(u);
	}
	
	public Usuario eliminaUsuario(Usuario u) {
		UsuarioRepositorio.delete(u);
		return null;
	}
	
}
