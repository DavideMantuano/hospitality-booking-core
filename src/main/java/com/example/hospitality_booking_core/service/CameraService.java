package com.example.hospitality_booking_core.service;

import com.example.hospitality_booking_core.dto.CameraRequestDTO;
import com.example.hospitality_booking_core.exception.BusinessException;
import com.example.hospitality_booking_core.model.Camera;
import com.example.hospitality_booking_core.model.Hotel;
import com.example.hospitality_booking_core.repository.CameraRepository;
import com.example.hospitality_booking_core.repository.HotelRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CameraService {

    private final CameraRepository cameraRepository;
    private final HotelRepository hotelRepository;


    public CameraService(CameraRepository cameraRepository, HotelRepository hotelRepository) {
        this.cameraRepository = cameraRepository;
        this.hotelRepository = hotelRepository;
    }

    public Camera creaCamera(CameraRequestDTO dto) {
        Hotel hotel = hotelRepository.findById(dto.hotelId())
                .orElseThrow(() -> new BusinessException("Hotel non trovato con ID: " + dto.hotelId()));

        Camera camera = new Camera(dto.numeroCamera(), dto.tipologia(), dto.prezzoPerNotte(), hotel);
        return cameraRepository.save(camera);
    }

    public List<Camera> trovaDisponibiliPerHotel(Long hotelId) {
        return cameraRepository.findByHotelIdAndDisponibileTrue(hotelId);
    }

}
