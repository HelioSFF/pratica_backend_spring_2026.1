package com.helio.supermercado.internal.produto.mapper;

import com.helio.supermercado.internal.produto.dto.ProdutoRequest;
import com.helio.supermercado.internal.produto.dto.ProdutoResponse;
import com.helio.supermercado.internal.produto.entity.ProdutoEntity;
import org.springframework.stereotype.Component;

@Component
public class ProdutoMapper {

    public ProdutoEntity toEntity(ProdutoRequest request) {
        ProdutoEntity entity = new ProdutoEntity();
        entity.setNome(request.nome());
        entity.setCodigoBarras(request.codigoBarras());
        entity.setPreco(request.preco());
        entity.setQuantidadeEstoque(request.quantidadeEstoque());
        return entity;
    }

    public ProdutoResponse toResponse(ProdutoEntity entity) {
        return new ProdutoResponse(
                entity.getId(),
                entity.getNome(),
                entity.getCodigoBarras(),
                entity.getPreco(),
                entity.getQuantidadeEstoque()
        );
    }
}