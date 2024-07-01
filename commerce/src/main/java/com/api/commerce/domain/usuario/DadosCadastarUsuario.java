package com.api.commerce.domain.usuario;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastarUsuario(
		
		@NotBlank
		String username,
		@NotBlank
		String user_password,
		String imagem,
		@NotBlank
		@Email
		String email, 
		@NotNull
		Tipo tipo,
		@Future
		LocalDateTime created_at, 
		
		LocalDateTime updated_at) {
	
	

}
