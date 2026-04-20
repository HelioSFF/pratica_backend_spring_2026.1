package com.helio.supermercado.internal.usuario.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.helio.supermercado.internal.produto.exception.RecursoNaoEncontradoException;
import com.helio.supermercado.internal.produto.exception.RegraNegocioException;
import com.helio.supermercado.internal.usuario.dto.UsuarioRequestRecord;
import com.helio.supermercado.internal.usuario.dto.UsuarioResponseRecord;
import com.helio.supermercado.internal.usuario.entity.UsuarioEntity;
import com.helio.supermercado.internal.usuario.mapper.UsuarioMapperRecord;
import com.helio.supermercado.internal.usuario.repository.UsuarioRepository;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<UsuarioResponseRecord> listaUsuarios() {
        List<UsuarioEntity> usuarios = this.usuarioRepository.findAll();
        return UsuarioMapperRecord.entidadeParaResponseRecordList(usuarios);
    }

    public void cadastrarUsuario(UsuarioRequestRecord usuarioRequest) {
        boolean emailJaExiste = this.usuarioRepository.existsByEmail(usuarioRequest.email());
        if (emailJaExiste) {
            throw new RegraNegocioException("Já existe um usuário cadastrado com este e-mail");
        }
        UsuarioEntity usuarioEntity = UsuarioMapperRecord.requestParaEntidade(usuarioRequest);
        this.usuarioRepository.save(usuarioEntity);
    }

    public UsuarioResponseRecord buscarUsuarioPorId(Long id) {
        UsuarioEntity usuarioEntity = this.usuarioRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuario nao Encontrado"));
        return UsuarioMapperRecord.entidadeParaResponse(usuarioEntity);
    }

    public void atualizarUsuario(Long id, UsuarioRequestRecord usuarioRequest) {
        UsuarioEntity usuarioEntity = this.usuarioRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuario nao Encontrado"));
        usuarioEntity.setNome(usuarioRequest.nome());
        usuarioEntity.setEmail(usuarioRequest.email());
        usuarioEntity.setSenha(usuarioRequest.senha());
        this.usuarioRepository.save(usuarioEntity);
    }

    public void removerUsuario(Long id) {
        this.usuarioRepository.deleteById(id);
    }
}