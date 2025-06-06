package com.pedro.cerealista.models.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;


@Entity
@Table(name = "TB_ITENS_VENDA")
@Getter @Setter @NoArgsConstructor
public class SellItemModel {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Positive(message = "Quantidade deve ser positiva")
        private BigDecimal quantidade;

        @Positive(message = "Preço deve ser positivo")
        private BigDecimal precoUnitario;

        @Positive(message = "Preço deve ser positivo")
        private BigDecimal custoUnitario;

        @ManyToOne
        @JoinColumn(name = "produto_id")
        private ProductModel produto;

        @JsonIgnore
        @ManyToOne
        @JoinColumn(name = "venda_id")
        private SellModel venda;

}
