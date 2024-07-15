package com.api.commerce.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "produtos")
@Entity(name = "Produto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Produto {

	@Id
	private String id;
	private String name;
	private int quantity;
	private double price;
	private String color;
	private String size;
	private String discount;
	private String description;
	private String category_id;
	private Boolean ativo;
	
	@OneToMany(mappedBy = "produto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProdutoImagem> imagens = new ArrayList<>();


	public void addImagem(ProdutoImagem imagem) {
        imagem.setProduto(this);
        this.imagens.add(imagem);
    }

    public void removeImagem(ProdutoImagem imagem) {
        imagem.setProduto(null);
        this.imagens.remove(imagem);
    }
}
