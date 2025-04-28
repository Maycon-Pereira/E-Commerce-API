package com.api.commerce.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.api.commerce.domain.usuario.DadosCadastarUsuario;
import com.api.commerce.domain.usuario.Tipo;
import com.api.commerce.entity.Usuario;

import jakarta.persistence.EntityManager;

@DataJpaTest
@ActiveProfiles("test")
public class UsuarioRepositoryTest {

	@Autowired
	UsuarioRepository repository;
	
	@Autowired
	EntityManager entityManager;
	
	@Test
	@DisplayName("Should get User successfully from DB")
	void findByEmailCase1() {
		String email = "teste@gmail.com";
		DadosCadastarUsuario dados = new DadosCadastarUsuario("Maycon", "123456789", "teste imagem base64", email, Tipo.CLIENT, LocalDateTime.now(), LocalDateTime.now());
		this.CreateUser(dados);
		
		Usuario usuarioEncontrado = this.repository.findByEmail(email);
		assertThat(usuarioEncontrado).isNotNull();
		assertThat(usuarioEncontrado.getEmail()).isEqualTo(email);
		
	}
	
	private Usuario CreateUser(DadosCadastarUsuario usuario) {
		Usuario newUsuario = new Usuario(usuario);
		this.entityManager.persist(newUsuario);
		return newUsuario;
		
	}
	
}
