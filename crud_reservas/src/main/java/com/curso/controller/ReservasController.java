package com.curso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.curso.model.Reserva;
import com.curso.model.ReservaInfo;
import com.curso.service.ReservasService;
/**
 * @author Herón Pliego Crespo
 * @version 1.0
 * @implNote Viva el Betis!
 * @see com.curso.service.ReservasService
 */
@RestController
public class ReservasController {
    @Autowired
    ReservasService service;

    /**
     * Endpoint para realizar una reserva.
     * 
     * - Método HTTP: POST
     * - Ruta: /reserva
     * - Consumo de tipo de contenido: JSON
     * - Parámetro: Objeto Reserva en el cuerpo de la solicitud
     * - Llama al método reservar del servicio ReservasService para procesar la reserva.
     */
    @PostMapping(value = "reserva", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void reservar(@RequestBody Reserva reserva) {
        service.reservar(reserva);
    }

    /**
     * Endpoint para obtener las reservas para un nombre de hotel dado.
     * 
     * - Método HTTP: GET
     * - Ruta: /reservas/{nombre}
     * - Producción de tipo de contenido: JSON
     * - Parámetro: Nombre del hotel proporcionado como parte de la URL
     * - Llama al método obtenerReservas del servicio ReservasService para obtener la lista de reservas.
     * - Retorna la lista de ReservaInfo en formato JSON.
     */
    @GetMapping(value = "reservas/{nombre}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ReservaInfo> obtenerReservas(@PathVariable("nombre") String nombre) {
        return service.obtenerReservas(nombre);
    }
}
