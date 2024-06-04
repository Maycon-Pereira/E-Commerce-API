package com.api.commerce.domain.usuario;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	Page<Usuario> findAllByAtivoTrue(Pageable paginacao);

	Optional<Usuario> findById(String id);
	
	

}
