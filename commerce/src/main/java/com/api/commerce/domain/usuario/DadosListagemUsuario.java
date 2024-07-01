package com.api.commerce.domain.usuario;

import java.time.LocalDateTime;

import com.api.commerce.entity.Usuario;

public record DadosListagemUsuario(String id, String username, String email, Boolean ativo, Tipo tipo, LocalDateTime created_at, LocalDateTime updated_at, String imagem) {

	public DadosListagemUsuario(Usuario usuario) {
		this(usuario.getId(), usuario.getUsername(), usuario.getEmail(), usuario.getAtivo(), usuario.getTipo(), usuario.getCreated_at(), usuario.getUpdated_at(), usuario.getImagem());
	}
}
