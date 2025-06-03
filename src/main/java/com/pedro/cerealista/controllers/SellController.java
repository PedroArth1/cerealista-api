package com.pedro.cerealista.controllers;


import com.pedro.cerealista.dtos.SellRecordDto;
import com.pedro.cerealista.models.entities.ProductModel;
import com.pedro.cerealista.models.entities.SellModel;
import com.pedro.cerealista.repositories.ProductRepository;
import com.pedro.cerealista.repositories.SellRepository;
import com.pedro.cerealista.service.SellService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RequestMapping("/sell")
@RestController
public class SellController {


    @Autowired
    SellService sellService;

    @GetMapping
    public ResponseEntity<List<SellModel>> getAllSells() {
        return ResponseEntity.status(HttpStatus.OK).body(sellService.getAllSells());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SellModel> getOneSell(@PathVariable(value="id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(sellService.getSellById(id));
    }

    // No Controller
    @PostMapping
    public ResponseEntity<String> criarVenda(@RequestBody SellRecordDto dto) {
        try {
            SellModel vendaSalva = sellService.createSell(dto);
            return ResponseEntity.ok("Venda criada com ID: " + vendaSalva.getId());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteSell(@PathVariable(value="id") Long id){
        var deleted = sellService.delete(id);
        if (deleted){
            return ResponseEntity.status(HttpStatus.OK).body("Deletado");
        } return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Venda não encontrada");
    }

}
