package com.leonardo.lojinha.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.leonardo.lojinha.entity.Cliente;

@Repository
public interface ClienteDAO extends JpaRepository<Cliente, Integer>{

}
