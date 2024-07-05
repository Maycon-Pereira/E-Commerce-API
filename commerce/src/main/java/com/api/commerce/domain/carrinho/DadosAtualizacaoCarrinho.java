package com.api.commerce.domain.carrinho;

import java.util.List;

import com.api.commerce.entity.Item;

import jakarta.validation.constraints.NotBlank;

public record DadosAtualizacaoCarrinho(
		
		@NotBlank
		String user_id,

		List<Item> items
		) {

}
