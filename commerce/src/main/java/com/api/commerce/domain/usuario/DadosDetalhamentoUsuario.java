package com.api.commerce.domain.usuario;

import java.time.LocalDateTime;

public record DadosDetalhamentoUsuario(String username, String user_password, String email, Tipo tipo, LocalDateTime created_at, LocalDateTime updated_at) {

	
	public DadosDetalhamentoUsuario(Usuario usuario) {
		this(usuario.getUsername(), usuario.getUser_password(), usuario.getEmail(), usuario.getTipo(), usuario.getCreated_at(), usuario.getUpdated_at());
	}
}