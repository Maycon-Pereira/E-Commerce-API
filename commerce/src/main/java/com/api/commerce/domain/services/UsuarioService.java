package com.api.commerce.domain.services;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.commerce.domain.produto.DadosDetalhamentoProduto;
import com.api.commerce.domain.produto.DadosListagemProduto;
import com.api.commerce.domain.produto.Produto;
import com.api.commerce.domain.usuario.DadosAtualizacaoUsuario;
import com.api.commerce.domain.usuario.DadosCadastarUsuario;
import com.api.commerce.domain.usuario.DadosDetalhamentoUsuario;
import com.api.commerce.domain.usuario.DadosListagemUsuario;
import com.api.commerce.domain.usuario.Usuario;
import com.api.commerce.domain.usuario.UsuarioRepository;

import jakarta.validation.Valid;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public DadosDetalhamentoUsuario categorizarProduto(@Valid DadosCadastarUsuario dadosUsuario) {

		var createdTime = LocalDateTime.now();
		
		Usuario usuario = new Usuario();

		usuario.setId(UUID.randomUUID().toString());

		usuario.setUsername(dadosUsuario.username());
		usuario.setUser_password(dadosUsuario.user_password());
		usuario.setEmail(dadosUsuario.email());
		usuario.setTipo(dadosUsuario.tipo());
		usuario.setCreated_at(createdTime);
		usuario.setUpdated_at(dadosUsuario.updated_at());
		usuario.setAtivo(true);

		Usuario saved = usuarioRepository.save(usuario);

		return new DadosDetalhamentoUsuario(saved);
	}

	public Page<DadosListagemUsuario> findAllByAtivoTrue(Pageable paginacao) {
		Page<DadosListagemUsuario> response = usuarioRepository.findAllByAtivoTrue(paginacao).map(DadosListagemUsuario::new);
		return response;
	}

	public DadosDetalhamentoUsuario atualizarInformacoes(String id, @Valid DadosAtualizacaoUsuario dados) {
		Optional<Usuario> procurado = usuarioRepository.findById(id);
		if (!procurado.isPresent()) {
			return null;
		}
		var updatedTime = LocalDateTime.now();
		Usuario produto = procurado.get();
		produto.setUsername(dados.username());
		produto.setUser_password(dados.user_password());
		produto.setEmail(dados.email());
		produto.setUpdated_at(updatedTime);

		Usuario saved = usuarioRepository.save(produto);

		return new DadosDetalhamentoUsuario(saved);
	}

	public DadosListagemUsuario excluirUsuario(String id) {
		Optional<Usuario> procurado = usuarioRepository.findById(id);
		if (!procurado.isPresent()) {
			return null;
		}
		
		Usuario usuario = procurado.get();
		usuario.setAtivo(false);
		
		Usuario saved = usuarioRepository.save(usuario);
		
		return new DadosListagemUsuario(saved);
	}

	public DadosDetalhamentoUsuario detalharUsuario(String id) {
		Optional<Usuario> procurado = usuarioRepository.findById(id);

        if (!procurado.isPresent()) {
            return null;
        }

        Usuario usuario = procurado.get();
        return new DadosDetalhamentoUsuario(usuario);
	}

}
