package com.api.commerce.domain.usuario;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "usuarios")
@Entity(name = "Usuario")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario {

	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String username;
	private String user_password;
	private String email;
	
	@Enumerated(EnumType.STRING)
	private Tipo tipo;
	private LocalDateTime created_at;
	private LocalDateTime updated_at;
	private Boolean ativo;
	
	
	public Usuario(DadosCadastarUsuario dados) {
		this.username = dados.username();
		this.user_password = dados.user_password();
		this.email = dados.email();
		this.tipo = dados.tipo();
		this.created_at = LocalDateTime.now();
		this.updated_at = dados.updated_at();
		this.ativo = true;
	}


	public void atualizarInformacoes(DadosAtualizacaoUsuario dados) {
		if (dados.username() != null) {
			this.username = dados.username();
		}
		if (dados.user_password() != null) {
			this.user_password = dados.user_password();
		}
		if (dados.updated_at() == null) {
			this.updated_at = LocalDateTime.now();
		}
		
	}


	public void excluir() {
		this.ativo = false;
		
	}
	
	
	
	
}
