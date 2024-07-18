package com.api.commerce.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.security.auth.login.AccountNotFoundException;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.api.commerce.domain.imagem.DadosDetalhamentoImagens;
import com.api.commerce.entity.Produto;
import com.api.commerce.entity.ProdutoImagem;
import com.api.commerce.repository.ImagemRepository;
import com.api.commerce.repository.ProdutoRepository;

@Service
public class ImagemService {

	@Autowired
	private ImagemRepository imagemRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	// IMAGEM UPLOAD E DOWNLOAD
	public void upload(MultipartFile[] files, String id) throws Exception {
		Optional<Produto> produtoOptional = produtoRepository.findById(id);
		if (produtoOptional.isEmpty()) {
			throw new RuntimeException("Produto não encontrado");
		}

		Produto produto = produtoOptional.get();

		for (MultipartFile file : files) {
			ProdutoImagem produtoImagem = new ProdutoImagem();
			produtoImagem.setId(UUID.randomUUID().toString());
			produtoImagem.setImagem(Base64.encodeBase64String(file.getBytes()));
			produtoImagem.setAtivo(true);
			produto.addImagem(produtoImagem);
		}

		produtoRepository.save(produto);
	}

	public List<DadosDetalhamentoImagens> listarImagens() {
		List<ProdutoImagem> imagens = imagemRepository.findAll();
		return imagens.stream().map(
				imagem -> new DadosDetalhamentoImagens(imagem.getId(), imagem.getProduto().getId(), imagem.getAtivo(), imagem.getImagem()))
				.collect(Collectors.toList());
	}

	public DadosDetalhamentoImagens excluirImagem(String id) throws Exception {
		Optional<ProdutoImagem> procurado = imagemRepository.findById(id);
		if (!procurado.isPresent()) {
			throw new AccountNotFoundException("Id da imagem não encontrado na base");
		}
		
		ProdutoImagem imagem = procurado.get();
		imagem.setAtivo(false);
		
		ProdutoImagem saved = imagemRepository.save(imagem);
		
		return new DadosDetalhamentoImagens(saved);
	}

}
