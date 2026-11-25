package com.example.hospitality_booking_core.dto;

import com.example.hospitality_booking_core.model.StatoPrenotazione;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PrenotazioneResponseDTO(

        String codicePrenotazione,
        String nomeOspite,
        String nomeHotel,
        String numeroCamera,
        LocalDate checkIn,
        LocalDate checkOut,
        BigDecimal costoTotale,
        StatoPrenotazione stato
) {}
