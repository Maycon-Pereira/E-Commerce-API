package com.api.commerce.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.api.commerce.entity.Carrinho;

public interface CarrinhoRepository extends JpaRepository<Carrinho, String>{

	Page<Carrinho> findAllByAtivoTrue(Pageable paginacao);

}
