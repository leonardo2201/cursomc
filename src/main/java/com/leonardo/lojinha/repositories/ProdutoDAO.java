package com.leonardo.lojinha.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.leonardo.lojinha.entity.Produto;

@Repository
public interface ProdutoDAO extends JpaRepository<Produto, Integer>{

}
