package com.cisyapp.rest.servicio;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cisyapp.rest.modelo.Incidencia;
import com.cisyapp.rest.modelo.Usuario;
import com.cisyapp.rest.repositorio.IncidenciaRepositorio;
import com.cisyapp.rest.repositorio.UsuarioRepositorio;


@Service
public class IncidenciaServicio{
	
	@Autowired
	private IncidenciaRepositorio IncidenciaRepositorio;

	
	// Metodo utilizado para registrar una nueva incidencia.
	public Incidencia registrarIncidencia(Incidencia incidencia) {
		return IncidenciaRepositorio.save(incidencia);
	}

}
