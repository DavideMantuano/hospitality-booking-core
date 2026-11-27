package com.example.hospitality_booking_core.service;

import com.example.hospitality_booking_core.dto.PrenotazioneRequestDTO;
import com.example.hospitality_booking_core.dto.PrenotazioneResponseDTO;
import com.example.hospitality_booking_core.exception.BusinessException;
import com.example.hospitality_booking_core.model.Camera;
import com.example.hospitality_booking_core.model.Prenotazione;
import com.example.hospitality_booking_core.model.StatoPrenotazione;
import com.example.hospitality_booking_core.repository.CameraRepository;
import com.example.hospitality_booking_core.repository.PrenotazioneRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Service
public class PrenotazioneService {

    private final PrenotazioneRepository prenotazioneRepository;
    private final CameraRepository cameraRepository;

    public PrenotazioneService(PrenotazioneRepository prenotazioneRepository, CameraRepository cameraRepository) {
        this.prenotazioneRepository = prenotazioneRepository;
        this.cameraRepository = cameraRepository;
    }

    @Transactional
    public PrenotazioneResponseDTO creaPrenotazione(PrenotazioneRequestDTO dto) {
        if (!dto.checkOut().isAfter(dto.checkIn())) {
            throw new BusinessException("La data di check-out deve essere successiva al check-in");
        }

        Camera camera = cameraRepository.findById(dto.cameraId())
                .orElseThrow(() -> new BusinessException("Camera non trovata con ID: " + dto.cameraId()));

        if (!camera.isDisponibile()) {
            throw new BusinessException("La camera " + camera.getNumeroCamera() + " non è al momento disponibile");
        }

        long notti = ChronoUnit.DAYS.between(dto.checkIn(), dto.checkOut());
        BigDecimal costoTotale = camera.getPrezzoPerNotte().multiply(BigDecimal.valueOf(notti));

        String codice = "HTL-" + UUID.randomUUID().toString().substring(0, 6).toUpperCase();

        Prenotazione prenotazione = new Prenotazione();
        prenotazione.setCodicePrenotazione(codice);
        prenotazione.setNomeOspite(dto.nomeOspite());
        prenotazione.setEmailOspite(dto.emailOspite());
        prenotazione.setCheckIn(dto.checkIn());
        prenotazione.setCheckOut(dto.checkOut());
        prenotazione.setCostoTotale(costoTotale);
        prenotazione.setStato(StatoPrenotazione.CONFERMATA);
        prenotazione.setCamera(camera);

        camera.setDisponibile(false);
        cameraRepository.save(camera);
        Prenotazione salvata = prenotazioneRepository.save(prenotazione);

        return mappaInResponseDTO(salvata);
    }

    public PrenotazioneResponseDTO trovaPerCodice(String codicePrenotazione) {
        Prenotazione prenotazione = prenotazioneRepository.findByCodicePrenotazione(codicePrenotazione)
                .orElseThrow(() -> new BusinessException("Nessuna prenotazione trovata con codice: " + codicePrenotazione));
        return mappaInResponseDTO(prenotazione);
    }

    @Transactional
    public PrenotazioneResponseDTO cancellaPrenotazione(String codicePrenotazione) {
        Prenotazione prenotazione = prenotazioneRepository.findByCodicePrenotazione(codicePrenotazione)
                .orElseThrow(() -> new BusinessException("Nessuna prenotazione trovata con codice: " + codicePrenotazione));

        if (prenotazione.getStato() == StatoPrenotazione.CANCELLATA) {
            throw new BusinessException("La prenotazione risulta già cancellata");
        }

        prenotazione.setStato(StatoPrenotazione.CANCELLATA);
        Camera camera = prenotazione.getCamera();
        camera.setDisponibile(true);

        cameraRepository.save(camera);
        Prenotazione aggiornata = prenotazioneRepository.save(prenotazione);

        return mappaInResponseDTO(aggiornata);
    }

    private PrenotazioneResponseDTO mappaInResponseDTO(Prenotazione p) {
        return new PrenotazioneResponseDTO(
                p.getCodicePrenotazione(),
                p.getNomeOspite(),
                p.getCamera().getHotel().getNome(),
                p.getCamera().getNumeroCamera(),
                p.getCheckIn(),
                p.getCheckOut(),
                p.getCostoTotale(),
                p.getStato()
        );
    }
}

