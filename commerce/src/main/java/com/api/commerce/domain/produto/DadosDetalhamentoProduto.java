package com.api.commerce.domain.produto;

import com.api.commerce.entity.Produto;

public record DadosDetalhamentoProduto(String id, String name, int quantity, double price, String description, String category_id) {

	public DadosDetalhamentoProduto(Produto produto) {
		this(produto.getId(), produto.getName(), produto.getQuantity(), produto.getPrice(), produto.getDescription(), produto.getCategory_id());
	}
	
}
