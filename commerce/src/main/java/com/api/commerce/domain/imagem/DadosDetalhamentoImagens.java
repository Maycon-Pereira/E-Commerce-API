package com.api.commerce.domain.imagem;

import com.api.commerce.entity.ProdutoImagem;

public record DadosDetalhamentoImagens(String id, String produtoId, Boolean ativo, String imagem) {

	public DadosDetalhamentoImagens(ProdutoImagem imagem) {
		this(imagem.getId(), imagem.getProduto().getId(), imagem.getAtivo(), imagem.getImagem());
	}
	
}
