package com.example.hospitality_booking_core.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.hospitality_booking_core.dto.HotelRequestDTO;
import com.example.hospitality_booking_core.exception.BusinessException;
import com.example.hospitality_booking_core.model.Hotel;
import com.example.hospitality_booking_core.repository.HotelRepository;

@ExtendWith(MockitoExtension.class)
class HotelServiceTest {

    @Mock
    private HotelRepository hotelRepository;

    @InjectMocks
    private HotelService hotelService;

    @Test
    void creaHotel_CodiceNuovo_SalvaCorrettamente() {

        // Arrange
        HotelRequestDTO dto = new HotelRequestDTO("ROMMC", "Grand Hotel Roma", "Roma", 5);
        Hotel hotelSalvato = new Hotel("ROMMC", "Grand Hotel Roma", "Roma", 5);

        when(hotelRepository.findByCodiceStruttura("ROMMC")).thenReturn(Optional.empty());
        when(hotelRepository.save(any(Hotel.class))).thenReturn(hotelSalvato);

        // Act
        Hotel risultato = hotelService.creaHotel(dto);

        // Assert
        assertNotNull(risultato);
        assertEquals("ROMMC", risultato.getCodiceStruttura());

        verify(hotelRepository).findByCodiceStruttura("ROMMC");
        verify(hotelRepository).save(any(Hotel.class));
    }

    @Test
    void creaHotel_CodiceDuplicato_LanciaBusinessException() {

        // Arrange
        HotelRequestDTO dto = new HotelRequestDTO("ROMMC", "Grand Hotel Roma", "Roma", 5);
        Hotel hotelEsistente = new Hotel("ROMMC", "Grand Hotel Roma", "Roma", 5);

        when(hotelRepository.findByCodiceStruttura("ROMMC")).thenReturn(Optional.of(hotelEsistente));

        // Act & Assert
        assertThrows(BusinessException.class, () -> hotelService.creaHotel(dto));

        verify(hotelRepository).findByCodiceStruttura("ROMMC");
        verify(hotelRepository, never()).save(any(Hotel.class));
    }
}