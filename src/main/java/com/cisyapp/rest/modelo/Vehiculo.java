package com.cisyapp.rest.modelo;
// Generated 28-may-2020 20:41:23 by Hibernate Tools 5.2.12.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Vehiculo generated by hbm2java
 */
@Entity
@Table(name = "vehiculo", catalog = "cisy", uniqueConstraints = @UniqueConstraint(columnNames = "matricula"))
public class Vehiculo implements java.io.Serializable {

	private Integer idvehiculo;
	private Usuario usuario;
	private String matricula;
	@NotNull
	@Size(min=1, max=30)
	private String modelo;
	@NotNull
	@Size(min=1, max=30)
	private String color;
	@NotNull
	@Size(min=1, max=20)
	private String combustible;
	@NotNull
	@Size(min=1, max=30)
	private String marca;
	private String fotovehiculo;
	private Date fechadealta;
	private Set<Viaje> viajes = new HashSet<Viaje>(0);

	public Vehiculo() {
	}

	public Vehiculo(Usuario usuario, String matricula, Date fechadealta) {
		this.usuario = usuario;
		this.matricula = matricula;
		this.fechadealta = fechadealta;
	}

	public Vehiculo(Usuario usuario, String matricula, String modelo, String color, String combustible, String marca,
			String fotovehiculo, Date fechadealta, Set<Viaje> viajes) {
		this.usuario = usuario;
		this.matricula = matricula;
		this.modelo = modelo;
		this.color = color;
		this.combustible = combustible;
		this.marca = marca;
		this.fotovehiculo = fotovehiculo;
		this.fechadealta = fechadealta;
		this.viajes = viajes;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "idvehiculo", unique = true, nullable = false)
	public Integer getIdvehiculo() {
		return this.idvehiculo;
	}

	public void setIdvehiculo(Integer idvehiculo) {
		this.idvehiculo = idvehiculo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idusuario", nullable = false)
	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Column(name = "matricula", unique = true, nullable = false, length = 10)
	public String getMatricula() {
		return this.matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	@Column(name = "modelo", length = 30)
	public String getModelo() {
		return this.modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	@Column(name = "color", length = 30)
	public String getColor() {
		return this.color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Column(name = "combustible", length = 20)
	public String getCombustible() {
		return this.combustible;
	}

	public void setCombustible(String combustible) {
		this.combustible = combustible;
	}

	@Column(name = "marca", length = 30)
	public String getMarca() {
		return this.marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	@Column(name = "fotovehiculo", length = 500)
	public String getFotovehiculo() {
		return this.fotovehiculo;
	}

	public void setFotovehiculo(String fotovehiculo) {
		this.fotovehiculo = fotovehiculo;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fechadealta", nullable = false, length = 19)
	public Date getFechadealta() {
		return this.fechadealta;
	}

	public void setFechadealta(Date fechadealta) {
		this.fechadealta = fechadealta;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "vehiculo")
	public Set<Viaje> getViajes() {
		return this.viajes;
	}

	public void setViajes(Set<Viaje> viajes) {
		this.viajes = viajes;
	}

}
