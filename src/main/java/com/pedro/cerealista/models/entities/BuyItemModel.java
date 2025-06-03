package com.pedro.cerealista.models.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
@Table(name = "TB_ITEM_COMPRA")
@Entity
public class BuyItemModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantidade;
    private BigDecimal precoUnitario;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private ProductModel produto;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "compra_id")
    private BuyModel compra;
}
