package com.curso.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.dao.HotelesDao;
import com.curso.model.Hotel;

/**
 * @author Herón Pliego Crespo
 * @version 1.0
 * @implNote Viva el Betis!
 * 
 * Servicio que implementa la lógica de negocios relacionada con los hoteles.
 * 
 * - Anotación @Service indica que esta clase es un componente de servicio gestionado por Spring.
 * - Contiene métodos para realizar consultas y operaciones en la base de datos a través de HotelesDao.
 * - Proporciona funcionalidades como obtener hoteles disponibles, buscar hoteles por nombre,
 *   realizar reservas de habitaciones y obtener el precio total de un hotel para un número de clientes.
 * @see com.curso.service.HotelesService
 * @see com.curso.dao.HotelesDao
 */
@Service
public class HotelesServiceImpl implements HotelesService {
    @Autowired
    HotelesDao dao;

    /**
     * Obtiene una lista de hoteles disponibles para un número específico de clientes.
     * 
     * @param clientes Número de clientes para los que se busca disponibilidad.
     * @return Lista de hoteles disponibles.
     */
    @Override
    public List<Hotel> hotelesDisp(int clientes) {
        return dao.findDisponibleHotels(clientes);
    }

    /**
     * Busca hoteles por nombre y devuelve una lista de IDs de hoteles cuyos nombres
     * contienen o empiezan por el parámetro proporcionado.
     * 
     * @param nombre Nombre o parte del nombre del hotel a buscar.
     * @return Lista de IDs de hoteles que coinciden con la búsqueda.
     */
    @Override
    public List<Integer> buscarHotel(String nombre) {
        List<Integer> listaHoteles = new ArrayList<>();
        List<Hotel> hoteles = dao.findByNombreIgnoreCaseContaining(nombre);
        for (Hotel hotel : hoteles) {
            listaHoteles.add(hotel.getIdHotel());
        }
        if (listaHoteles.isEmpty()) {
            return null;
        } else {
            return listaHoteles;
        }
    }

    /**
     * Modifica el número de habitaciones disponibles al reservar. Si este es 0,
     * el hotel ya no estará disponible.
     * 
     * @param idHotel   ID del hotel que se va a reservar.
     * @param clientes  Número de clientes para los que se realiza la reserva.
     */
    @Override
    public void reservarHotel(int idHotel, int clientes) {
        List<Hotel> hotelesDisp = hotelesDisp(clientes);
        for (Hotel h : hotelesDisp) {
            if (h.getIdHotel() == idHotel) {
                h.setHabdisp((int) (h.getHabdisp() - Math.ceil(clientes / 2)));
                if (h.getHabdisp() == 0) {
                    h.setDisponible(false);
                }
                dao.save(h);
                break;
            } else {
                break;
            }
        }
    }

    /**
     * Obtiene el precio total del hotel para un número específico de clientes.
     * 
     * @param idHotel   ID del hotel para el cual se calcula el precio.
     * @param clientes  Número de clientes para los que se calcula el precio.
     * @return Precio total del hotel para el número de clientes especificado.
     */
    @Override
    public double obtenerPrecio(int idHotel, int clientes) {
        Hotel hotel = dao.findById(idHotel).orElse(null);
        if (hotel != null) {
            return hotel.getPrecio() * clientes;
        } else {
            return 0.0;
        }
    }
    
    @Override
    public List<String> obtenerNombre(List<Integer> idHoteles) {
        List<String> hNombres = new ArrayList<>();

        for (Integer id : idHoteles) {
            Hotel hotel = dao.findById(id).orElse(null);

            if (hotel != null) {
                String hotelNombre = hotel.getNombre();
                hNombres.add(hotelNombre);
            } else {
                // Puedes manejar el caso en el que el hotel no se encuentra
                // Puedes agregar un valor predeterminado o lanzar una excepción, según tus necesidades
                hNombres.add("Hotel no encontrado para ID: " + id);
            }
        }
		return hNombres;
    }
    @Override
    public String obtenerNombreId(Integer id) {
    	Hotel hotel = dao.findById(id).orElse(null);
    	String nombre = hotel.getNombre();
		return nombre;
    }
    }
