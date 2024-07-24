package com.api.commerce.domain.produto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroProduto(
		
		@NotBlank
		String name,
		@NotNull
		int quantity,
		@NotNull
		double price,
		String color,
		String size,
		String discount,
		int rating,
		@NotBlank
		String description,
		@NotBlank
		String category_id,
		@Future
		LocalDateTime created_at
		
		) {
	
}
