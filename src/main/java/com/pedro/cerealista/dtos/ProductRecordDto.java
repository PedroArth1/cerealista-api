package com.pedro.cerealista.dtos;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ProductRecordDto(
        @NotBlank(message = "O nome do produto é obrigatório")
        @Size(min = 2, max = 100, message = "O nome deve ter entre 2 e 100 caracteres")
        String nome,
        @NotBlank(message = "O tipo do produto é obrigatório")
        String tipo,
        @NotNull(message = "A data de validade é obrigatória")
        @FutureOrPresent(message = "A data de validade deve ser atual ou futura")
        LocalDate validade,
        @NotNull(message = "O preço é obrigatório")
        @DecimalMin(value = "0.01", message = "O preço deve ser maior que zero")
        @Digits(integer = 6, fraction = 2, message = "Preço deve ter no máximo 6 dígitos inteiros e 2 decimais")
        BigDecimal preco,
        @NotNull(message = "A quantidade em estoque é obrigatória")
        @Min(value = 0, message = "O estoque não pode ser negativo")
        Integer quantidadeEstoque) {
}
