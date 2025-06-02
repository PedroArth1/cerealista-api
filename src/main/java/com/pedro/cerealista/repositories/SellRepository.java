package com.pedro.cerealista.repositories;

import com.pedro.cerealista.models.entities.SellModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SellRepository extends JpaRepository<SellModel, Long> {
}
