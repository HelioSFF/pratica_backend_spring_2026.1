package com.helio.supermercado.internal.produto.dto;

import java.math.BigDecimal;

public record ProdutoResponse(
    Long id,
    String nome,
    String codigoBarras,
    BigDecimal preco,
    Integer quantidadeEstoque
) {}