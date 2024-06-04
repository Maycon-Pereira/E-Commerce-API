package com.api.commerce.domain.categoria;

import jakarta.validation.constraints.NotBlank;

public record DadosCategorizarProduto(
		
		@NotBlank
		String categoryName
		) {

}
