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

    @GetMapping({"/id"})
    public ResponseEntity<Object> getOneSell(@PathVariable(value="id") Long id) {
        Optional<SellModel> sell0 = sellService.findById(id);
        if (sell0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sell not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(sell0.get());
    }

    @PostMapping
    public ResponseEntity<Object> saveSell(@RequestBody @Valid SellRecordDto sellRecordDto) {
        var sellModel = new SellModel();
        BeanUtils.copyProperties(sellRecordDto, sellModel);
        SellModel v = sellService.save(sellModel);
        System.out.println(v);
        if (v.getId() != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Venda salva com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro ao salvar venda.");
        }
    }

    @DeleteMapping({"/id"})
    public ResponseEntity<Object> deleteSell(@PathVariable(value="id") Long id){
        boolean deletado = sellService.delete(id);
        if (deletado){
            return ResponseEntity.status(HttpStatus.OK).body("Sell deleted");
        } return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sell Not Found");
    }
}
