package com.api.commerce.domain.pedido;

import java.time.LocalDateTime;

public record DadosAtualizacaoPedido(
		
		String product_id,
		String user_id,
		LocalDateTime updated_at) {

}
