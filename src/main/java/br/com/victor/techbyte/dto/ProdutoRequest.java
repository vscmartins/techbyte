package br.com.victor.techbyte.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record ProdutoRequest(
        @NotBlank(message = "O nome é obrigatório")
        @Size(max = 120, message = "O nome deve ter no máximo 120 caracteres")
        String nome,

        @NotBlank(message = "A descrição é obrigatória")
        @Size(max = 500, message = "A descrição deve ter no máximo 500 caracteres")
        String descricao,

        @NotNull(message = "O preço é obrigatório")
        @DecimalMin(value = "0.00", message = "O preço não pode ser negativo")
        BigDecimal preco
) {
}
