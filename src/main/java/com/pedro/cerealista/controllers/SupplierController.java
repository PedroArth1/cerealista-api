package com.pedro.cerealista.controllers;

import com.pedro.cerealista.dtos.SupplierRecordDto;
import com.pedro.cerealista.models.entities.SupplierModel;
import com.pedro.cerealista.repositories.SupplierRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api/fornecedores")
@RestController
public class SupplierController {

        @Autowired
        SupplierRepository supplierRepository;

        @GetMapping
        public ResponseEntity<List<SupplierModel>> getAllSuppliers() {
            return ResponseEntity.status(HttpStatus.OK).body(supplierRepository.findAll());
        }

        @GetMapping("/{id}")
        public ResponseEntity<Object> getOneSupplier(@PathVariable(value = "id") Long id) {
            Optional<SupplierModel> supplier0 = supplierRepository.findById(id);
            if (supplier0.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Supplier not found");
            }
            return ResponseEntity.status(HttpStatus.OK).body(supplier0.get());
        }

        @PostMapping
        public ResponseEntity<SupplierModel> saveSupplier(@RequestBody @Valid SupplierRecordDto supplierRecordDto) {
            var supplierModel = new SupplierModel();
            BeanUtils.copyProperties(supplierRecordDto, supplierModel);
            return ResponseEntity.status(HttpStatus.CREATED).body(supplierRepository.save(supplierModel));
        }

        @PutMapping("/{id}")
        public ResponseEntity<Object> updateSupplier(@PathVariable(value = "id") Long id, @RequestBody @Valid SupplierRecordDto supplierRecordDto) {
            Optional<SupplierModel> supplierO = supplierRepository.findById(id);
            if (supplierO.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Supplier not found");
            }
            var supplierModel = supplierO.get();
            BeanUtils.copyProperties(supplierRecordDto, supplierModel);
            return ResponseEntity.status(HttpStatus.OK).body(supplierRepository.save(supplierModel));
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Object> deleteSupplier(@PathVariable(value = "id") Long id) {
            Optional<SupplierModel> supplierO = supplierRepository.findById(id);
            if (supplierO.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Supplier not found");
            }
            supplierRepository.delete(supplierO.get());
            return ResponseEntity.status(HttpStatus.OK).body("Supplier deleted successfully.");
        }
    }

