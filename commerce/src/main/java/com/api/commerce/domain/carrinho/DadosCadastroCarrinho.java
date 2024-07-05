package com.api.commerce.domain.carrinho;

import java.time.LocalDateTime;
import java.util.List;

import com.api.commerce.entity.Item;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroCarrinho(
		
		@NotNull
		LocalDateTime created_at,
		LocalDateTime updated_at,
		@NotNull
		Boolean ativo,

		@NotBlank
		String user_id,

		List<Item> items
		) {

}
