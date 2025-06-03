package com.pedro.cerealista.dtos;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


import java.util.List;

public record BuyRecordDto(
        @NotNull(message = "ID do fornecedor é obrigatório")
        Long fornecedorId,

        @NotEmpty(message = "A compra deve ter pelo menos um item")
        @Valid
        List<BuyItemRecordDto> itens
) {}
