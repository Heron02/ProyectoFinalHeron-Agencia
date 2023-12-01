package com.curso.service;

import java.util.List;

import com.curso.model.Hotel;

public interface HotelesService {
	public List<Hotel> hotelesDisp(int clientes);
	public List<Integer> buscarHotel(String nombre);
	public void reservarHotel(int idHotel, int clientes);
	public double obtenerPrecio(int idHotel, int clientes);
	List<String> obtenerNombre(List<Integer> idHoteles);
	String obtenerNombreId(Integer id);
}
