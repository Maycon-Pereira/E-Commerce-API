package com.api.commerce.domain.pedido;

import java.time.LocalDateTime;

import com.api.commerce.entity.Pedido;

public record DadosDetalhamentoPedido(String id, String product_id, String user_id, LocalDateTime created_at, LocalDateTime updated_at, LocalDateTime canceled_at) {

	public DadosDetalhamentoPedido(Pedido pedido) {
		this(pedido.getId(), pedido.getProduct_id(), pedido.getUser_id(), pedido.getCreated_at(), pedido.getUpdated_at(), pedido.getCanceled_at());
	}
}
