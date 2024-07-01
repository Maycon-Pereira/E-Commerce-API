package com.api.commerce.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.api.commerce.entity.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, String>{

	Page<Pedido> findAllByAtivoTrue(Pageable paginacao);

}
