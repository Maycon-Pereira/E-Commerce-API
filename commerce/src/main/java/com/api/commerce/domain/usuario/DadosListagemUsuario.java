package com.api.commerce.domain.usuario;

import java.time.LocalDateTime;

public record DadosListagemUsuario(String id, String username, String email, Boolean ativo, Tipo tipo, LocalDateTime created_at, LocalDateTime updated_at) {

	public DadosListagemUsuario(Usuario usuario) {
		this(usuario.getId(), usuario.getUsername(), usuario.getEmail(), usuario.getAtivo(), usuario.getTipo(), usuario.getCreated_at(), usuario.getUpdated_at());
	}
}
