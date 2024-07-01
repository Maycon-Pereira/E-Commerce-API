package com.api.commerce.domain.usuario;

import java.time.LocalDateTime;

import com.api.commerce.entity.Usuario;

public record DadosDetalhamentoUsuario(String id, String username, String user_password, String imagem, String email, Boolean ativo, Tipo tipo, LocalDateTime created_at, LocalDateTime updated_at) {

	
	public DadosDetalhamentoUsuario(Usuario usuario) {
		this(usuario.getId(), usuario.getUsername(), usuario.getUser_password(), usuario.getImagem(), usuario.getEmail(), usuario.getAtivo(), usuario.getTipo(), usuario.getCreated_at(), usuario.getUpdated_at());
	}
}
