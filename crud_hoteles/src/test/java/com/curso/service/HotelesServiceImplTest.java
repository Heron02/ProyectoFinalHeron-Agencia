package com.curso.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.curso.inicio.Application;
import com.curso.model.Hotel;

@SpringBootTest
@ContextConfiguration(classes = { Application.class })
class HotelesServiceImplTest {

    @Autowired
    HotelesService service;

    @Test
    void testHotelesDisp() {
        // Prueba para verificar que la lista de hoteles disponibles no es nula y no está vacía.
        int clientes = 3;
        List<Hotel> hoteles = service.hotelesDisp(clientes);

        assertNotNull(hoteles);
        assertFalse(hoteles.isEmpty());
    }

    @Test
    void testBuscarHotel() {
        // Prueba para verificar que la búsqueda de hoteles por nombre devuelve una lista de IDs.
        String nombreHotel = "NH";
        List<Integer> idsHoteles = service.buscarHotel(nombreHotel);

        assertNotNull(idsHoteles);
    }

    @Test
    void testReservarHotel() {
        // Prueba para verificar que la reserva de hotel se realiza correctamente.
        int idHotel = 1;
        int clientes = 2;

        // Guarda el estado actual del hotel antes de reservar
        Hotel hotelAntesDeReservar = service.hotelesDisp(clientes).stream()
                .filter(h -> h.getIdHotel() == idHotel)
                .findFirst()
                .orElse(null);

        // Realiza la reserva
        service.reservarHotel(idHotel, clientes);

        // Obtiene el estado del hotel después de la reserva
        Hotel hotelDespuesDeReservar = service.hotelesDisp(clientes).stream()
                .filter(h -> h.getIdHotel() == idHotel)
                .findFirst()
                .orElse(null);

        assertNotNull(hotelAntesDeReservar);
        assertNotNull(hotelDespuesDeReservar);
        assertEquals(hotelAntesDeReservar.getHabdisp() - Math.ceil(clientes / 2), hotelDespuesDeReservar.getHabdisp(), 0.01);
    }

    @Test
    void testObtenerPrecio() {
        // Prueba para verificar que el cálculo del precio total del hotel es correcto.
        int idHotel = 1;
        int clientes = 3;

        double precioTotal = service.obtenerPrecio(idHotel, clientes);

        assertEquals(1200.0, precioTotal, 0.01);
    }
}