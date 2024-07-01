package com.api.commerce.entity;

import java.time.LocalDateTime;

import com.api.commerce.domain.usuario.Tipo;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "usuarios")
@Entity(name = "Usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario {

	@Id
	private String id;
	private String username;
	private String user_password;
	private String email;

	@Enumerated(EnumType.STRING)
	private Tipo tipo;
	private LocalDateTime created_at;
	private LocalDateTime updated_at;
	private Boolean ativo;

	@Lob
    private String imagem;
	
}
