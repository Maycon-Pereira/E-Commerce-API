package com.api.commerce.domain.categoria;

import com.api.commerce.entity.CategoryProduct;

public record DadosListagemCategoria(String id, String categoryName) {

	public DadosListagemCategoria(CategoryProduct categoria) {
		this(categoria.getId(), categoria.getCategoryName());
	}
}
