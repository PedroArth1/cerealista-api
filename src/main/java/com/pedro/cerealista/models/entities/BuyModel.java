package com.pedro.cerealista.models.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "TB_COMPRAS")
public class BuyModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDate data;

    private BigDecimal valorTotal;

    @ManyToOne
    @JoinColumn(name = "fornecedor_id")
    private SupplierModel supplier;

    @OneToMany(mappedBy = "compra", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<BuyItemModel> itens = new ArrayList<>();


    // Método para adicionar itens mantendo a consistência bidirecional
    public void adicionarItem(BuyItemModel item) {
        itens.add(item);
        item.setCompra(this);
    }

    // Método para calcular o valor total automaticamente
    public void calcularValorTotal() {
        this.valorTotal = itens.stream()
                .map(item -> item.getPrecoUnitario().multiply(BigDecimal.valueOf(item.getQuantidade())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
