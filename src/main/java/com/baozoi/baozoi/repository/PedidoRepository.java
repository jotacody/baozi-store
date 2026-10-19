package com.baozoi.baozoi.repository;

import com.baozoi.baozoi.model.Pedido;
import com.baozoi.baozoi.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> { }
