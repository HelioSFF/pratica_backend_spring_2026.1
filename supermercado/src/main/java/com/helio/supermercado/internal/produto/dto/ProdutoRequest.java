package com.helio.supermercado.internal.produto.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

public record ProdutoRequest(
    @NotBlank(message = "O nome é obrigatório")
    String nome,
    
    @NotBlank(message = "O código de barras é obrigatório")
    String codigoBarras,
    
    @NotNull(message = "O preço é obrigatório")
    @Positive(message = "O preço deve ser maior que zero")
    BigDecimal preco,
    
    @NotNull(message = "A quantidade é obrigatória")
    @PositiveOrZero(message = "A quantidade não pode ser negativa")
    Integer quantidadeEstoque
) {}