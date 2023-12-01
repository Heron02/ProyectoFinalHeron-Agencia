package com.curso.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.curso.inicio.Application;
import com.curso.model.Vuelo;

@SpringBootTest
@ContextConfiguration(classes = { Application.class })
class VuelosServiceImplTest {

    @Autowired
    VuelosService service;
    @Autowired
    VuelosServiceImpl serviceI;

    @Test
    void testBuscarPorId() {
        // Prueba para verificar que la búsqueda por ID devuelve el vuelo correcto o null si no existe.
        int idVueloExistente = 1;
        int idVueloNoExistente = -1;

        Vuelo vueloExistente = serviceI.buscarPorId(idVueloExistente);
        Vuelo vueloNoExistente = serviceI.buscarPorId(idVueloNoExistente);

        assertNotNull(vueloExistente);
        assertNull(vueloNoExistente);
    }

    @Test
    void testVuelosDisp() {
        // Prueba para verificar que la lista de vuelos disponibles no es nula y no está vacía.
        Integer plazas = 2;
        List<Vuelo> vuelos = service.vuelosDisp(plazas);

        assertNotNull(vuelos);
        assertFalse(vuelos.isEmpty());
    }

    @Test
    void testReservarVuelo() {
        // Prueba para verificar que la reserva de vuelo se realiza correctamente.
        int idVuelo = 1;
        int plazas = 3;

        // Guarda el estado actual del vuelo antes de reservar
        Vuelo vueloAntesDeReservar = serviceI.buscarPorId(idVuelo);

        // Realiza la reserva
        service.reservarVuelo(idVuelo, plazas);

        // Obtiene el estado del vuelo después de la reserva
        Vuelo vueloDespuesDeReservar = serviceI.buscarPorId(idVuelo);

        assertNotNull(vueloAntesDeReservar);
        assertNotNull(vueloDespuesDeReservar);
        assertEquals(vueloAntesDeReservar.getPlazasdisp() - plazas, vueloDespuesDeReservar.getPlazasdisp());
    }

    @Test
    void testObtenerPrecio() {
        // Prueba para verificar que el cálculo del precio total del vuelo es correcto.
        int idVuelo = 1;
        int plazas = 2;

        double precioTotal = service.obtenerPrecio(idVuelo, plazas);

        assertEquals(648.0, precioTotal, 0.01);
    }
}
