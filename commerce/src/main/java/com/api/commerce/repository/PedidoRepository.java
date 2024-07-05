package com.api.commerce.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.api.commerce.entity.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, String>{

	Page<Pedido> findAllByAtivoTrue(Pageable paginacao);

	@Query("SELECT p FROM Pedido p WHERE p.user_id = :user_id")
	Optional<Pedido> findByUserId(String user_id);

}
