package com.curso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.curso.model.Vuelo;
import com.curso.service.VuelosService;

/**
 * @author Herón Pliego Crespo
 * @version 1.0
 * @implNote Viva el Betis!
 * @sumary Controlador que maneja las solicitudes relacionadas con los vuelos.
 * 
 * - Anotación @RestController indica que esta clase es un controlador de Spring.
 * - Contiene métodos para manejar solicitudes HTTP relacionadas con vuelos, como obtener vuelos disponibles,
 *   reservar vuelos y obtener el precio total de un vuelo.
 * @see com.curso.service.VuelosService
 */
@RestController
public class VuelosController {
    @Autowired
    VuelosService service;

    /**
     * Devuelve la lista de vuelos disponibles para un número específico de plazas.
     * 
     * - Método HTTP: GET
     * - Ruta: /vuelos/disponibles/{plazas}
     * - Producción de tipo de contenido: JSON
     * - Parámetro: Número de plazas proporcionado como parte de la URL
     * - Llama al método vuelosDisp del servicio VuelosService para obtener la lista de vuelos disponibles.
     */
    @GetMapping(value = "vuelos/disponibles/{plazas}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Vuelo> vuelosDisp(@PathVariable("plazas") Integer plazas) {
        return service.vuelosDisp(plazas);
    }

    /**
     * Reserva un número específico de plazas en un vuelo.
     * 
     * - Método HTTP: PUT
     * - Ruta: /vuelos/{idVuelo}/reservar/{plazas}
     * - Parámetros: ID del vuelo y número de plazas proporcionados como parte de la URL
     * - Llama al método reservarVuelo del servicio VuelosService para realizar la reserva.
     */
    @PutMapping(value = "vuelos/{idVuelo}/reserva/{plazas}")
    public void reservarVuelo(@PathVariable("idVuelo") int idVuelo, @PathVariable("plazas") int plazas) {
        service.reservarVuelo(idVuelo, plazas);
    }

    /**
     * Devuelve el precio total del vuelo para el número de plazas solicitado.
     * 
     * - Método HTTP: GET
     * - Ruta: /vuelos/{idVuelo}/{plazas}
     * - Parámetros: ID del vuelo y número de plazas proporcionados como parte de la URL
     * - Llama al método obtenerPrecio del servicio VuelosService para calcular el precio total del vuelo.
     */
    @GetMapping(value = "/vuelos/{idVuelo}/{plazas}")
    public double obtenerPrecio(@PathVariable("idVuelo") int idVuelo, @PathVariable("plazas") int plazas) {
        return service.obtenerPrecio(idVuelo, plazas);
    }
}
