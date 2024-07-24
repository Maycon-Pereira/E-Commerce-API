package com.api.commerce.domain.produto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;

public record DadosAtualizacaoProduto(

		String name, 
		int quantity,
		double price,
		String color,
		String size,
		String discount,
		int rating,
		String description,
		@NotBlank
		String category_id,
		@Future		
		LocalDateTime updated_at
		) {

}
