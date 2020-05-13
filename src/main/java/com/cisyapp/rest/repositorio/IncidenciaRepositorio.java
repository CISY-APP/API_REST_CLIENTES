package com.cisyapp.rest.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cisyapp.rest.modelo.Incidencia;


public interface IncidenciaRepositorio extends JpaRepository<Incidencia, Integer>{
	
	
}