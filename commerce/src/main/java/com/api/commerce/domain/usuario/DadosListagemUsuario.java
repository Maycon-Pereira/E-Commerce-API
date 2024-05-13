package com.api.commerce.domain.usuario;

import java.time.LocalDateTime;

public record DadosListagemUsuario(Long id, String username, String email, Tipo tipo, LocalDateTime created_at, LocalDateTime updated_at) {

	public DadosListagemUsuario(Usuario usuario) {
		this(usuario.getId(), usuario.getUsername(), usuario.getEmail(), usuario.getTipo(), usuario.getCreated_at(), usuario.getUpdated_at());
	}
}
