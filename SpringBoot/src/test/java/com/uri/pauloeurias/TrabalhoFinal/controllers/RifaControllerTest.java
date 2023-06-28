package com.uri.pauloeurias.TrabalhoFinal.controllers;


import com.uri.pauloeurias.TrabalhoFinal.dtos.RifaDto;
import com.uri.pauloeurias.TrabalhoFinal.models.RifaModel;
import com.uri.pauloeurias.TrabalhoFinal.services.RifaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class RifaControllerTest {

    @Mock
    private RifaService rifaService;

    @InjectMocks
    private RifaController rifaController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllRifas() {

        RifaModel rifaModel = new RifaModel();
        rifaModel.setId(UUID.fromString("3827ccd6-1543-11ee-be56-0242ac120002"));
        rifaModel.setRifaNome("Rifa Unity Test");
        rifaModel.setRifaNumber(1);
        rifaModel.setRifaPreco(5.54);
        rifaModel.setRifaDataSorteio("2023-07-28");
        rifaModel.setRegistrationDate(LocalDateTime.MAX);
        List<RifaModel> rifas = new ArrayList<>();
        rifas.add(rifaModel);

        Page<RifaModel> rifasPage = new PageImpl<>(rifas);

        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        Pageable pageable = PageRequest.of(0, 10, sort);

        when(rifaService.findAll(pageable)).thenReturn(rifasPage);

        ResponseEntity<Page<RifaModel>> response = rifaController.getAllRifas(pageable);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(rifasPage, response.getBody());

        verify(rifaService, times(1)).findAll(pageable);
    }

    @Test
    public void testGetRifasByID() {
        UUID id = UUID.fromString("3827ccd6-1543-11ee-be56-0242ac120002");

        RifaModel rifaModel = new RifaModel();
        rifaModel.setId(id);
        rifaModel.setRifaNome("Rifa Unity Test");
        rifaModel.setRifaNumber(1);
        rifaModel.setRifaPreco(5.54);
        rifaModel.setRifaDataSorteio("2023-07-28");
        rifaModel.setRegistrationDate(LocalDateTime.MAX);

        when(rifaService.findById(id)).thenReturn(Optional.of(rifaModel));

        ResponseEntity<Object> response = rifaController.getOneRifa(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(rifaModel, response.getBody());

        verify(rifaService, times(1)).findById(id);
    }

    @Test
    public void testSaveRifa() {
        RifaService rifaService = Mockito.mock(RifaService.class);

        RifaController rifaController = new RifaController(rifaService);

        RifaDto rifaDto = new RifaDto();
        rifaDto.setRifaNumber(1);
        rifaDto.setRifaNome("Rifa teste unitario");
        rifaDto.setRifaPreco(5.67);
        rifaDto.setRifaDataSorteio("2023-05-24");

        when(rifaService.existsByRifaNumber(rifaDto.getRifaNumber())).thenReturn(false);
        when(rifaService.save(Mockito.any(RifaModel.class))).thenAnswer(invocation -> invocation.getArgument(0));

        ResponseEntity<Object> response = rifaController.saveRifa(rifaDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());

        verify(rifaService, times(1)).existsByRifaNumber(rifaDto.getRifaNumber());
        verify(rifaService, times(1)).save(Mockito.any(RifaModel.class));
    }

    @Test
    public void testDeleteRifa() {
        RifaService rifaService = Mockito.mock(RifaService.class);

        RifaController rifaController = new RifaController(rifaService);

        UUID id = UUID.fromString("3827ccd6-1543-11ee-be56-0242ac120002");

        RifaModel rifaModel = new RifaModel();
        when(rifaService.findById(id)).thenReturn(Optional.of(rifaModel));

        ResponseEntity<Object> response = rifaController.deleteRifa(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Rifa deletada com sucesso!", response.getBody());

        verify(rifaService, times(1)).findById(id);
        verify(rifaService, times(1)).delete(rifaModel);
    }

    @Test
    public void testUpdateRifa() {
        RifaService rifaService = Mockito.mock(RifaService.class);

        RifaController rifaController = new RifaController(rifaService);

        UUID id = UUID.fromString("3827ccd6-1543-11ee-be56-0242ac120002");

        RifaDto rifaDto = new RifaDto();
        rifaDto.setRifaNome("Rifa Unity Test Atualizada");

        RifaModel rifaModel = new RifaModel();
        rifaModel.setId(id);
        Optional<RifaModel> rifaModelOptional = Optional.of(rifaModel);
        when(rifaService.findById(id)).thenReturn(rifaModelOptional);
        when(rifaService.save(Mockito.any(RifaModel.class))).thenAnswer(invocation -> invocation.getArgument(0));

        ResponseEntity<Object> response = rifaController.updateRifa(id, rifaDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());

        verify(rifaService, times(1)).findById(id);
        verify(rifaService, times(1)).save(Mockito.any(RifaModel.class));
    }

}
