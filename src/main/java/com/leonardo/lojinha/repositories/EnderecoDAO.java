package com.leonardo.lojinha.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.leonardo.lojinha.entity.Endereco;

@Repository
public interface EnderecoDAO extends JpaRepository<Endereco, Integer>{

}
