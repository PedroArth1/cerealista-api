package com.pedro.cerealista.repositories;

import com.pedro.cerealista.models.entities.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, Long> {
    @Query(value = "select * from tb_produtos where nome ilike %:nome%", nativeQuery = true)
    List<ProductModel> buscarLikeNome(@Param("nome") String nome);
}
