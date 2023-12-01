package com.curso.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.curso.model.Vuelo;

/**
 * @sumary Interfaz que define métodos de acceso a datos para la entidad Vuelo.
 * 
 * - Extiende JpaRepository, proporcionando operaciones CRUD estándar para la entidad Vuelo.
 * - Utiliza anotaciones de Spring Data JPA como @Query para definir consultas personalizadas.
 */
public interface VuelosDao extends JpaRepository<Vuelo, Integer> {

    /**
     * Consulta personalizada para obtener una lista de vuelos disponibles con un número mínimo de plazas.
     * 
     * @param plazas Número mínimo de plazas disponibles en los vuelos.
     * @return Lista de vuelos que cumplen con el criterio de disponibilidad de plazas.
     */
    @Query("SELECT v FROM Vuelo v WHERE v.plazasdisp >= :plazas")
    List<Vuelo> findDisponibleVuelos(@Param("plazas") Integer plazas);
}

