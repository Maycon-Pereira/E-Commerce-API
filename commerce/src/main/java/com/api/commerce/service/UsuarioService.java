package com.api.commerce.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.security.auth.login.AccountNotFoundException;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.api.commerce.domain.usuario.DadosAtualizacaoUsuario;
import com.api.commerce.domain.usuario.DadosCadastarUsuario;
import com.api.commerce.domain.usuario.DadosDetalhamentoUsuario;
import com.api.commerce.domain.usuario.DadosListagemUsuario;
import com.api.commerce.entity.Usuario;
import com.api.commerce.repository.UsuarioRepository;

import jakarta.validation.Valid;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public DadosDetalhamentoUsuario criarUsuario(@Valid DadosCadastarUsuario dadosUsuario) {

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

	public DadosDetalhamentoUsuario atualizarInformacoes(String id, @Valid DadosAtualizacaoUsuario dados) throws Exception {
		Optional<Usuario> procurado = usuarioRepository.findById(id);
		if (!procurado.isPresent()) {
			throw new AccountNotFoundException("Id do usuario não encontrado na base");
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

	public DadosListagemUsuario excluirUsuario(String id) throws Exception {
		Optional<Usuario> procurado = usuarioRepository.findById(id);
		if (!procurado.isPresent()) {
			throw new AccountNotFoundException("Id do usuario não encontrado na base");
		}
		
		Usuario usuario = procurado.get();
		usuario.setAtivo(false);
		
		Usuario saved = usuarioRepository.save(usuario);
		
		return new DadosListagemUsuario(saved);
	}

	public DadosDetalhamentoUsuario detalharUsuario(String id) throws Exception {
		Optional<Usuario> procurado = usuarioRepository.findById(id);

        if (!procurado.isPresent()) {
        	throw new AccountNotFoundException("Id do usuario não encontrado na base");
        }

        Usuario usuario = procurado.get();
        return new DadosDetalhamentoUsuario(usuario);
	}

	//IMAGEM UPLOAD E DOWNLOAD
		public void upload(MultipartFile file, String id) throws Exception {
			Optional<Usuario> procurado = usuarioRepository.findById(id);

		    if (!procurado.isPresent()) {
		        throw new RuntimeException("Usuario não encontrado");
		    }
		    Usuario usuario = procurado.get();

		    // Convertendo o arquivo para uma string Base64
		    byte[] imagemBytes = file.getBytes();
		    String imagemBase64 = Base64.encodeBase64String(imagemBytes);
		    usuario.setImagem(imagemBase64);

		    // Salvando a entidade Usuario com a imagem em Base64
		    usuarioRepository.save(usuario);
		}

		public List<Usuario> download() {
		    return usuarioRepository.findAll();
		}

}
