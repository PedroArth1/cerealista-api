package com.pedro.cerealista.models.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "TB_PRODUTOS")
public class ProductModel {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id_produto")
    private Long idProduto;
    private String nome;
    private String tipo;
    private LocalDate validade;
    private BigDecimal preco;
    private Integer quantidadeEstoque;
}
