package com.curso.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.curso.model.Hotel;

/**
 * @summary Esta interfaz {@link HotelesDao} extiende de {@link JpaRepository} y
 *          responde consultas
 * @see {@link JpaRepository}
 * @return Listas de Hoteles
 */
public interface HotelesDao extends JpaRepository<Hotel, Integer> {
	/*
	 * Devuelve una lista de Hoteles disponibles para un número de clientes, por
	 * cada 2 clientes es una habitación
	 * @return List<Hotel> de los hoteles disponibles para ese numero de clientes
	 */
	@Query("SELECT h FROM Hotel h WHERE h.disponible = true AND h.habdisp >= FUNCTION('CEIL', :clientes / 2.0)")
	List<Hotel> findDisponibleHotels(@Param("clientes") int clientes);

	/*
	 * @return Devuelve una List<Hotel> que contengan o empiezen por el parámetro
	 * introducido
	 */
	List<Hotel> findByNombreIgnoreCaseContaining(String nombre);
}
