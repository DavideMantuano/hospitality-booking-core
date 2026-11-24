package com.example.hospitality_booking_core.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String codiceStruttura;

    private String nome;
    private String citta;
    private Integer stelle;

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Camera> camere;

    public Hotel() {
    }

    public Hotel(String codiceStruttura, String nome, String citta, Integer stelle) {
        this.codiceStruttura = codiceStruttura;
        this.nome = nome;
        this.citta = citta;
        this.stelle = stelle;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodiceStruttura() {
        return codiceStruttura;
    }

    public void setCodiceStruttura(String codiceStruttura) {
        this.codiceStruttura = codiceStruttura;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public Integer getStelle() {
        return stelle;
    }

    public void setStelle(Integer stelle) {
        this.stelle = stelle;
    }

    public List<Camera> getCamere() {
        return camere;
    }

    public void setCamere(List<Camera> camere) {
        this.camere = camere;
    }
}
