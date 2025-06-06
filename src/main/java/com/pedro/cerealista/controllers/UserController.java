package com.pedro.cerealista.controllers;


import com.pedro.cerealista.dtos.UserRecordDto;
import com.pedro.cerealista.models.entities.UserModel;
import com.pedro.cerealista.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api/usuarios")
@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping
    public ResponseEntity<List<UserModel>> getAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneUser(@PathVariable(value = "id") Long id) {
        Optional<UserModel> user0 = userRepository.findById(id);
        if(user0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(user0.get());
    }

    @PostMapping
    public ResponseEntity<UserModel> saveUser(@Valid @RequestBody UserRecordDto userRecordDto){
        var user0 = new UserModel();
        BeanUtils.copyProperties(userRecordDto, user0);
        return ResponseEntity.status(HttpStatus.OK).body(userRepository.save(user0));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable(value = "id") Long id, @Valid @RequestBody UserRecordDto userRecordDto) {
        Optional<UserModel> user0 = userRepository.findById(id);
        if (user0.isEmpty()){
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        var productModel = user0.get();
        BeanUtils.copyProperties(userRecordDto, productModel);
        return ResponseEntity.status(HttpStatus.OK).body(userRepository.save(productModel));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable(value = "id") Long id) {
        Optional<UserModel> user0 = userRepository.findById(id);
        if (user0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        userRepository.delete(user0.get());
        return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully");
    }
}
