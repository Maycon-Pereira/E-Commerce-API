package com.api.commerce.domain.categoria;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoriaRepository extends JpaRepository<CategoryProduct, Long>{
	Page<CategoryProduct> findAllByAtivoTrue(Pageable paginacao);

	Optional<CategoryProduct> findById(String id);

}
