package com.curso.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.curso.inicio.Application;
import com.curso.model.Reserva;
import com.curso.model.ReservaInfo;

@SpringBootTest
@ContextConfiguration(classes = { Application.class })
class ReservasServiceImplTest {
	@Autowired
	ReservasService service;

	@Test
	void testReservar() {
		// Realiza una reserva ficticia y verifica que se haya guardado correctamente
				Reserva reserva = new Reserva(1, 1, "48786453T", "Miguel", 5);
				service.reservar(reserva);
				assertNotNull(reserva.getIdreserva());
	}

	@Test
	void testObtenerReservas() {
		List<ReservaInfo> reservas = service.obtenerReservas("NH");

		assertNotNull(reservas);
		assertFalse(reservas.isEmpty());
		assertTrue(reservas.size() > 0);
	}
}

