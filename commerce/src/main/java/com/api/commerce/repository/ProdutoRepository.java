package com.api.commerce.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.api.commerce.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, String>{
	Page<Produto> findAllByAtivoTrue(Pageable paginacao);

	Optional<Produto> findById(String id);

}
