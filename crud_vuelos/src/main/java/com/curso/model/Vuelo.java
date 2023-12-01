package com.curso.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "vuelos")
public class Vuelo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idvuelo;
	private String company;
	private LocalDate fecvuelo;
	private double precio;
	private int plazasdisp;

	public Vuelo() {
		super();
	}

	public Vuelo(int idvuelo, String nombre, LocalDate fecvuelo, double precio, int plazasdisp) {
		super();
		this.idvuelo = idvuelo;
		this.company = nombre;
		this.fecvuelo = fecvuelo;
		this.precio = precio;
		this.plazasdisp = plazasdisp;
	}

	public int getIdvuelo() {
		return idvuelo;
	}

	public void setIdvuelo(int idvuelo) {
		this.idvuelo = idvuelo;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String nombre) {
		this.company = nombre;
	}

	public LocalDate getFecvuelo() {
		return fecvuelo;
	}

	public void setFecvuelo(LocalDate fecvuelo) {
		this.fecvuelo = fecvuelo;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getPlazasdisp() {
		return plazasdisp;
	}

	public void setPlazasdisp(int plazasdisp) {
		this.plazasdisp = plazasdisp;
	}

}
