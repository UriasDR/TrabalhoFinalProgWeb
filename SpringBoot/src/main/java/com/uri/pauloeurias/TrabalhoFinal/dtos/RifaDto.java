package com.uri.pauloeurias.TrabalhoFinal.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Date;

public class RifaDto {

    @NotNull
    private Integer rifaNumber;

    @NotBlank
    private String rifaNome;

    @NotBlank
    private String rifaDataSorteio;

    @NotNull
    private Double rifaPreco;

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
}
