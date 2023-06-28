package com.uri.pauloeurias.TrabalhoFinal.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "Table_Rifa")
public class RifaModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false, unique = true)
    private Integer rifaNumber;

    @Column(nullable = false)
    private String rifaNome;

    @Column(nullable = false)
    private String rifaDataSorteio;

    @Column(nullable = false)
    private Double rifaPreco;
    @Column(nullable = false)
    private LocalDateTime registrationDate;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getRifaNumber() {
        return rifaNumber;
    }

    public void setRifaNumber(Integer rifaNumber) {
        this.rifaNumber = rifaNumber;
    }

    public String getRifaNome() {
        return rifaNome;
    }

    public void setRifaNome(String rifaNome) {
        this.rifaNome = rifaNome;
    }

    public String getRifaDataSorteio() {
        return rifaDataSorteio;
    }

    public void setRifaDataSorteio(String rifaDataSorteio) {
        this.rifaDataSorteio = rifaDataSorteio;
    }

    public Double getRifaPreco() {
        return rifaPreco;
    }

    public void setRifaPreco(Double rifaPreco) {
        this.rifaPreco = rifaPreco;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }
}
