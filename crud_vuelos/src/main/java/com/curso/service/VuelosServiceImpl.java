package com.curso.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.dao.VuelosDao;
import com.curso.model.Vuelo;
/**
 * @author Herón Pliego Crespo
 * @version 1.0
 * @implNote Viva el Betis!
 * @sumary Servicio que implementa la lógica de negocios relacionada con los vuelos.
 * 
 * - Anotación @Service indica que esta clase es un componente de servicio gestionado por Spring.
 * - Contiene atributo autowired: VuelosDao, inyectado por Spring.
 */
@Service
public class VuelosServiceImpl implements VuelosService {
    @Autowired
    VuelosDao dao;

    /**
     * Busca un vuelo por su ID.
     * 
     * @param id ID del vuelo a buscar.
     * @return Vuelo encontrado o null si no existe.
     */
    public Vuelo buscarPorId(int id) {
        return dao.findById(id).orElse(null);
    }

    /**
     * Obtiene la lista de vuelos disponibles con un número mínimo de plazas.
     * 
     * @param plazas Número mínimo de plazas disponibles en los vuelos.
     * @return Lista de vuelos disponibles que cumplen con el criterio de plazas.
     */
    @Override
    public List<Vuelo> vuelosDisp(Integer plazas) {
        return dao.findDisponibleVuelos(plazas);
    }

    /**
     * Reserva un número específico de plazas en un vuelo.
     * 
     * @param idVuelo ID del vuelo a reservar.
     * @param plazas Número de plazas a reservar.
     */
    @Override
    public void reservarVuelo(int idVuelo, int plazas) {
        Vuelo vuelo = buscarPorId(idVuelo);
        vuelo.setPlazasdisp(vuelo.getPlazasdisp() - plazas);
        dao.save(vuelo);
    }

    /**
     * Obtiene el precio total de un vuelo para un número específico de plazas.
     * 
     * @param idVuelo ID del vuelo para el cual se obtendrá el precio.
     * @param plazas Número de plazas para calcular el precio total.
     * @return Precio total del vuelo.
     */
    @Override
    public double obtenerPrecio(int idVuelo, int plazas) {
        Vuelo vuelo = buscarPorId(idVuelo);
        double total = vuelo.getPrecio() * plazas;
        return total;
    }
}

