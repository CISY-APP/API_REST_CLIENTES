package com.cisyapp.rest.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cisyapp.rest.modelo.Vehiculo;
import com.cisyapp.rest.repositorio.VehiculoRepositorio;


@Service
public class VehiculoServicio{
	
	@Autowired
	private VehiculoRepositorio VehiculoRepositorio;

	
	//Metodo utilizado para registrar un nuevo vehiculo.
	public Vehiculo registraVehiculo(Vehiculo vehiculo) {
		return VehiculoRepositorio.save(vehiculo);
	}
	
	//Metodo utilizado para consultar un vehiculo por Id
	public Optional<Vehiculo> consultaVehiculoPorId(Integer idVehiculo) {
		return VehiculoRepositorio.findById(idVehiculo);
	}
	
	//Metodo para contar vehiculos filtrando por Id de Usuario
	public Integer cuentaVehiculoPorIdUsuario(Integer auxIdUsuario){
		return VehiculoRepositorio.cuentaVehiculosPorIdUsuario(auxIdUsuario);
	}
	
	//Metodo para consultar vehiculos filtrando por Id de Usuario
		public List<Vehiculo> consultaVehiculosPorIdUsuario(Integer auxIdUsuario){
			return VehiculoRepositorio.consultaVehiculosPorIdUsuario(auxIdUsuario);
		}
	
	//Metodo para consultar vehiculos filtrando por Id de Usuario
	public Optional<Vehiculo> consultaVehiculoPorMatricula(String matricula){
		return VehiculoRepositorio.findBymatricula(matricula);
		}
	
	
	//Metodo para actualizar los datos del vehiculo
	public Vehiculo actualizaVehiculo(Vehiculo v) {
		return VehiculoRepositorio.save(v);
	}
		
	//Metodo para eliminar Vehiculo
	public Vehiculo eliminaVehiculo(Vehiculo v) {
		VehiculoRepositorio.delete(v);
		return null;
		}



}
