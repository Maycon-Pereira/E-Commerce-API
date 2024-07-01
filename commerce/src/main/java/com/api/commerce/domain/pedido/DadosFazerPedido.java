package com.api.commerce.domain.pedido;

import jakarta.validation.constraints.NotBlank;

public record DadosFazerPedido(
		
		@NotBlank
		String product_id,
		String user_id
		) {

}
