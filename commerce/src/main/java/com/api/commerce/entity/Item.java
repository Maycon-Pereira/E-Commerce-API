package com.api.commerce.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "carrinho_item")
@Entity(name = "Carrinho_item")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Item {
	
	@Id
	private String id;

	
	private String name;
	private int quantity;
	private double price;
	private String description;
	private String category_id;
	private String carrinho_id;
	private String product_id;
	private Boolean ativo;

	private String imagem;
}