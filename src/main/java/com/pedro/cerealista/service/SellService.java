package com.pedro.cerealista.service;


import com.pedro.cerealista.models.entities.SellItemModel;
import com.pedro.cerealista.models.entities.SellModel;
import com.pedro.cerealista.repositories.SellRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Service
public class SellService {

    @Autowired
    SellRepository sellRepository;

    public SellModel save(SellModel sell) {
        for (SellItemModel itemVenda : sell.getItens()) {
            itemVenda.setVenda(sell);
        }
        return sellRepository.save(sell);
    }

    public boolean delete(Long id) {
        Optional<SellModel> vendaOptional = sellRepository.findById(id);
        if (vendaOptional.isPresent()) {
            sellRepository.delete(vendaOptional.get());
            return true;
        } else {
            return false;
        }

}

    public List<SellModel> getAllSells() {
        return sellRepository.findAll();
    }

    public Optional<SellModel> findById(Long id) {
        return sellRepository.findById(id);
    }
}
