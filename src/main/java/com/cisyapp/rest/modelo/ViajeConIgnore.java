package com.cisyapp.rest.modelo;


import static javax.persistence.GenerationType.IDENTITY;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
* Viaje generated by hbm2java
*/
@Entity
@Table(name = "viaje", catalog = "cisy")

public class ViajeConIgnore implements java.io.Serializable{
	private Integer idviaje;
	private Usuario usuario;
	private Vehiculo vehiculo;
	private String origen;
	private String destino;
	private Integer codigoverificacion;
	private BigDecimal precio;
	private int numplazasdisponibles;
	private Date fechasalida;
	private Date horasalida;
	private Date fechacreacionviaje;
	private String error;
	private Set<Reserva> reservas = new HashSet<Reserva>(0);

	public ViajeConIgnore() {
	}

	public ViajeConIgnore(Usuario usuario, Vehiculo vehiculo, String origen, String destino, int numplazasdisponibles,
			Date fechacreacionviaje) {
		this.usuario = usuario;
		this.vehiculo = vehiculo;
		this.origen = origen;
		this.destino = destino;
		this.numplazasdisponibles = numplazasdisponibles;
		this.fechacreacionviaje = fechacreacionviaje;
	}

	public ViajeConIgnore(Usuario usuario, Vehiculo vehiculo, String origen, String destino, Integer codigoverificacion,
			BigDecimal precio, int numplazasdisponibles, Date fechasalida, Date horasalida, Date fechacreacionviaje,
			Set<Reserva> reservas) {
		this.usuario = usuario;
		this.vehiculo = vehiculo;
		this.origen = origen;
		this.destino = destino;
		this.codigoverificacion = codigoverificacion;
		this.precio = precio;
		this.numplazasdisponibles = numplazasdisponibles;
		this.fechasalida = fechasalida;
		this.horasalida = horasalida;
		this.fechacreacionviaje = fechacreacionviaje;
		this.reservas = reservas;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "idviaje", unique = true, nullable = false)
	public Integer getIdviaje() {
		return this.idviaje;
	}

	public void setIdviaje(Integer idviaje) {
		this.idviaje = idviaje;
	}
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idusuarioconductor", nullable = false)
	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idvehiculo", nullable = false)
	public Vehiculo getVehiculo() {
		return this.vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	@Column(name = "origen", nullable = false, length = 30)
	public String getOrigen() {
		return this.origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	@Column(name = "destino", nullable = false, length = 30)
	public String getDestino() {
		return this.destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	@Column(name = "codigoverificacion")
	public Integer getCodigoverificacion() {
		return this.codigoverificacion;
	}

	public void setCodigoverificacion(Integer codigoverificacion) {
		this.codigoverificacion = codigoverificacion;
	}

	@Column(name = "precio", precision = 4)
	public BigDecimal getPrecio() {
		return this.precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	@Column(name = "numplazasdisponibles", nullable = false)
	public int getNumplazasdisponibles() {
		return this.numplazasdisponibles;
	}

	public void setNumplazasdisponibles(int numplazasdisponibles) {
		this.numplazasdisponibles = numplazasdisponibles;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fechasalida", length = 10)
	public Date getFechasalida() {
		return this.fechasalida;
	}

	public void setFechasalida(Date fechasalida) {
		this.fechasalida = fechasalida;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "horasalida", length = 19)
	public Date getHorasalida() {
		return this.horasalida;
	}

	public void setHorasalida(Date horasalida) {
		this.horasalida = horasalida;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fechacreacionviaje", nullable = false, length = 19)
	public Date getFechacreacionviaje() {
		return this.fechacreacionviaje;
	}

	public void setFechacreacionviaje(Date fechacreacionviaje) {
		this.fechacreacionviaje = fechacreacionviaje;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "viaje")
	public Set<Reserva> getReservas() {
		return this.reservas;
	}

	public void setReservas(Set<Reserva> reservas) {
		this.reservas = reservas;
	}
	
	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

}
