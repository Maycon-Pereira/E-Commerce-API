package com.api.commerce.domain.produto;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoProduto(

		String name, 
		int quantity,
		double price, 
		String description,
		@NotNull
		String category_id,
		@NotNull
		String user_id
		) {

}
