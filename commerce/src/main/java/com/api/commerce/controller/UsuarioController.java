package com.api.commerce.controller;

import java.util.List;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import com.api.commerce.domain.usuario.DadosAtualizacaoUsuario;
import com.api.commerce.domain.usuario.DadosCadastarUsuario;
import com.api.commerce.domain.usuario.DadosDetalhamentoUsuario;
import com.api.commerce.domain.usuario.DadosListagemUsuario;
import com.api.commerce.entity.Usuario;
import com.api.commerce.service.UsuarioService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@PostMapping
	@Transactional
	public ResponseEntity<DadosDetalhamentoUsuario> cadastrar(@RequestBody @Valid DadosCadastarUsuario dados, UriComponentsBuilder uriBuilder) {

		DadosDetalhamentoUsuario dadosDetalhamentoUsuario = usuarioService.criarUsuario(dados);

		var uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(dadosDetalhamentoUsuario.id()).toUri();

		return ResponseEntity.created(uri).body(dadosDetalhamentoUsuario);
	}

	@GetMapping
	public ResponseEntity<Page<DadosListagemUsuario>> listar(
			@PageableDefault(size = 10, sort = { "username" }) Pageable paginacao) {
		
		Page<DadosListagemUsuario> response = usuarioService.findAllByAtivoTrue(paginacao);

		return ResponseEntity.ok(response);
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<DadosDetalhamentoUsuario> atualizar(@PathVariable String id, @RequestBody @Valid DadosAtualizacaoUsuario dados) throws Exception {

		DadosDetalhamentoUsuario response = usuarioService.atualizarInformacoes(id, dados);
		if (response == null) {
			throw new AccountNotFoundException("Id não encontrado na base");
		}
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<DadosListagemUsuario> excluir(@PathVariable String id) throws Exception {
		
		usuarioService.excluirUsuario(id);


		 return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DadosDetalhamentoUsuario> detalhar(@PathVariable String id) throws Exception {
		
		DadosDetalhamentoUsuario response = usuarioService.detalharUsuario(id);
		
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/upload-imagem")
    public void uploadImagem(@RequestParam("id") String id,
                             @RequestPart("imagem") MultipartFile imagem) throws Exception {
        usuarioService.upload(imagem, id);
    }

    @GetMapping("/download-imagem")
    public List<Usuario> downloadImagens() {
        return usuarioService.download();
    }
	
}
