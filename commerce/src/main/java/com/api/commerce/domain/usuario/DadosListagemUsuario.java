package com.api.commerce.domain.usuario;

import java.time.LocalDateTime;

import com.api.commerce.entity.Usuario;

public record DadosListagemUsuario(String id, String username, String email, String imagem, Boolean ativo, Tipo tipo, LocalDateTime created_at, LocalDateTime updated_at) {

	public DadosListagemUsuario(Usuario usuario) {
		this(usuario.getId(), usuario.getUsername(), usuario.getEmail(), usuario.getImagem(), usuario.getAtivo(), usuario.getTipo(), usuario.getCreated_at(), usuario.getUpdated_at());
	}
}
