package com.api.commerce.domain.produto;

public record DadosListagemProduto(String id, String name, int quantity, double price, String description, String category_id, String user_id) {

	public DadosListagemProduto(Produto produto) {
		this(produto.getId(), produto.getName(), produto.getQuantity(), produto.getPrice(), produto.getDescription(), produto.getCategory_id(), produto.getUser_id());
	}
}
