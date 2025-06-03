package com.pedro.cerealista.controllers;

import com.pedro.cerealista.dtos.BuyRecordDto;
import com.pedro.cerealista.models.entities.BuyModel;
import com.pedro.cerealista.service.BuyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/buy")
@RestController
public class BuyController {

    @Autowired
    BuyService buyService;

    @PostMapping
    public ResponseEntity<BuyModel> criarCompra(@RequestBody @Valid BuyRecordDto dto) {
        BuyModel compra = buyService.criarCompra(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(compra);
    }
}
