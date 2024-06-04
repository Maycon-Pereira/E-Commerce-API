package com.api.commerce.controller;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
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

import com.api.commerce.domain.produto.DadosAtualizacaoProduto;
import com.api.commerce.domain.produto.DadosCadastroProduto;
import com.api.commerce.domain.produto.DadosDetalhamentoProduto;
import com.api.commerce.domain.produto.DadosListagemProduto;
import com.api.commerce.domain.produto.ProdutoRepository;
import com.api.commerce.domain.services.ProdutoService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("produtos")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private ProdutoRepository produtoRepository;

	@PostMapping
	@Transactional
	public ResponseEntity<DadosDetalhamentoProduto> cadastrar(@RequestBody @Valid DadosCadastroProduto dados,
			UriComponentsBuilder uriBuilder) {

		DadosDetalhamentoProduto dadosDetalhamentoProduto = produtoService.categorizarProduto(dados);

		var uri = uriBuilder.path("/produtos/{id}").buildAndExpand(dadosDetalhamentoProduto.id()).toUri();

		return ResponseEntity.created(uri).body(dadosDetalhamentoProduto);
	}

	@GetMapping
	public ResponseEntity<Page<DadosListagemProduto>> listar(
			@PageableDefault(size = 10, sort = { "name" }) Pageable paginacao) {

		Page<DadosListagemProduto> response = produtoService.findAllByAtivoTrue(paginacao);

		return ResponseEntity.ok(response);
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity atualizar(@PathVariable String id, @RequestBody @Valid DadosAtualizacaoProduto dados)
			throws Exception {

		DadosDetalhamentoProduto response = produtoService.atualizarInformacoes(id, dados);
		if (response == null) {
			throw new AccountNotFoundException("Id não encontrado na base");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<DadosListagemProduto> excluir(@PathVariable String id) {
		
		DadosListagemProduto response = produtoService.excluirProduto(id);


		 return ResponseEntity.noContent().build();
	}
	
	
	
	@GetMapping("/{id}")
	public ResponseEntity<DadosDetalhamentoProduto> detalhar(@PathVariable String id) {
		
		DadosDetalhamentoProduto response = produtoService.detalharProduto(id);
		
		return ResponseEntity.ok(response);
	}
	
	

}
