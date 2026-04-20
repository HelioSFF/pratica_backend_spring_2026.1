package com.helio.supermercado.internal.produto.service;

import com.helio.supermercado.internal.produto.dto.ProdutoRequest;
import com.helio.supermercado.internal.produto.dto.ProdutoResponse;
import com.helio.supermercado.internal.produto.mapper.ProdutoMapper;
import com.helio.supermercado.internal.produto.entity.ProdutoEntity;
import com.helio.supermercado.internal.produto.exception.RecursoNaoEncontradoException;
import com.helio.supermercado.internal.produto.exception.RegraNegocioException;
import com.helio.supermercado.internal.produto.repository.ProdutoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository repository;
    private final ProdutoMapper mapper;

    public ProdutoService(ProdutoRepository repository, ProdutoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional
    public ProdutoResponse cadastrar(ProdutoRequest request) {
        if (repository.existsByCodigoBarras(request.codigoBarras())) {
            throw new RegraNegocioException("Código de barras já cadastrado");
        }
        ProdutoEntity entity = mapper.toEntity(request);
        ProdutoEntity savedEntity = repository.save(entity);
        return mapper.toResponse(savedEntity);
    }

    @Transactional(readOnly = true)
    public List<ProdutoResponse> listarTodos() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .toList();
    }
    
    @Transactional
    public ProdutoResponse atualizar(Long id, ProdutoRequest request) {
        ProdutoEntity entity = repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Produto não encontrado"));


        if (!entity.getCodigoBarras().equals(request.codigoBarras()) && 
            repository.existsByCodigoBarras(request.codigoBarras())) {
            throw new RegraNegocioException("Código de barras já cadastrado em outro produto");
        }

        entity.setNome(request.nome());
        entity.setCodigoBarras(request.codigoBarras());
        entity.setPreco(request.preco());
        entity.setQuantidadeEstoque(request.quantidadeEstoque());

        return mapper.toResponse(repository.save(entity));
    }

    @Transactional
    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Produto não encontrado");
        }
        repository.deleteById(id);
    }
}