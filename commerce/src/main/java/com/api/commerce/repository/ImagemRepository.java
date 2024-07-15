package com.api.commerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.commerce.entity.ProdutoImagem;

public interface ImagemRepository  extends JpaRepository<ProdutoImagem, String>{

}
