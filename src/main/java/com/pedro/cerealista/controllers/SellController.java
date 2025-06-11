package com.pedro.cerealista.controllers;


import com.pedro.cerealista.models.entities.SellModel;
import com.pedro.cerealista.service.SellService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendas")
public class SellController {

    private final SellService sellService;

    public SellController(SellService sellService) {
        this.sellService = sellService;
    }

    @PostMapping
    public ResponseEntity<SellModel> criarVenda(@RequestBody SellModel venda) {
        SellModel vendaCriada = sellService.criarVenda(venda);
        return ResponseEntity.status(HttpStatus.CREATED).body(vendaCriada);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SellModel> getVenda(@PathVariable Long id) {
        SellModel venda = sellService.getVenda(id);
        return ResponseEntity.status(HttpStatus.OK).body(venda);
    }

    @GetMapping
    public ResponseEntity<List<SellModel>> GetVendas() {
        List<SellModel> vendas = sellService.GetAllSells();
        return ResponseEntity.status(HttpStatus.OK).body(vendas);
    }

}
