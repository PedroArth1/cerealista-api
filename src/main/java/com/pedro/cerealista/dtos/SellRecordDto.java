package com.pedro.cerealista.dtos;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record SellRecordDto(
        @NotNull(message = "O ID do cliente é obrigatório")
        Long clienteId,

        @NotNull(message = "A lista de itens é obrigatória")
        @Size(min = 1, message = "A venda deve ter pelo menos 1 item")
        List<ItemSellRecordDto> itens,

        @NotNull(message = "A data da venda é obrigatória")
        LocalDate data,

        @NotNull(message = "O valor total é obrigatório")
        @DecimalMin(value = "0.01", message = "O valor total deve ser maior que zero")
        BigDecimal valorTotal
) {

}
