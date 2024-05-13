package com.api.commerce.domain.usuario;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoUsuario(
		@NotNull
		Long id, 
		String username, 
		String user_password, 
		LocalDateTime updated_at) {

}
