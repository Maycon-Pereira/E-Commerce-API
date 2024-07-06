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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.commerce.domain.carrinho.DadosAtualizacaoCarrinho;
import com.api.commerce.domain.carrinho.DadosDetalhamentoCarrinho;
import com.api.commerce.domain.carrinho.DadosListagemCarrinho;
import com.api.commerce.service.CarrinhoService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("carrinho")
public class CarrinhoController {

	@Autowired
	private CarrinhoService carrinhoService;
//
//	@PostMapping
//	@Transactional
//	public ResponseEntity<DadosDetalhamentoCarrinho> criarOCarrinho(@RequestBody @Valid DadosCadastroCarrinho dados,
//			UriComponentsBuilder uriBuilder) throws Exception {
//
//		DadosDetalhamentoCarrinho dadosDetalhamentoCarrinho = carrinhoService.criarCarrinho(dados);
//
//		var uri = uriBuilder.path("/carrinho/{id}").buildAndExpand(dadosDetalhamentoCarrinho.id()).toUri();
//
//		return ResponseEntity.created(uri).body(dadosDetalhamentoCarrinho);
//	}

	@GetMapping
	public ResponseEntity<Page<DadosListagemCarrinho>> listar(
			@PageableDefault(size = 10, sort = { "name" }) Pageable paginacao) {

		Page<DadosListagemCarrinho> response = carrinhoService.findAllByAtivoTrue(paginacao);

		return ResponseEntity.ok(response);
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<DadosDetalhamentoCarrinho> atualizar(@PathVariable String id, @RequestBody @Valid DadosAtualizacaoCarrinho dados)
			throws Exception {

		DadosDetalhamentoCarrinho response = carrinhoService.atualizarInformacoes(id, dados);
		if (response == null) {
			throw new AccountNotFoundException("Id não encontrado na base");
		}
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<DadosListagemCarrinho> excluir(@PathVariable String id) throws Exception {

		carrinhoService.excluirCarrinho(id);

		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<DadosDetalhamentoCarrinho> detalhar(@PathVariable String id) throws Exception {

		DadosDetalhamentoCarrinho response = carrinhoService.detalharCarrinho(id);

		return ResponseEntity.ok(response);
	}

}
