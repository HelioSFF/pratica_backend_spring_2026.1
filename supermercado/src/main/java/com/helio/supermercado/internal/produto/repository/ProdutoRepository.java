package com.helio.supermercado.internal.produto.repository;

import com.helio.supermercado.internal.produto.entity.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Long> {
    boolean existsByCodigoBarras(String codigoBarras);
}