package com.example.hospitality_booking_core.controller;

import com.example.hospitality_booking_core.dto.CameraRequestDTO;
import com.example.hospitality_booking_core.model.Camera;
import com.example.hospitality_booking_core.service.CameraService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/camere")
public class CameraController {
    private final CameraService cameraService;

    public CameraController(CameraService cameraService) {
        this.cameraService = cameraService;
    }

    @PostMapping
    public ResponseEntity<Camera> creaCamera(@Valid @RequestBody CameraRequestDTO dto){
        Camera creata = cameraService.creaCamera(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creata);
    }

    @GetMapping("/disponibili/{hotelId}")
    public ResponseEntity<List<Camera>> listaDisponibili(@PathVariable Long hotelId) {
        return ResponseEntity.ok(cameraService.trovaDisponibiliPerHotel(hotelId));
    }
}
