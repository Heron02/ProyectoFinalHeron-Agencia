package com.curso.service;

import java.util.List;

import com.curso.model.Vuelo;

public interface VuelosService {
	public List<Vuelo> vuelosDisp(Integer plazas);
	public void reservarVuelo(int idVuelo, int plazas);
	double obtenerPrecio(int idVuelo, int plazas);
}
