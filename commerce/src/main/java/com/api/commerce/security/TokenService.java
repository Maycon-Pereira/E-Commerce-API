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
			return JWT.create().withIssuer("API E.commerce").withSubject(usuario.getEmail())
					.withExpiresAt(dataExpiracao()).sign(algoritmo);
		} catch (JWTCreationException exception) {
			throw new RuntimeException("erro ao gerrar token jwt", exception);
		}
	}

	public String getSubject(String tokenJWT) {
		try {
			var algoritmo = Algorithm.HMAC256(secret);
			return JWT.require(algoritmo)
					.withIssuer("API E.commerce")
					.build()
					.verify(tokenJWT)
					.getSubject();

		} catch (JWTVerificationException exception) {
			throw new RuntimeException("Token JWT invalido ou expirado!");
		}
	}

	private Instant dataExpiracao() {
		return LocalDateTime.now().plusHours(8).toInstant(ZoneOffset.of("-03:00"));
	}

}
