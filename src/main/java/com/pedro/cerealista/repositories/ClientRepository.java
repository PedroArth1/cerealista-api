package com.pedro.cerealista.repositories;

import com.pedro.cerealista.models.entities.ClientModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClientRepository extends JpaRepository<ClientModel, Long> {

    @Query(value = "select * from tb_clientes where nome ilike %:nome%", nativeQuery = true)
    List<ClientModel> buscarLikeNome(@Param("nome") String nome);
}
