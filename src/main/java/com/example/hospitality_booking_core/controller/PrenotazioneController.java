package com.example.hospitality_booking_core.controller;

import com.example.hospitality_booking_core.dto.PrenotazioneRequestDTO;
import com.example.hospitality_booking_core.dto.PrenotazioneResponseDTO;
import com.example.hospitality_booking_core.service.PrenotazioneService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/prenotazioni")
public class PrenotazioneController {

    private final PrenotazioneService prenotazioneService;

    public PrenotazioneController(PrenotazioneService prenotazioneService) {
        this.prenotazioneService = prenotazioneService;
    }

    @PostMapping
    public ResponseEntity<PrenotazioneResponseDTO> creaPrenotazione(@Valid @RequestBody PrenotazioneRequestDTO dto) {
        PrenotazioneResponseDTO creata = prenotazioneService.creaPrenotazione(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creata);
    }

    @GetMapping("/{codice}")
    public ResponseEntity<PrenotazioneResponseDTO> cercaPrenotazione(@PathVariable String codice) {
        return ResponseEntity.ok(prenotazioneService.trovaPerCodice(codice));
    }

    @PatchMapping("/{codice}/cancella")
    public ResponseEntity<PrenotazioneResponseDTO> cancellaPrenotazione(@PathVariable String codice) {
        return ResponseEntity.ok(prenotazioneService.cancellaPrenotazione(codice));
    }
}
