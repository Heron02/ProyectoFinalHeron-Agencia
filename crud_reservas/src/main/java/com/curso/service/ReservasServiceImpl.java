package com.curso.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import com.curso.dao.ReservasDao;
import com.curso.model.Reserva;
import com.curso.model.ReservaInfo;

/**
 * @author Herón Pliego Crespo
 * @version 1.0
 * @implNote Viva el Betis!
 * 
 * Servicio que implementa la lógica de negocios relacionada con las reservas.
 * 
 * - Anotación @Service indica que esta clase es un componente de servicio gestionado por Spring.
 * - Contiene atributos autowired: ReservasDao y RestTemplate, inyectados por Spring.
 * - Incluye dos URLs que representan las direcciones de los servicios web para hoteles y vuelos.
 * @see com.curso.service.ReservasService
 */
@Service
public class ReservasServiceImpl implements ReservasService {
    @Autowired
    ReservasDao dao;
    @Autowired
    RestTemplate template;

    private static String url = "http://localhost:8080/";
    private static String url2 = "http://localhost:7000/";

    /**
     * Realiza una reserva combinando la información de vuelos y hoteles.
     * - Obtiene el precio del vuelo y del hotel mediante llamadas a servicios web externos.
     * - Realiza reservas en hoteles y vuelos.
     * - Calcula el precio total de la reserva sumando los precios del vuelo y del hotel.
     * - Guarda la reserva en la base de datos utilizando el objeto dao.
     * @param Reserva reserva a añadir
     */
    @Override
    public void reservar(Reserva reserva) {
        int idHotel = reserva.getIdhotel();
        int idVuelo = reserva.getIdvuelo();
        double precioVuelo = template.getForObject(url2 + "vuelos/" + idVuelo + "/" + reserva.getTotalpers(), Double.class);
        double precioHotel = template.getForObject(url + "hoteles/" + idHotel + "/" + reserva.getTotalpers(), Double.class);
        template.put(url + "/hoteles/" + idHotel + "/reserva/" + reserva.getTotalpers(), Integer.class);
        template.put(url2 + "/vuelos/" + idVuelo + "/reserva/" + reserva.getTotalpers(), Integer.class);
        reserva.setTotalprecio(precioVuelo + precioHotel);
        dao.save(reserva);
    }

    /**
     * Obtiene las reservas para un nombre de hotel dado.
     * - Obtiene una lista de identificadores de hoteles mediante una llamada a un servicio web externo.
     * - Utiliza el objeto dao para obtener una lista de reservas para los hoteles encontrados.
     * - Convierte la lista de reservas a una lista de ReservaInfo utilizando el método convertirAReservaInfo.
     * @param String nombre de las reservas con el nombre de un hotel
     * @return reservasInfoList
     */
    @Override
    public List<ReservaInfo> obtenerReservas(String nombre) {
        @SuppressWarnings("unchecked")
        List<Integer> idHoteles = template.getForObject(url + "hoteles/" + nombre, List.class);
        List<Reserva> reservas = dao.findByidhotel(idHoteles);
        List<ReservaInfo> reservasInfoList = convertirAReservaInfo(reservas);
        return reservasInfoList;
    }

    /**
     * Convierte una lista de objetos Reserva a una lista de objetos ReservaInfo.
     * @param List<Reserva> Lista de reservas para convertir en ReservasInfo
     * @return reservasInfoList
     */
    public List<ReservaInfo> convertirAReservaInfo(List<Reserva> reservas) {
        List<ReservaInfo> reservasInfoList = new ArrayList<>();
        
        for (Reserva reserva : reservas) {
        	
        	String nombreHotel = template.getForObject(url + "hoteles/id/" + (Integer)reserva.getIdhotel() , String.class);
            ReservaInfo reservaInfo = convertirReservaAReservaInfo(reserva, nombreHotel);
            reservaInfo.setNombreHotel(nombreHotel);
            reservasInfoList.add(reservaInfo);
        }

        return reservasInfoList;
    }

    /**
     * Convierte un objeto Reserva a un objeto ReservaInfo utilizando un HashMap.
     * @param Reserva para convertir
     * @return reservasInfoList
     */
    public static ReservaInfo convertirReservaAReservaInfo(Reserva reserva, String nombreHotel) {
        Map<String, Object> reservaMap = new HashMap<>();
        reservaMap.put("nombreHotel", nombreHotel);
        reservaMap.put("idvuelo", reserva.getIdvuelo());
        reservaMap.put("dni", reserva.getDni());
        reservaMap.put("nombre", reserva.getNombre());
        reservaMap.put("totalprecio", reserva.getTotalprecio());

        // Crear un objeto ReservaInfo utilizando el HashMap
        return new ReservaInfo((String) reservaMap.get("nombreHotel"), (int) reservaMap.get("idvuelo"), (String) reservaMap.get("dni"),
                (String) reservaMap.get("nombre"), (double) reservaMap.get("totalprecio"));
    }
}
