package com.curso.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.curso.model.Reserva;

/**
 * @sumary Interfaz que define métodos de acceso a datos para la entidad Reserva.
 * 
 * - Extiende JpaRepository, proporcionando operaciones CRUD estándar para la entidad Reserva.
 * - Utiliza anotaciones de Spring Data JPA como @Query para definir consultas personalizadas.
 */
public interface ReservasDao extends JpaRepository<Reserva, Integer> {

    /**
     * Consulta personalizada para obtener una lista de reservas para un conjunto de identificadores de hoteles.
     * 
     * @param idHoteles Lista de identificadores de hoteles para filtrar las reservas.
     * @return Lista de reservas asociadas a los hoteles proporcionados.
     */
    @Query("SELECT r FROM Reserva r WHERE r.idhotel IN :idHoteles")
    List<Reserva> findByidhotel(@Param("idHoteles") List<Integer> idHoteles);
}

