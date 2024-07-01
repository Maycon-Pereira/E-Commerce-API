package com.api.commerce.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.api.commerce.entity.CategoryProduct;

public interface CategoriaRepository extends JpaRepository<CategoryProduct, String> {
	Page<CategoryProduct> findAllByAtivoTrue(Pageable paginacao);

	Optional<CategoryProduct> findById(String id);

}
