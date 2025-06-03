package com.pedro.cerealista.dtos;


import jakarta.validation.constraints.*;

import java.math.BigDecimal;


public record ItemSellRecordDto(
        @NotNull(message = "O ID do produto é obrigatório")
        Long produtoId,

        @NotNull(message = "A quantidade é obrigatória")
        @Min(value = 1, message = "A quantidade deve ser no mínimo 1")
        @Max(value = 1000, message = "A quantidade não pode exceder 1000 unidades")
        Integer quantidade,

        @NotNull(message = "O preço unitário é obrigatório")
        @DecimalMin(value = "0.01", message = "O preço unitário deve ser maior que zero")
        @Digits(integer = 10, fraction = 2, message = "Formato inválido: máximo 10 dígitos inteiros e 2 decimais")
        BigDecimal precoUnitario
) {}