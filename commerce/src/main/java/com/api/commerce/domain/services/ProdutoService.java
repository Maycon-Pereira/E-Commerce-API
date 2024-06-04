package com.api.commerce.domain.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.commerce.domain.categoria.CategoriaRepository;
import com.api.commerce.domain.categoria.CategoryProduct;
import com.api.commerce.domain.categoria.DadosDetalhamentoCategoria;
import com.api.commerce.domain.produto.DadosAtualizacaoProduto;
import com.api.commerce.domain.produto.DadosCadastroProduto;
import com.api.commerce.domain.produto.DadosDetalhamentoProduto;
import com.api.commerce.domain.produto.DadosListagemProduto;
import com.api.commerce.domain.produto.Produto;
import com.api.commerce.domain.produto.ProdutoRepository;
import com.api.commerce.domain.usuario.Usuario;
import com.api.commerce.domain.usuario.UsuarioRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	public DadosDetalhamentoProduto categorizarProduto(DadosCadastroProduto dadosProduto) {

		
		
		Optional<CategoryProduct> categoriaId = categoriaRepository.findById(dadosProduto.category_id());

		Optional<Usuario> usuarioId = usuarioRepository.findById(dadosProduto.user_id());
		
		if (!categoriaId.isPresent()) {
			return null;
		}
		if (!usuarioId.isPresent()) {
			return null;
		}
		
		
		Produto produto = new Produto();

		produto.setId(UUID.randomUUID().toString());

		produto.setName(dadosProduto.name());
		produto.setQuantity(dadosProduto.quantity());
		produto.setPrice(dadosProduto.price());
		produto.setDescription(dadosProduto.description());
		produto.setCategory_id(dadosProduto.category_id());
		produto.setUser_id(dadosProduto.user_id());
		produto.setAtivo(true);


		Produto saved = produtoRepository.save(produto);

		return new DadosDetalhamentoProduto(saved);
	}

	public Page<DadosListagemProduto> findAllByAtivoTrue(Pageable paginacao) {

		Page<DadosListagemProduto> response = produtoRepository.findAllByAtivoTrue(paginacao)
				.map(DadosListagemProduto::new);
		return response;
	}

	public DadosDetalhamentoProduto atualizarInformacoes(String id, DadosAtualizacaoProduto dadosProduto) {
		Optional<Produto> procurado = produtoRepository.findById(id);
		if (!procurado.isPresent()) {
			return null;
		}

		Produto produto = procurado.get();
		produto.setName(dadosProduto.name());
		produto.setQuantity(dadosProduto.quantity());
		produto.setPrice(dadosProduto.price());
		produto.setDescription(dadosProduto.description());

		produto.setCategory_id(dadosProduto.category_id());
		produto.setUser_id(dadosProduto.user_id());

		Produto saved = produtoRepository.save(produto);

		return new DadosDetalhamentoProduto(saved);
	}

	public DadosListagemProduto excluirProduto(String id) {
		Optional<Produto> procurado = produtoRepository.findById(id);
		if (!procurado.isPresent()) {
			return null;
		}
		
		Produto produto = procurado.get();
		produto.setAtivo(false);
		
		Produto saved = produtoRepository.save(produto);
		
		return new DadosListagemProduto(saved);
	}

	public DadosDetalhamentoProduto detalharProduto(String id) {
		Optional<Produto> procurado = produtoRepository.findById(id);

        if (!procurado.isPresent()) {
            return null;
        }

        Produto produto = procurado.get();
        return new DadosDetalhamentoProduto(produto);
	}


}