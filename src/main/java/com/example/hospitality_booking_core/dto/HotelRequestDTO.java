package com.example.hospitality_booking_core.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record HotelRequestDTO(

        @NotBlank(message = "Il codice struttura è obbligatorio")
        String codiceStruttura,

        @NotBlank(message = "Il nome dell'hotel è obbligatorio")
        String nome,

        @NotBlank(message = "La citta è obbligatoria")
        String citta,

        @Min(value = 1, message = "Le stelle devono essere almeno 1")
        @Max(value = 5, message = "Le stelle devono essere massimo 5")
        Integer stelle

) {}
