package com.Nico.TpFinal.Model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Repuesto {

	@Id
	private int idRepuesto;
	private String nombre;
	private float precio;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy="repuesto")
	List<RepOrd>lstRepOrdR=new ArrayList<RepOrd>();

	public Repuesto(int idRepuesto, float precio, List<RepOrd> lstRepOrdR,String nombre) {
		super();
		this.idRepuesto = idRepuesto;
		this.precio = precio;
		this.lstRepOrdR = lstRepOrdR;
		this.nombre=nombre;
	}

	public Repuesto() {
		// TODO Auto-generated constructor stub
	}

	public int getIdRepuesto() {
		return idRepuesto;
	}

	public void setIdRepuesto(int idRepuesto) {
		this.idRepuesto = idRepuesto;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public List<RepOrd> getLstRepOrdR() {
		return lstRepOrdR;
	}

	public void setLstRepOrdR(List<RepOrd> lstRepOrdR) {
		this.lstRepOrdR = lstRepOrdR;
	}
	
	public String getNombre(){
		return nombre;
	}
	
	public void setNombre(String nombre){
		this.nombre=nombre;
	}

	
	
	
}
