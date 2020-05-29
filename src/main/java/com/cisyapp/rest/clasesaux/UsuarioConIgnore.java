package com.cisyapp.rest.clasesaux;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.cisyapp.rest.modelo.Pago;
import com.cisyapp.rest.modelo.Reserva;
import com.cisyapp.rest.modelo.Vehiculo;
import com.cisyapp.rest.modelo.Viaje;
import com.fasterxml.jackson.annotation.JsonIgnore;


public class UsuarioConIgnore implements java.io.Serializable {

	private Integer idusuario;
	private String nombre;
	private String apellidos;
	private Boolean espasajero;
	private Boolean esconductor;
	private Integer telefono;
	private String email;
	private String clave;
	private Date fechanacimiento;
	private String fotousuario;
	private String descripcion;
	private Date fecharegistro;
	private Date fechadesconexion;
	private Boolean sesioniniciada;
	private Set<Vehiculo> vehiculos = new HashSet<Vehiculo>(0);
	private Set<Reserva> reservas = new HashSet<Reserva>(0);
	private Set<Viaje> viajes = new HashSet<Viaje>(0);
	private Set<Pago> pagosForIdusuarioconductor = new HashSet<Pago>(0);
	private Set<Pago> pagosForIdusuariopasajero = new HashSet<Pago>(0);

	public UsuarioConIgnore() {
	}

	public UsuarioConIgnore(String nombre, String apellidos, String email, String clave, Date fecharegistro) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.clave = clave;
		this.fecharegistro = fecharegistro;
	}

	public UsuarioConIgnore(String nombre, String apellidos, Boolean espasajero, Boolean esconductor, Integer telefono,
			String email, String clave, Date fechanacimiento, String fotousuario, String descripcion,
			Date fecharegistro, Date fechadesconexion, Boolean sesioniniciada, Set<Vehiculo> vehiculos,
			Set<Reserva> reservas, Set<Viaje> viajes, Set<Pago> pagosForIdusuarioconductor,
			Set<Pago> pagosForIdusuariopasajero) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.espasajero = espasajero;
		this.esconductor = esconductor;
		this.telefono = telefono;
		this.email = email;
		this.clave = clave;
		this.fechanacimiento = fechanacimiento;
		this.fotousuario = fotousuario;
		this.descripcion = descripcion;
		this.fecharegistro = fecharegistro;
		this.fechadesconexion = fechadesconexion;
		this.sesioniniciada = sesioniniciada;
		this.vehiculos = vehiculos;
		this.reservas = reservas;
		this.viajes = viajes;
		this.pagosForIdusuarioconductor = pagosForIdusuarioconductor;
		this.pagosForIdusuariopasajero = pagosForIdusuariopasajero;
	}


	public Integer getIdusuario() {
		return this.idusuario;
	}

	public void setIdusuario(Integer idusuario) {
		this.idusuario = idusuario;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public Boolean getEspasajero() {
		return this.espasajero;
	}

	public void setEspasajero(Boolean espasajero) {
		this.espasajero = espasajero;
	}

	public Boolean getEsconductor() {
		return this.esconductor;
	}

	public void setEsconductor(Boolean esconductor) {
		this.esconductor = esconductor;
	}

	public Integer getTelefono() {
		return this.telefono;
	}

	public void setTelefono(Integer telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getClave() {
		return this.clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public Date getFechanacimiento() {
		return this.fechanacimiento;
	}

	public void setFechanacimiento(Date fechanacimiento) {
		this.fechanacimiento = fechanacimiento;
	}

	public String getFotousuario() {
		return this.fotousuario;
	}

	public void setFotousuario(String fotousuario) {
		this.fotousuario = fotousuario;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFecharegistro() {
		return this.fecharegistro;
	}

	public void setFecharegistro(Date fecharegistro) {
		this.fecharegistro = fecharegistro;
	}

	public Date getFechadesconexion() {
		return this.fechadesconexion;
	}

	public void setFechadesconexion(Date fechadesconexion) {
		this.fechadesconexion = fechadesconexion;
	}

	public Boolean getSesioniniciada() {
		return this.sesioniniciada;
	}

	public void setSesioniniciada(Boolean sesioniniciada) {
		this.sesioniniciada = sesioniniciada;
	}

	public Set<Vehiculo> getVehiculos() {
		return this.vehiculos;
	}

	public void setVehiculos(Set<Vehiculo> vehiculos) {
		this.vehiculos = vehiculos;
	}

	public Set<Reserva> getReservas() {
		return this.reservas;
	}

	public void setReservas(Set<Reserva> reservas) {
		this.reservas = reservas;
	}

	public Set<Viaje> getViajes() {
		return this.viajes;
	}

	public void setViajes(Set<Viaje> viajes) {
		this.viajes = viajes;
	}

	public Set<Pago> getPagosForIdusuarioconductor() {
		return this.pagosForIdusuarioconductor;
	}

	public void setPagosForIdusuarioconductor(Set<Pago> pagosForIdusuarioconductor) {
		this.pagosForIdusuarioconductor = pagosForIdusuarioconductor;
	}

	public Set<Pago> getPagosForIdusuariopasajero() {
		return this.pagosForIdusuariopasajero;
	}

	public void setPagosForIdusuariopasajero(Set<Pago> pagosForIdusuariopasajero) {
		this.pagosForIdusuariopasajero = pagosForIdusuariopasajero;
	}

}