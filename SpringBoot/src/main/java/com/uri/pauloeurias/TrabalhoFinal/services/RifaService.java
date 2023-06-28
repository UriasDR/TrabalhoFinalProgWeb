package com.uri.pauloeurias.TrabalhoFinal.services;

import com.uri.pauloeurias.TrabalhoFinal.models.RifaModel;
import com.uri.pauloeurias.TrabalhoFinal.repositories.RifaRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RifaService {

    final RifaRepository rifaRepository;

    public RifaService(RifaRepository rifaRepository){
        this.rifaRepository = rifaRepository;
    }

    @Transactional
    public RifaModel save(RifaModel rifaModel) {
        return rifaRepository.save(rifaModel);
    }

    public boolean existsByRifaNumber(Integer rifaNumber) {
        return rifaRepository.existsByRifaNumber(rifaNumber);
    }

    public Page<RifaModel> findAll(Pageable pageable) {
        return rifaRepository.findAll(pageable);
    }

    public Optional<RifaModel> findById(UUID id) {
        return rifaRepository.findById(id);
    }

    @Transactional
    public void delete(RifaModel rifaModel) {
        rifaRepository.delete(rifaModel);
    }
}
