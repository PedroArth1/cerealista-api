package com.pedro.cerealista.repositories;

import com.pedro.cerealista.models.entities.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, Long> {
}
