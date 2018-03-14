package com.Nico.TpFinal.Model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Propietario {

	@Id
	private long dni;
	private String nombre;
	private String apellido;
	private String direccion;
	private long telefono;
	@OneToMany(cascade = CascadeType.ALL,mappedBy="propietario")
	List<OrdenDeTrabajo>lstODTP=new ArrayList<OrdenDeTrabajo>();
	
	public Propietario(long dni, String nombre, String apellido, String direccion, long telefono,
			List<OrdenDeTrabajo> lstODTP) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.direccion = direccion;
		this.telefono = telefono;
		this.lstODTP = lstODTP;
	}
	
	public Propietario() {
		// TODO Auto-generated constructor stub
	}

	public long getDni() {
		return dni;
	}
	public void setDni(long dni) {
		this.dni = dni;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public long getTelefono() {
		return telefono;
	}
	public void setTelefono(long telefono) {
		this.telefono = telefono;
	}
	public List<OrdenDeTrabajo> getLstODTP() {
		return lstODTP;
	}
	public void setLstODTP(List<OrdenDeTrabajo> lstODTP) {
		this.lstODTP = lstODTP;
	}
}
