package com.example.hospitality_booking_core.repository;

import com.example.hospitality_booking_core.model.Camera;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CameraRepository extends JpaRepository<Camera, Long> {
    List<Camera> findByHotelIdAndDisponibileTrue(Long hotelId);
}
