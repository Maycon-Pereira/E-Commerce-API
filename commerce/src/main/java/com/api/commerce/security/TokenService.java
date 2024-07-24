package com.api.commerce.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.api.commerce.entity.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

@Service
public class TokenService {

	@Value("${api.security.token.secret}")
	private String secret;

	public String gerarToken(Usuario usuario) {
		System.out.println(secret);
		try {
			var algoritmo = Algorithm.HMAC256(secret);
			System.out.println("token 1");
			return JWT.create().withIssuer("API Voll.med").withSubject(usuario.getUsername())
					.withExpiresAt(dataExpiracao()).sign(algoritmo);
		} catch (JWTCreationException exception) {
			throw new RuntimeException("erro ao gerrar token jwt", exception);
		}
	}

	public String getSubject(String tokenJWT) {
		try {
			var algoritmo = Algorithm.HMAC256(secret);
			System.out.println("token 2");
			return JWT.require(algoritmo)
					.withIssuer("API Voll.med")
					.build()
					.verify(tokenJWT)
					.getSubject();

		} catch (JWTVerificationException exception) {
			throw new RuntimeException("Token JWT invalido ou expirado!");
		}
	}

	private Instant dataExpiracao() {
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
	}

}
