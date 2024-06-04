package com.api.commerce.domain.categoria;

public record DadosDetalhamentoCategoria(String id, String categoryName) {

	public DadosDetalhamentoCategoria(CategoryProduct categoria) {
		this(categoria.getId(), categoria.getCategoryName());
	}
}
