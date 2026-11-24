package com.example.hospitality_booking_core.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class Camera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numeroCamera;
    private String tipologia;
    private BigDecimal prezzoPerNotte;
    private boolean disponibile = true;

    @ManyToOne
    @JoinColumn(name = "hotal_id", nullable = false)
    private Hotel hotel;

    public Camera() {
    }

    public Camera(String numeroCamera, String tipologia, BigDecimal prezzoPerNotte, Hotel hotel) {
        this.numeroCamera = numeroCamera;
        this.tipologia = tipologia;
        this.prezzoPerNotte = prezzoPerNotte;
        this.hotel = hotel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroCamera() {
        return numeroCamera;
    }

    public void setNumeroCamera(String numeroCamera) {
        this.numeroCamera = numeroCamera;
    }

    public String getTipologia() {
        return tipologia;
    }

    public void setTipologia(String tipologia) {
        this.tipologia = tipologia;
    }

    public BigDecimal getPrezzoPerNotte() {
        return prezzoPerNotte;
    }

    public void setPrezzoPerNotte(BigDecimal prezzoPerNotte) {
        this.prezzoPerNotte = prezzoPerNotte;
    }

    public boolean isDisponibile() {
        return disponibile;
    }

    public void setDisponibile(boolean disponibile) {
        this.disponibile = disponibile;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
}
