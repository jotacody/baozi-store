package com.baozoi.baozoi.repository;

import com.baozoi.baozoi.model.Cliente;
import com.baozoi.baozoi.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> { }
