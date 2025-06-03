package com.pedro.cerealista.repositories;

import com.pedro.cerealista.models.entities.BuyModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuyRepository extends JpaRepository<Long, BuyModel> {
}
