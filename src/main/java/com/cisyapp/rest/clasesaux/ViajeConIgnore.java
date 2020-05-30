package com.cisyapp.rest.clasesaux;


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.cisyapp.rest.modelo.Reserva;
import com.cisyapp.rest.modelo.Usuario;
import com.cisyapp.rest.modelo.Vehiculo;
//import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnore;


public class ViajeConIgnore implements java.io.Serializable{
	private Integer idviaje;
	private Usuario usuario;
	private Vehiculo vehiculo;
	private String localidadorigen;
	private String lugarsalida;
	private String localidaddestino;
	private String lugarllegada;
	private BigDecimal precio;
	private int numplazasdisponibles;
	private Date fechasalida;
	private Date fechacreacionviaje;
	private String matriculaError;
	private String usuarioError;
	private String vehiculoError;
	private Set<Reserva> reservas = new HashSet<Reserva>(0);

	public ViajeConIgnore() {
	}

	public ViajeConIgnore(Usuario usuario, Vehiculo vehiculo, String localidadorigen, String lugarsalida,
			String localidaddestino, String lugarllegada, int numplazasdisponibles, Date fechacreacionviaje) {
		this.usuario = usuario;
		this.vehiculo = vehiculo;
		this.localidadorigen = localidadorigen;
		this.lugarsalida = lugarsalida;
		this.localidaddestino = localidaddestino;
		this.lugarllegada = lugarllegada;
		this.numplazasdisponibles = numplazasdisponibles;
		this.fechacreacionviaje = fechacreacionviaje;
	}

	public ViajeConIgnore(Usuario usuario, Vehiculo vehiculo, String localidadorigen, String lugarsalida,
			String localidaddestino, String lugarllegada, BigDecimal precio, int numplazasdisponibles, Date fechasalida,
			Date fechacreacionviaje, Set<Reserva> reservas) {
		this.usuario = usuario;
		this.vehiculo = vehiculo;
		this.localidadorigen = localidadorigen;
		this.lugarsalida = lugarsalida;
		this.localidaddestino = localidaddestino;
		this.lugarllegada = lugarllegada;
		this.precio = precio;
		this.numplazasdisponibles = numplazasdisponibles;
		this.fechasalida = fechasalida;
		this.fechacreacionviaje = fechacreacionviaje;
		this.reservas = reservas;
	}

	public Integer getIdviaje() {
		return this.idviaje;
	}

	public void setIdviaje(Integer idviaje) {
		this.idviaje = idviaje;
	}

	@JsonIgnore
	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@JsonIgnore
	public Vehiculo getVehiculo() {
		return this.vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}


	public String getLocalidadOrigen() {
		return this.localidadorigen;
	}

	public void setLocalidadOrigen(String localidadorigen) {
		this.localidadorigen = localidadorigen;
	}

	public String getLugarSalida() {
		return this.lugarsalida;
	}

	public void setLugarSalida(String lugarsalida) {
		this.lugarsalida = lugarsalida;
	}


	public String getLocalidadDestino() {
		return this.localidaddestino;
	}

	public void setLocalidadDestino(String localidaddestino) {
		this.localidaddestino = localidaddestino;
	}

	public String getLugarLlegada() {
		return this.lugarllegada;
	}

	public void setLugarLlegada(String lugarllegada) {
		this.lugarllegada = lugarllegada;
	}

	public BigDecimal getPrecio() {
		return this.precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public int getNumplazasdisponibles() {
		return this.numplazasdisponibles;
	}

	public void setNumplazasdisponibles(int numplazasdisponibles) {
		this.numplazasdisponibles = numplazasdisponibles;
	}

	public Date getFechasalida() {
		return this.fechasalida;
	}

	public void setFechasalida(Date fechasalida) {
		this.fechasalida = fechasalida;
	}

	public Date getFechacreacionviaje() {
		return this.fechacreacionviaje;
	}

	public void setFechacreacionviaje(Date fechacreacionviaje) {
		this.fechacreacionviaje = fechacreacionviaje;
	}
	@JsonIgnore
	public Set<Reserva> getReservas() {
		return this.reservas;
	}

	public void setReservas(Set<Reserva> reservas) {
		this.reservas = reservas;
	}

	public String getMatriculaError() {
		return matriculaError;
	}

	public void setMatriculaError(String matriculaError) {
		this.matriculaError = matriculaError;
	}

	public String getUsuarioError() {
		return usuarioError;
	}

	public void setUsuarioError(String usuarioError) {
		this.usuarioError = usuarioError;
	}

	public String getVehiculoError() {
		return vehiculoError;
	}

	public void setVehiculoError(String vehiculoError) {
		this.vehiculoError = vehiculoError;
	}

}
