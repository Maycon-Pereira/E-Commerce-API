package com.api.commerce.domain.produto;

import java.time.LocalDateTime;

import com.api.commerce.entity.Produto;

public record DadosListagemProduto(String id, String name, int quantity, double price, String color, String size, String discount, int rating, String description, String category_id, LocalDateTime created_at, LocalDateTime updated_at) {

	public DadosListagemProduto(Produto produto) {
		this(produto.getId(), produto.getName(), produto.getQuantity(), produto.getPrice(), produto.getColor(), produto.getSize(), produto.getDiscount(), produto.getRating(), produto.getDescription(), produto.getCategory_id(), produto.getCreated_at(),produto.getUpdated_at()
				//,produto.getImagens().stream().map(ProdutoImagem::getImagem).collect(Collectors.toList())
				);
	}
}
