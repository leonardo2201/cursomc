package com.leonardo.lojinha.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.leonardo.lojinha.entity.Cidade;

@Repository
public interface CidadeDAO extends JpaRepository<Cidade, Integer>{

}
