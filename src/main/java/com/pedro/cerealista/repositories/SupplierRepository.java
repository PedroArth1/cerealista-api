package com.pedro.cerealista.repositories;

import com.pedro.cerealista.models.entities.SupplierModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<SupplierModel, Long> {

}
