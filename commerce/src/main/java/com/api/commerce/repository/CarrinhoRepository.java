package com.api.commerce.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.api.commerce.entity.Carrinho;

public interface CarrinhoRepository extends JpaRepository<Carrinho, String>{

	Page<Carrinho> findAllByAtivoTrue(Pageable paginacao);


	Optional<Carrinho> findByUserIdAndAtivoTrue(@Param("user_id") String user_id);

}
