package com.curso.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "reservas")
public class Reserva {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idreserva;
	private int idhotel;
	private int idvuelo;
	private String dni;
	private String nombre;
	private int totalpers;
	private double totalprecio;

	public Reserva() {
		super();
	}

	public Reserva(int idhotel, int idvuelo, String dni, String nombre, int totalpers) {
		super();
		this.idhotel = idhotel;
		this.idvuelo = idvuelo;
		this.dni = dni;
		this.nombre = nombre;
		this.totalpers = totalpers;
	}

	public int getIdreserva() {
		return idreserva;
	}
	
	public int getIdhotel() {
		return idhotel;
	}

	public int getIdvuelo() {
		return idvuelo;
	}

	public String getDni() {
		return dni;
	}

	public String getNombre() {
		return nombre;
	}

	public int getTotalpers() {
		return totalpers;
	}

	public void setTotalpers(int totalpers) {
		this.totalpers = totalpers;
	}

	public double getTotalprecio() {
		return totalprecio;
	}

	public void setTotalprecio(double totalprecio) {
		this.totalprecio = totalprecio;
	}

}
