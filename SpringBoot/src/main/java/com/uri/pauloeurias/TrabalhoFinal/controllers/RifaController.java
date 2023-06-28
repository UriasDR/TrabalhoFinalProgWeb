package com.uri.pauloeurias.TrabalhoFinal.controllers;

import com.uri.pauloeurias.TrabalhoFinal.dtos.RifaDto;
import com.uri.pauloeurias.TrabalhoFinal.models.RifaModel;
import com.uri.pauloeurias.TrabalhoFinal.services.RifaService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/Rifas")
public class RifaController {

    final RifaService rifaService;

    public RifaController(RifaService rifaService) {
        this.rifaService = rifaService;
    }

    @PostMapping
    public ResponseEntity<Object> saveRifa(@RequestBody @Valid RifaDto rifaDto){
        if(rifaService.existsByRifaNumber(rifaDto.getRifaNumber())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: Número de rifa já comprado");
        }
        var rifaModel = new RifaModel();
        BeanUtils.copyProperties(rifaDto, rifaModel);
        rifaModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(rifaService.save(rifaModel));
    }

    @GetMapping
    public ResponseEntity<Page<RifaModel>> getAllRifas(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC)Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(rifaService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneRifa(@PathVariable(value = "id") UUID id){
        Optional<RifaModel> rifaModelOptional = rifaService.findById(id);
        if(!rifaModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Rifa não encontrada");
        }
        return ResponseEntity.status(HttpStatus.OK).body(rifaModelOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteRifa(@PathVariable(value = "id") UUID id){
        Optional<RifaModel> rifaModelOptional = rifaService.findById(id);
        if(!rifaModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Rifa não encontrada");
        }
        rifaService.delete(rifaModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Rifa deletada com sucesso!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateRifa(@PathVariable(value = "id") UUID id,
                                             @RequestBody @Valid RifaDto rifaDto){
        Optional<RifaModel> rifaModelOptional = rifaService.findById(id);
        if(!rifaModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Rifa não encontrada");
        }
        var rifaModel = new RifaModel();
        BeanUtils.copyProperties(rifaDto,rifaModel);
        rifaModel.setId(rifaModelOptional.get().getId());
        rifaModel.setRegistrationDate(rifaModelOptional.get().getRegistrationDate());

        return ResponseEntity.status(HttpStatus.OK).body(rifaService.save(rifaModel));
    }


}
