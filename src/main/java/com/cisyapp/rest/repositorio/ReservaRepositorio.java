package com.cisyapp.rest.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cisyapp.rest.modelo.Reserva;

public interface ReservaRepositorio extends JpaRepository<Reserva, Integer>{

	
}
