package com.example.hospitality_booking_core.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PrenotazioneRequestDTO(

        @NotBlank(message = "Il nome dell'ospite è obbligatorio")
        String nomeOspite,

        @NotBlank(message = "L'email è obbligatoria")
        @Email(message = "Inserisci un indirizzo email valido")
        String emailOspite,

        @NotNull(message = "La data del check-In è obbligatoria")
        @FutureOrPresent(message = "Il check-In non può essere nel passato")
        LocalDate checkIn,

        @NotNull(message = "La data del check-Out è obbligatoria")
        @Future(message = "Il check-Out deve essere una data futura")
        LocalDate checkOut,

        @NotNull(message = "L'ID della camera è obbligatorio")
        Long cameraId

) {}
