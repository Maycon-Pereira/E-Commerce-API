package com.api.commerce.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.api.commerce.entity.Carrinho;

public interface CarrinhoRepository extends JpaRepository<Carrinho, String>{

	Page<Carrinho> findAllByAtivoTrue(Pageable paginacao);

	@Query("""
			SELECT c FROM Carrinho c 
			WHERE 
			c.user_id = :user_id 
			AND 
			c.ativo = true
			""")
	Optional<Carrinho> findByUserIdAndAtivoTrue(@Param("user_id") String user_id);

}
