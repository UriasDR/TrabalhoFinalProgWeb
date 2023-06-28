package com.uri.pauloeurias.TrabalhoFinal.repositories;

import com.uri.pauloeurias.TrabalhoFinal.models.RifaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RifaRepository extends JpaRepository<RifaModel, UUID> {

    boolean existsByRifaNumber(Integer rifaNumber);
}
