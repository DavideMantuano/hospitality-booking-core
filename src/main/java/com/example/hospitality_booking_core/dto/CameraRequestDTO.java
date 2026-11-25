package com.example.hospitality_booking_core.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record CameraRequestDTO(
        @NotBlank(message = "Il numero della camera è obbligatorio")
        String numeroCamera,

        @NotBlank(message = "La tipologia di camera è obbligatoria")
        String tipologia,

        @NotNull(message = "Il prezzo per notte è obbligatorio")
        @Positive(message = "Il prezzo deve essere maggiore di zero")
        BigDecimal prezzoPerNotte,

        @NotNull(message = "L'ID dell'hotel è obbligatorio")
        Long hotelId

) {}
