package com.pedro.cerealista.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SupplierRecordDto(
        @NotBlank(message = "O nome do fornecedor é obrigatório")
        @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
        String nome,

        @NotBlank(message = "O CNPJ é obrigatório")
        @Size(min = 14, max = 14, message = "CNPJ deve conter 14 dígitos numéricos")
        String cnpj,

        @NotBlank(message = "O contato é obrigatório")
        String contato ) {
}
