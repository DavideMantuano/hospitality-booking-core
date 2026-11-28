package com.example.hospitality_booking_core.controller;

import com.example.hospitality_booking_core.dto.HotelRequestDTO;
import com.example.hospitality_booking_core.model.Hotel;
import com.example.hospitality_booking_core.service.HotelService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hotel")
public class HotelController {
    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @PostMapping
    public ResponseEntity<Hotel> creaHotel(@Valid @RequestBody HotelRequestDTO dto){
        Hotel creato = hotelService.creaHotel(dto);
        return  ResponseEntity.status(HttpStatus.CREATED).body(creato);
    }

    @GetMapping
    public ResponseEntity<List<Hotel>> listaHotel() {
        return ResponseEntity.ok(hotelService.trovaTutti());
    }
}
