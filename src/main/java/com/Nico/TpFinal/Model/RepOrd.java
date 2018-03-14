package com.Nico.TpFinal.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class RepOrd {

	@Id
	@GeneratedValue
	private int idRepOrd;
	
	@ManyToOne
	@JoinColumn(name="idOrden")
	private OrdenDeTrabajo ordenDeTrabajo;
	
	@ManyToOne
	@JoinColumn(name="idRepuesto")
	private Repuesto repuesto;

	private int cantidad;
	private int hsManoDeObra;
	
	public RepOrd(int idRepOrd, OrdenDeTrabajo ordenDeTrabajo, Repuesto repuesto, int cantidad, int hsManoDeObra) {
		super();
		this.idRepOrd = idRepOrd;
		this.ordenDeTrabajo = ordenDeTrabajo;
		this.repuesto = repuesto;
		this.cantidad = cantidad;
		this.hsManoDeObra = hsManoDeObra;
	}

	public RepOrd() {
		// TODO Auto-generated constructor stub
	}

	public int getIdRepOrd() {
		return idRepOrd;
	}

	public void setIdRepOrd(int idRepOrd) {
		this.idRepOrd = idRepOrd;
	}

	public OrdenDeTrabajo getOrdenDeTrabajo() {
		return ordenDeTrabajo;
	}

	public void setOrdenDeTrabajo(OrdenDeTrabajo ordenDeTrabajo) {
		this.ordenDeTrabajo = ordenDeTrabajo;
	}

	public Repuesto getRepuesto() {
		return repuesto;
	}

	public void setRepuesto(Repuesto repuesto) {
		this.repuesto = repuesto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public int getHsManoDeObra() {
		return hsManoDeObra;
	}

	public void setHsManoDeObra(int hsManoDeObra) {
		this.hsManoDeObra = hsManoDeObra;
	}
	
	public float subTotal(){
		float subtotal;
		subtotal=cantidad*repuesto.getPrecio();
		return subtotal;
	}
	
	public float calcularTotal(){
		float total=0;
		total=total+(repuesto.getPrecio()*cantidad);
		total=total+(hsManoDeObra*350);
		return total;
	}
}
