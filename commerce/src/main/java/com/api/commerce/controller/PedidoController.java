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

import com.api.commerce.domain.pedido.DadosAtualizacaoPedido;
import com.api.commerce.domain.pedido.DadosDetalhamentoPedido;
import com.api.commerce.domain.pedido.DadosFazerPedido;
import com.api.commerce.domain.pedido.DadosListagemPedido;
import com.api.commerce.service.PedidoService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("pedidos")
public class PedidoController {
	
	@Autowired
	private PedidoService pedidoService;
	
	@PostMapping
	@Transactional
	public ResponseEntity<DadosDetalhamentoPedido> fazerPedido(@RequestBody @Valid DadosFazerPedido dados,
			UriComponentsBuilder uriBuilder) throws Exception {
		
		DadosDetalhamentoPedido dadosDetalhamentoPedido = pedidoService.fazerPedido(dados);
		
		var uri = uriBuilder.path("/produtos/{id}").buildAndExpand(dadosDetalhamentoPedido.id()).toUri();

		return ResponseEntity.created(uri).body(dadosDetalhamentoPedido);
		
	}
	
	@GetMapping
	public ResponseEntity<Page<DadosListagemPedido>> listar(
			@PageableDefault(size = 10, sort = { "name" }) Pageable paginacao) {

		Page<DadosListagemPedido> response = pedidoService.findAllByAtivoTrue(paginacao);

		return ResponseEntity.ok(response);
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<DadosDetalhamentoPedido> atualizar(@PathVariable String id, @RequestBody @Valid DadosAtualizacaoPedido dados)
			throws Exception {

		DadosDetalhamentoPedido response = pedidoService.atualizarInformacoes(id, dados);
		if (response == null) {
			throw new AccountNotFoundException("Id não encontrado na base");
		}
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<DadosListagemPedido> excluir(@PathVariable String id) throws Exception {

		pedidoService.excluirPedido(id);

		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DadosDetalhamentoPedido> detalhar(@PathVariable String id) throws Exception {

		DadosDetalhamentoPedido response = pedidoService.detalharPedido(id);

		return ResponseEntity.ok(response);
	}
	
	

}
