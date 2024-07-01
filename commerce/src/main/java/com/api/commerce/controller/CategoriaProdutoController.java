package com.api.commerce.controller;

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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.api.commerce.domain.categoria.DadosAtualizacaoCategoria;
import com.api.commerce.domain.categoria.DadosCategorizarProduto;
import com.api.commerce.domain.categoria.DadosDetalhamentoCategoria;
import com.api.commerce.domain.categoria.DadosListagemCategoria;
import com.api.commerce.service.CategoriaService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("categorias")
public class CategoriaProdutoController {

	@Autowired
	private CategoriaService categoriaService;

	@PostMapping
	@Transactional
	public ResponseEntity<DadosDetalhamentoCategoria> categorizar(@RequestBody @Valid DadosCategorizarProduto dados, UriComponentsBuilder uriBuilder) {

		DadosDetalhamentoCategoria dadosDetalhamentoCategoria = categoriaService.criarCategoria(dados);

		var uri = uriBuilder.path("/categorias/{id}").buildAndExpand(dadosDetalhamentoCategoria.id()).toUri();

		return ResponseEntity.created(uri).body(dadosDetalhamentoCategoria);
	}

	@GetMapping
	public ResponseEntity<Page<DadosListagemCategoria>> listar(
			@PageableDefault(size = 10, sort = { "categoryName" }) Pageable paginacao) {

		Page<DadosListagemCategoria> response = categoriaService.findAllByAtivoTrue(paginacao);

		return ResponseEntity.ok(response);
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<DadosDetalhamentoCategoria> atualizar(@PathVariable String id, @RequestBody @Valid DadosAtualizacaoCategoria dados) throws Exception {
		
		DadosDetalhamentoCategoria response = categoriaService.atualizarInformacoes(id, dados);
		if (response == null) {
			throw new AccountNotFoundException("Id não encontrado na base");
		}
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<DadosListagemCategoria> excluir(@PathVariable String id) throws Exception {
		
		categoriaService.excluirCategoria(id);

		 return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DadosDetalhamentoCategoria> detalhar(@PathVariable String id) throws Exception {
		
		DadosDetalhamentoCategoria response = categoriaService.detalharCategoria(id);
		
		return ResponseEntity.ok(response);
	}
	
}
