package com.api.commerce.domain.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.commerce.domain.categoria.CategoriaRepository;
import com.api.commerce.domain.categoria.CategoryProduct;
import com.api.commerce.domain.categoria.DadosAtualizacaoCategoria;
import com.api.commerce.domain.categoria.DadosCategorizarProduto;
import com.api.commerce.domain.categoria.DadosDetalhamentoCategoria;
import com.api.commerce.domain.categoria.DadosListagemCategoria;
import com.api.commerce.domain.produto.DadosDetalhamentoProduto;
import com.api.commerce.domain.produto.Produto;

import jakarta.validation.Valid;

@Service
public class CategoriaService {
	
	@Autowired
	CategoriaRepository categoriaRepository;

	public DadosDetalhamentoCategoria criarCategoria(DadosCategorizarProduto dadosCategoria) {
		

		CategoryProduct categoria = new CategoryProduct();

		categoria.setId(UUID.randomUUID().toString());

		categoria.setCategoryName(dadosCategoria.categoryName());
		categoria.setAtivo(true);

		CategoryProduct saved = categoriaRepository.save(categoria);

		return new DadosDetalhamentoCategoria(saved);

	}

	public Page<DadosListagemCategoria> findAllByAtivoTrue(Pageable paginacao) {
		
		Page<DadosListagemCategoria> response = categoriaRepository.findAllByAtivoTrue(paginacao).map(DadosListagemCategoria::new);
		return response;
	}

	public DadosDetalhamentoCategoria atualizarInformacoes(String id, @Valid DadosAtualizacaoCategoria dados) {
		Optional<CategoryProduct> procurado = categoriaRepository.findById(id);
		if (!procurado.isPresent()) {
			return null;
		}

		CategoryProduct produto = procurado.get();
		produto.setCategoryName(dados.categoryName());

		CategoryProduct saved = categoriaRepository.save(produto);

		return new DadosDetalhamentoCategoria(saved);
	}

	public DadosListagemCategoria excluirCategoria(String id) {
		Optional<CategoryProduct> procurado = categoriaRepository.findById(id);
		if (!procurado.isPresent()) {
			return null;
		}
		
		CategoryProduct categoria = procurado.get();
		categoria.setAtivo(false);
		
		CategoryProduct saved = categoriaRepository.save(categoria);
		
		return new DadosListagemCategoria(saved);
	}

	public DadosDetalhamentoCategoria detalharCategoria(String id) {
        Optional<CategoryProduct> procurado = categoriaRepository.findById(id);

        if (!procurado.isPresent()) {
            return null;
        }

        CategoryProduct categoria = procurado.get();
        return new DadosDetalhamentoCategoria(categoria);
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
