package com.api.commerce.service;

import java.util.Optional;
import java.util.UUID;

import javax.security.auth.login.AccountNotFoundException;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.api.commerce.domain.produto.DadosAtualizacaoProduto;
import com.api.commerce.domain.produto.DadosCadastroProduto;
import com.api.commerce.domain.produto.DadosDetalhamentoProduto;
import com.api.commerce.domain.produto.DadosListagemProduto;
import com.api.commerce.entity.CategoryProduct;
import com.api.commerce.entity.Produto;
import com.api.commerce.repository.CategoriaRepository;
import com.api.commerce.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	public DadosDetalhamentoProduto criarProduto(DadosCadastroProduto dadosProduto) throws Exception {

		Optional<CategoryProduct> categoriaId = categoriaRepository.findById(dadosProduto.category_id());
		
		if (!categoriaId.isPresent()) {
			throw new AccountNotFoundException("Id da categoria não encontrado na base");
		}
		
		Produto produto = new Produto();

		produto.setId(UUID.randomUUID().toString());

		produto.setName(dadosProduto.name());
		produto.setQuantity(dadosProduto.quantity());
		produto.setPrice(dadosProduto.price());
		produto.setDescription(dadosProduto.description());
		produto.setCategory_id(dadosProduto.category_id());
		produto.setAtivo(true);

		Produto saved = produtoRepository.save(produto);

		return new DadosDetalhamentoProduto(saved);
	}

	public Page<DadosListagemProduto> findAllByAtivoTrue(Pageable paginacao) {

		Page<DadosListagemProduto> response = produtoRepository.findAllByAtivoTrue(paginacao)
				.map(DadosListagemProduto::new);
		return response;
	}

	public DadosDetalhamentoProduto atualizarInformacoes(String id, DadosAtualizacaoProduto dadosProduto) throws Exception {
		Optional<Produto> procurado = produtoRepository.findById(id);
		if (!procurado.isPresent()) {
			throw new AccountNotFoundException("Id do produto não encontrado na base");
		}

		Produto produto = procurado.get();
		produto.setName(dadosProduto.name());
		produto.setQuantity(dadosProduto.quantity());
		produto.setPrice(dadosProduto.price());
		produto.setDescription(dadosProduto.description());

		produto.setCategory_id(dadosProduto.category_id());

		Produto saved = produtoRepository.save(produto);

		return new DadosDetalhamentoProduto(saved);
	}

	public DadosListagemProduto excluirProduto(String id) throws Exception {
		Optional<Produto> procurado = produtoRepository.findById(id);
		if (!procurado.isPresent()) {
			throw new AccountNotFoundException("Id do produto não encontrado na base");
		}
		
		Produto produto = procurado.get();
		produto.setAtivo(false);
		
		Produto saved = produtoRepository.save(produto);
		
		return new DadosListagemProduto(saved);
	}

	public DadosDetalhamentoProduto detalharProduto(String id) throws Exception {
		Optional<Produto> procurado = produtoRepository.findById(id);

        if (!procurado.isPresent()) {
        	throw new AccountNotFoundException("Id do produto não encontrado na base");
        }

        Produto produto = procurado.get();
        return new DadosDetalhamentoProduto(produto);
	}

	//IMAGEM UPLOAD E DOWNLOAD
		public void upload(MultipartFile file, String id) throws Exception {
			Optional<Produto> procurado = produtoRepository.findById(id);

		    if (!procurado.isPresent()) {
		        throw new RuntimeException("Usuario não encontrado");
		    }
		    Produto produto = procurado.get();

		    byte[] imagemBytes = file.getBytes();
		    String imagemBase64 = Base64.encodeBase64String(imagemBytes);
		    produto.setImagem(imagemBase64);

		    produtoRepository.save(produto);
		}	

}