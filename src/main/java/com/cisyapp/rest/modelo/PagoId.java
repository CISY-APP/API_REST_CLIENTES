package com.cisyapp.rest.modelo;
// Generated 28-may-2020 20:41:23 by Hibernate Tools 5.2.12.Final

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * PagoId generated by hbm2java
 */
@Embeddable
public class PagoId implements java.io.Serializable {

	private int idpago;
	private int idusuariopasajero;
	private int idusuarioconductor;

	public PagoId() {
	}

	public PagoId(int idpago, int idusuariopasajero, int idusuarioconductor) {
		this.idpago = idpago;
		this.idusuariopasajero = idusuariopasajero;
		this.idusuarioconductor = idusuarioconductor;
	}

	@Column(name = "idpago", nullable = false)
	public int getIdpago() {
		return this.idpago;
	}

	public void setIdpago(int idpago) {
		this.idpago = idpago;
	}

	@Column(name = "idusuariopasajero", nullable = false)
	public int getIdusuariopasajero() {
		return this.idusuariopasajero;
	}

	public void setIdusuariopasajero(int idusuariopasajero) {
		this.idusuariopasajero = idusuariopasajero;
	}

	@Column(name = "idusuarioconductor", nullable = false)
	public int getIdusuarioconductor() {
		return this.idusuarioconductor;
	}

	public void setIdusuarioconductor(int idusuarioconductor) {
		this.idusuarioconductor = idusuarioconductor;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof PagoId))
			return false;
		PagoId castOther = (PagoId) other;

		return (this.getIdpago() == castOther.getIdpago())
				&& (this.getIdusuariopasajero() == castOther.getIdusuariopasajero())
				&& (this.getIdusuarioconductor() == castOther.getIdusuarioconductor());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getIdpago();
		result = 37 * result + this.getIdusuariopasajero();
		result = 37 * result + this.getIdusuarioconductor();
		return result;
	}

}
