package com.api.commerce.domain.produto;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{
	Page<Produto> findAllByAtivoTrue(Pageable paginacao);

	Optional<Produto> findById(String id);

}
