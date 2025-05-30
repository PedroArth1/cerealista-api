package com.pedro.cerealista.dtos;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ClientRecordDto(
        @NotBlank(message = "O nome de cliente é obrigatório")
        @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
        String nome,
        @NotBlank(message = "O CPF/CNPJ é obrigatório")
        @Size(min = 11, max = 14, message = "CPF/CNPJ deve ter entre 11 e 14 caracteres")
        String cpfOuCnpj,
        @NotBlank(message = "O telefone é obrigatório")
        String telefone,
        @NotBlank(message = "O endereço é obrigatório")
        @Size(min = 5, max = 200, message = "O endereço deve ter entre 5 e 200 caracteres")
        String endereco ) {
}
