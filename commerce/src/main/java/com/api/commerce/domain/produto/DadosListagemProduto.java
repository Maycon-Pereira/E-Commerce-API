package com.api.commerce.domain.produto;

import java.util.List;
import java.util.stream.Collectors;

import com.api.commerce.entity.Produto;
import com.api.commerce.entity.ProdutoImagem;

public record DadosListagemProduto(String id, String name, int quantity, double price, String description, String category_id, List<String> imagem) {

	public DadosListagemProduto(Produto produto) {
		this(produto.getId(), produto.getName(), produto.getQuantity(), produto.getPrice(), produto.getDescription(), produto.getCategory_id(),
	             produto.getImagens().stream().map(ProdutoImagem::getImagem).collect(Collectors.toList()));
	}
}
