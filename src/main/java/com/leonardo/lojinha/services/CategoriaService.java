package com.leonardo.lojinha.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leonardo.lojinha.entity.Categoria;
import com.leonardo.lojinha.repositories.CategoriaDAO;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaDAO categoriaDAO;

	public Optional<Categoria> findByID(Integer id) {
		Optional<Categoria> categoria = categoriaDAO.findById(id);
		return categoria;
	}
}
