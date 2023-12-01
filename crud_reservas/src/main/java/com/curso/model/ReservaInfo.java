package com.curso.model;


public class ReservaInfo {
	private String nombreHotel;
	private int idvuelo;
	private String dni;
	private String nombre;
	private double totalprecio;

	public ReservaInfo() {
		super();
	}

	public ReservaInfo(String nombreHotel, int idvuelo, String dni, String nombre, double totalprecio) {
		super();
		this.nombreHotel = nombreHotel;
		this.idvuelo = idvuelo;
		this.dni = dni;
		this.nombre = nombre;
		this.totalprecio = totalprecio;
	}

	public String getNombreHotel() {
		return nombreHotel;
	}

	public void setNombreHotel(String nombreHotel) {
		this.nombreHotel = nombreHotel;
	}

	public int getIdvuelo() {
		return idvuelo;
	}

	public void setIdvuelo(int idvuelo) {
		this.idvuelo = idvuelo;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getTotalprecio() {
		return totalprecio;
	}

	public void setTotalprecio(double totalprecio) {
		this.totalprecio = totalprecio;
	}

}
