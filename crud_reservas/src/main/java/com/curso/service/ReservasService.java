package com.curso.service;

import java.util.List;

import com.curso.model.Reserva;
import com.curso.model.ReservaInfo;

public interface ReservasService {
	public void reservar(Reserva reserva);
	public List<ReservaInfo> obtenerReservas(String nombre);
}
