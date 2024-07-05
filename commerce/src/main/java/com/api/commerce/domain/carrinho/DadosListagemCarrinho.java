package com.api.commerce.domain.carrinho;

import java.time.LocalDateTime;
import java.util.List;

import com.api.commerce.entity.Carrinho;
import com.api.commerce.entity.Item;

public record DadosListagemCarrinho(String id, LocalDateTime created_at, LocalDateTime updated_at, Boolean ativo, String user_id,  List<Item> items) {

	public DadosListagemCarrinho(Carrinho carrinho) {
		this(carrinho.getId(), carrinho.getCreated_at(), carrinho.getUpdated_at(), carrinho.getAtivo(), carrinho.getUser_id(), carrinho.getItems());
	}
}
