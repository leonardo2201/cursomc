package com.leonardo.lojinha.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.leonardo.lojinha.entity.Categoria;

@Repository
public interface CategoriaDAO extends JpaRepository<Categoria, Integer>{

}
