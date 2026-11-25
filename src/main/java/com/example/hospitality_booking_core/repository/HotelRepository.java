package com.example.hospitality_booking_core.repository;

import com.example.hospitality_booking_core.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HotelRepository extends JpaRepository<Hotel, Long> {

    Optional<Hotel> findByCodiceStruttura(String codiceStruttura);
}
