package com.api.commerce.domain.categoria;

public record DadosListagemCategoria(String id, String categoryName) {

	public DadosListagemCategoria(CategoryProduct categoria) {
		this(categoria.getId(), categoria.getCategoryName());
	}
}
