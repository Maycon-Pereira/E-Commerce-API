package com.api.commerce.domain.categoria;

import com.api.commerce.entity.CategoryProduct;

public record DadosDetalhamentoCategoria(String id, String categoryName) {

	public DadosDetalhamentoCategoria(CategoryProduct categoria) {
		this(categoria.getId(), categoria.getCategoryName());
	}
}
