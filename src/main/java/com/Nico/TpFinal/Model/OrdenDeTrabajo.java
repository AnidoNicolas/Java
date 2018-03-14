package com.Nico.TpFinal.Model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class OrdenDeTrabajo {
	@Id
	@GeneratedValue
	private int idOrden;
	private String nroPatente;
	private String fechaIngreso;
	@ManyToOne
	@JoinColumn(name="idPropietario")
	private Propietario propietario;
	@OneToMany(cascade = CascadeType.ALL,mappedBy="ordenDeTrabajo")
	List<RepOrd>lstRepOrdO=new ArrayList<RepOrd>();
	private String marca;
	private String detalle;
	private float costo;
	private boolean estado;
	
	
	public OrdenDeTrabajo(int id, String nroPatente, String fechaIngreso, Propietario propietario,
			List<RepOrd> lstRepOrdO, String marca, String detalle, float costo, boolean estado) {
		super();
		this.idOrden = id;
		this.nroPatente = nroPatente;
		this.fechaIngreso = fechaIngreso;
		this.propietario = propietario;
		this.lstRepOrdO = lstRepOrdO;
		this.marca = marca;
		this.detalle = detalle;
		this.costo = costo;
		this.estado = false;
	}


	public OrdenDeTrabajo() {
		// TODO Auto-generated constructor stub
	}


	public int getId() {
		return idOrden;
	}


	public void setId(int id) {
		this.idOrden = id;
	}


	public String getNroPatente() {
		return nroPatente;
	}


	public void setNroPatente(String nroPatente) {
		this.nroPatente = nroPatente;
	}


	public String getFechaIngreso() {
		return fechaIngreso;
	}


	public void setFechaIngreso(String fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}


	public Propietario getPropietario() {
		return propietario;
	}


	public void setPropietario(Propietario propietario) {
		this.propietario = propietario;
	}


	public List<RepOrd> getLstRepOrdO() {
		return lstRepOrdO;
	}


	public void setLstRepOrdO(List<RepOrd> lstRepOrdO) {
		this.lstRepOrdO = lstRepOrdO;
	}


	public String getMarca() {
		return marca;
	}


	public void setMarca(String marca) {
		this.marca = marca;
	}


	public String getDetalle() {
		return detalle;
	}


	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}


	public float getCosto() {
		return costo;
	}


	public void setCosto(float costo) {
		this.costo = costo;
	}


	public boolean isEstado() {
		return estado;
	}


	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
	
}
