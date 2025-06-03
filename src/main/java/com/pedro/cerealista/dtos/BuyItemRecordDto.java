package com.pedro.cerealista.dtos;

import com.pedro.cerealista.models.entities.BuyModel;
import com.pedro.cerealista.models.entities.ProductModel;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record BuyItemRecordDto(
        @NotNull(message = "ID do produto é obrigatório")
        Long produtoId,

        @NotNull(message = "Quantidade é obrigatória")
        @Positive(message = "Quantidade deve ser positiva")
        Integer quantidade,

        @NotNull(message = "Preço unitário é obrigatório")
        @Positive(message = "Preço unitário deve ser positivo")
        BigDecimal precoUnitario
) {}
