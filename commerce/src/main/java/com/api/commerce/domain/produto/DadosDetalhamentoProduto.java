package com.api.commerce.domain.produto;

import com.api.commerce.entity.Produto;

public record DadosDetalhamentoProduto(String id, String name, int quantity, double price, String color, String size, String discount, String description, String category_id
		//, List<String> imagem
		) {

	public DadosDetalhamentoProduto(Produto produto) {
		this(produto.getId(), produto.getName(), produto.getQuantity(), produto.getPrice(), produto.getColor(), produto.getSize(), produto.getDiscount(), produto.getDescription(), produto.getCategory_id()
				
				//,produto.getImagens().stream().map(ProdutoImagem::getImagem).collect(Collectors.toList())
	             
				);
	}
	
}
