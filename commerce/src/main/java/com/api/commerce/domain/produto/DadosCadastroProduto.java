package com.api.commerce.domain.produto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroProduto(
		
		@NotBlank
		String name,
		@NotNull
		int quantity,
		@NotNull
		double price,
		@NotBlank
		String description,
		@NotBlank
		String category_id
		
		) {
	
}
