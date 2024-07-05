package com.api.commerce.service;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.commerce.domain.carrinho.DadosAtualizacaoCarrinho;
import com.api.commerce.domain.carrinho.DadosCadastroCarrinho;
import com.api.commerce.domain.carrinho.DadosDetalhamentoCarrinho;
import com.api.commerce.domain.carrinho.DadosListagemCarrinho;
import com.api.commerce.entity.Carrinho;
import com.api.commerce.entity.Pedido;
import com.api.commerce.entity.Produto;
import com.api.commerce.repository.CarrinhoRepository;
import com.api.commerce.repository.PedidoRepository;
import com.api.commerce.repository.ProdutoRepository;

import jakarta.validation.Valid;

@Service
public class CarrinhoService {

	@Autowired
	private CarrinhoRepository carrinhoRepository;

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;

	public DadosDetalhamentoCarrinho criarCarrinho(DadosCadastroCarrinho dadosCarrinho) throws Exception {

	    Optional<Carrinho> userId = carrinhoRepository.findById(dadosCarrinho.user_id());
	    Optional<Pedido> pedidoUserId = pedidoRepository.findByUserId(dadosCarrinho.user_id());

	    if (!userId.isPresent() && !pedidoUserId.isPresent()) {
	        throw new AccountNotFoundException("Id do usuário não encontrado na base");
	    }
	    if (!userId.get().getId().equals(pedidoUserId.get().getUser_id())) {
	        throw new AccountNotFoundException("Id do usuário incorreto");
	    }

	    Pedido pedidoAll = pedidoUserId.get();
	    Optional<Produto> produtoOptional = produtoRepository.findById(pedidoAll.getProduct_id());

	    if (!produtoOptional.isPresent()) {
	        throw new AccountNotFoundException("Produto não encontrado na base");
	    }



	    
	    return new DadosDetalhamentoCarrinho(null);
	}


	
	
	
	public Page<DadosListagemCarrinho> findAllByAtivoTrue(Pageable paginacao) {
		Page<DadosListagemCarrinho> response = carrinhoRepository.findAllByAtivoTrue(paginacao)
				.map(DadosListagemCarrinho::new);
		return response;
	}

	public DadosDetalhamentoCarrinho atualizarInformacoes(String id, @Valid DadosAtualizacaoCarrinho dados)
			throws AccountNotFoundException {
		Optional<Carrinho> procurado = carrinhoRepository.findById(id);
		if (!procurado.isPresent()) {
			throw new AccountNotFoundException("Id do carrinho não encontrado na base");
		}
		var updatedTime = LocalDateTime.now();

		Carrinho carrinho = procurado.get();
		carrinho.setUpdated_at(updatedTime);
		carrinho.setUser_id(dados.user_id());
		carrinho.setItems(dados.items());

		Carrinho saved = carrinhoRepository.save(carrinho);

		return new DadosDetalhamentoCarrinho(saved);
	}

	public DadosListagemCarrinho excluirCarrinho(String id) throws AccountNotFoundException {
		Optional<Carrinho> procurado = carrinhoRepository.findById(id);
		if (!procurado.isPresent()) {
			throw new AccountNotFoundException("Id do carrinho não encontrado na base");
		}

		Carrinho carrinho = procurado.get();
		carrinho.setAtivo(false);

		Carrinho saved = carrinhoRepository.save(carrinho);

		return new DadosListagemCarrinho(saved);
	}

	public DadosDetalhamentoCarrinho detalharCarrinho(String id) throws AccountNotFoundException {
		Optional<Carrinho> procurado = carrinhoRepository.findById(id);

		if (!procurado.isPresent()) {
			throw new AccountNotFoundException("Id do produto não encontrado na base");
		}

		Carrinho carrinho = procurado.get();
		return new DadosDetalhamentoCarrinho(carrinho);
	}

}
