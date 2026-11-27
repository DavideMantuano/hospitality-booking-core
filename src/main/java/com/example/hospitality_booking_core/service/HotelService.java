package com.example.hospitality_booking_core.service;

import com.example.hospitality_booking_core.dto.HotelRequestDTO;
import com.example.hospitality_booking_core.exception.BusinessException;
import com.example.hospitality_booking_core.model.Hotel;
import com.example.hospitality_booking_core.repository.HotelRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService {

    private final HotelRepository hotelRepository;

    public HotelService(HotelRepository hotelRepository) {

        this.hotelRepository=hotelRepository;
    }

    public Hotel creaHotel(HotelRequestDTO dto){
        if(hotelRepository.findByCodiceStruttura(dto.codiceStruttura()).isPresent()){
            throw new BusinessException("Esiste già un hotel con codice: " + dto.codiceStruttura());
        }
        Hotel hotel = new Hotel(dto.codiceStruttura(), dto.nome(), dto.citta(), dto.stelle());

        return hotelRepository.save(hotel);
    }

    public List<Hotel> trovaTutti() {
        return hotelRepository.findAll();
    }
}
