package com.api.commerce.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "pedidos")
@Entity(name = "Pedido")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Pedido {

	@Id
	private String id;

	private String product_id;
	private String user_id;
	
	private LocalDateTime created_at;
	private LocalDateTime updated_at;
	private LocalDateTime canceled_at;
	private Boolean ativo;

}
