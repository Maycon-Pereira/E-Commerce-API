package com.api.commerce.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.api.commerce.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
	Page<Usuario> findAllByAtivoTrue(Pageable paginacao);

	Optional<Usuario> findById(String id);

}
