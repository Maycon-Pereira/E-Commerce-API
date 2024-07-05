package com.api.commerce.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "carrinho")
@Entity(name = "Carrinho")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Carrinho {

	@Id
	private String id;

	private LocalDateTime created_at;
	private LocalDateTime updated_at;
	private Boolean ativo;

	private String user_id;

	@OneToMany(mappedBy = "carrinho_id", fetch = FetchType.LAZY)
	private List<Item> items;

}
