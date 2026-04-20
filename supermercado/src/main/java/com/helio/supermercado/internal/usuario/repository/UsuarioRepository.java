package com.helio.supermercado.internal.usuario.repository;

import com.helio.supermercado.internal.usuario.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
    UsuarioEntity findByEmail(String email);

    Boolean existsByEmail(String email);
}