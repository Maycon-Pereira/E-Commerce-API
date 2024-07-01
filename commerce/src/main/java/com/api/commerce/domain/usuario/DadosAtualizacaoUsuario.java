package com.api.commerce.domain.usuario;

import java.time.LocalDateTime;

public record DadosAtualizacaoUsuario(

		String username, 
		String email,
		String imagem,
		String user_password, 
		LocalDateTime updated_at) {

}
