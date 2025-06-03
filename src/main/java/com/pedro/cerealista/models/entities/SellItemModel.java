package com.pedro.cerealista.models.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
@Table(name = "TB_ITEM_VENDA")
@Entity
public class SellItemModel {
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
        @JoinColumn(name = "venda_id")
        private SellModel venda;
}
