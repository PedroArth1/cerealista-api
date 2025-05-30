package com.pedro.cerealista.repositories;

import com.pedro.cerealista.models.entities.ClientModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<ClientModel, Long> {
}
