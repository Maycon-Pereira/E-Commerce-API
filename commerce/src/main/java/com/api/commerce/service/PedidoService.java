package com.api.commerce.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.commerce.domain.pedido.DadosAtualizacaoPedido;
import com.api.commerce.domain.pedido.DadosDetalhamentoPedido;
import com.api.commerce.domain.pedido.DadosFazerPedido;
import com.api.commerce.domain.pedido.DadosListagemPedido;
import com.api.commerce.entity.Carrinho;
import com.api.commerce.entity.Item;
import com.api.commerce.entity.Pedido;
import com.api.commerce.entity.Produto;
import com.api.commerce.entity.Usuario;
import com.api.commerce.repository.CarrinhoRepository;
import com.api.commerce.repository.ItemRepository;
import com.api.commerce.repository.PedidoRepository;
import com.api.commerce.repository.ProdutoRepository;
import com.api.commerce.repository.UsuarioRepository;

import jakarta.validation.Valid;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private CarrinhoRepository carrinhoRepository;
	
	@Autowired
	private ItemRepository itemRepository;



	public DadosDetalhamentoPedido fazerPedido(@Valid DadosFazerPedido dados) throws Exception {
	    Optional<Produto> produtoId = produtoRepository.findById(dados.product_id());
	    if (!produtoId.isPresent()) {
	        throw new AccountNotFoundException("Id do produto não encontrado na base");
	    }
	    Optional<Usuario> usuarioId = usuarioRepository.findById(dados.user_id());
	    if (!usuarioId.isPresent()) {
	        throw new AccountNotFoundException("Id do usuário não encontrado na base");
	    }
	    Produto produto = produtoId.get();

	    var createdTime = LocalDateTime.now();

	    Pedido pedido = new Pedido();
	    pedido.setId(UUID.randomUUID().toString());
	    pedido.setProduct_id(dados.product_id());
	    pedido.setUser_id(dados.user_id());
	    pedido.setCreated_at(createdTime);
	    pedido.setAtivo(true);

	    Carrinho carrinho = new Carrinho();
	    carrinho.setId(UUID.randomUUID().toString());
	    carrinho.setCreated_at(createdTime);
	    carrinho.setUser_id(dados.user_id());
	    carrinho.setAtivo(true);

	    // Cria um item a partir do produto
	    Item item = new Item();
	    item.setId(UUID.randomUUID().toString());
	    item.setName(produto.getName());
	    item.setProduct_id(dados.product_id());
	    item.setQuantity(produto.getQuantity()); // Define a quantidade do pedido
	    item.setPrice(produto.getPrice());
	    item.setDescription(produto.getDescription());
	    item.setCategory_id(produto.getCategory_id());
	    item.setAtivo(produto.getAtivo());
	    item.setImagem(produto.getImagem());
	    item.setCarrinho_id(carrinho.getId()); // Associa o item ao carrinho

	    List<Item> items = new ArrayList<>();
	    items.add(item);
	    carrinho.setItems(items);

	    carrinhoRepository.save(carrinho);
	    
	    itemRepository.save(item);

	    Pedido saved = pedidoRepository.save(pedido);

	    return new DadosDetalhamentoPedido(saved);
	}


	public Page<DadosListagemPedido> findAllByAtivoTrue(Pageable paginacao) {
		Page<DadosListagemPedido> response = pedidoRepository.findAllByAtivoTrue(paginacao)
				.map(DadosListagemPedido::new);
		return response;
	}

	public DadosDetalhamentoPedido atualizarInformacoes(String id, @Valid DadosAtualizacaoPedido dados)
			throws Exception {
		Optional<Pedido> procurado = pedidoRepository.findById(id);
		if (!procurado.isPresent()) {
			throw new AccountNotFoundException("Id do pedido não encontrado na base");
		}
		var updatedTime = LocalDateTime.now();

		Pedido pedido = new Pedido();
		pedido.setProduct_id(dados.product_id());
		pedido.setUser_id(dados.user_id());
		pedido.setCreated_at(updatedTime);

		Pedido saved = pedidoRepository.save(pedido);

		return new DadosDetalhamentoPedido(saved);
	}

	public DadosListagemPedido excluirPedido(String id) throws Exception {
		Optional<Pedido> procurado = pedidoRepository.findById(id);
		if (!procurado.isPresent()) {
			throw new AccountNotFoundException("Id do pedido não encontrado na base");
		}
		var canceledTime = LocalDateTime.now();
		Pedido pedido = procurado.get();
		pedido.setCanceled_at(canceledTime);
		pedido.setAtivo(false);

		Pedido saved = pedidoRepository.save(pedido);

		return new DadosListagemPedido(saved);
	}

	public DadosDetalhamentoPedido detalharPedido(String id) throws Exception {
		Optional<Pedido> procurado = pedidoRepository.findById(id);
		if (!procurado.isPresent()) {
			throw new AccountNotFoundException("Id do pedido não encontrado na base");
		}
		Pedido pedido = procurado.get();
		return new DadosDetalhamentoPedido(pedido);
	}

}
