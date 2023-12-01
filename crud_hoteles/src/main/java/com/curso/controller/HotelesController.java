package com.curso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.curso.model.Hotel;
import com.curso.service.HotelesService;

/**
 * @author Herón Pliego Crespo
 * @version 1.0
 * @implNote El Nano manda #33
 * 
 * @summary En esta clase{@link HotelesController} controladora de hoteles
 *          respondera una petición del cliente y responderá al otro servicio
 *          llamado reservas. Contiene métodos para manejar solicitudes HTTP relacionadas con hoteles, 
 *          como obtener hoteles disponibles,buscar un hotel por nombre, realizar reservas y obtener 
 *          el precio total de un hotel.
 * @return Listas de hoteles y Precio(double) de hoteles por número de personas
 */
@RestController
public class HotelesController {
    @Autowired
    HotelesService service;

    /**
     * Devuelve la lista de hoteles disponibles para un número específico de clientes.
     * 
     * - Método HTTP: GET
     * - Ruta: /hoteles/disponibles/{clientes}
     * - Producción de tipo de contenido: JSON
     * - Parámetro: Número de clientes proporcionado como parte de la URL
     * - Llama al método hotelesDisp del servicio HotelesService para obtener la lista de hoteles disponibles.
     */
    @GetMapping(value = "/hoteles/disponibles/{clientes}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Hotel> hoteles(@PathVariable("clientes") int clientes) {
        return service.hotelesDisp(clientes);
    }

    /**
     * Devuelve la ID del hotel con el nombre introducido por parámetro.
     * 
     * - Método HTTP: GET
     * - Ruta: /hoteles/{nombre}
     * - Parámetro: Nombre del hotel proporcionado como parte de la URL
     * - Llama al método buscarHotel del servicio HotelesService para obtener la lista de IDs de hoteles que coinciden con el nombre.
     */
    @GetMapping(value = "hoteles/{nombre}")
    public List<Integer> obtenerIdHotel(@PathVariable("nombre") String nombre) {
    	
        return service.buscarHotel(nombre);
    }

    /**
     * Método utilizado para dar servicio a reservas, resta la cantidad de habitaciones para un número de clientes.
     * 
     * - Método HTTP: PUT
     * - Ruta: /hoteles/{idHotel}/reserva/{clientes}
     * - Parámetros: ID del hotel y número de clientes proporcionados como parte de la URL
     * - Llama al método reservarHotel del servicio HotelesService para realizar la reserva y actualizar la disponibilidad de habitaciones.
     */
    @PutMapping(value = "/hoteles/{idHotel}/reserva/{clientes}")
    public void reservarHotel(@PathVariable("idHotel") int idHotel, @PathVariable("clientes") int clientes) {
        service.reservarHotel(idHotel, clientes);
    }

    /**
     * Devuelve el precio total del hotel para el número de clientes solicitado.
     * 
     * - Método HTTP: GET
     * - Ruta: /hoteles/{idHotel}/{clientes}
     * - Parámetros: ID del hotel y número de clientes proporcionados como parte de la URL
     * - Llama al método obtenerPrecio del servicio HotelesService para calcular el precio total del hotel.
     */
    @GetMapping(value = "/hoteles/{idHotel}/{clientes}")
    public double obtenerPrecio(@PathVariable("idHotel") int idHotel, @PathVariable("clientes") int clientes) {
        return service.obtenerPrecio(idHotel, clientes);
    }
    
    @GetMapping(value = "/hoteles/id/{id}")
    public String obtenerNombreId(@PathVariable("id")Integer id) {
    	return service.obtenerNombreId(id);
    }
}
