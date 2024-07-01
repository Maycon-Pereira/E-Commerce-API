package com.api.commerce.domain.produto;

import jakarta.validation.constraints.NotBlank;

public record DadosAtualizacaoProduto(

		String name, 
		int quantity,
		double price, 
		String description,
		@NotBlank
		String category_id
		) {

}
