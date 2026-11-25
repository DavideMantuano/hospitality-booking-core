package com.example.hospitality_booking_core.repository;

import com.example.hospitality_booking_core.model.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Optional<Prenotazione>> {
    Optional<Prenotazione> findByCodicePrenotazione(String codicePrenotazione);
}
