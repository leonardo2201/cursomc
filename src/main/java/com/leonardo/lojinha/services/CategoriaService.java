package com.leonardo.lojinha.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leonardo.lojinha.entity.Categoria;
import com.leonardo.lojinha.repositories.CategoriaDAO;
import com.leonardo.lojinha.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaDAO categoriaDAO;

	public Optional<Categoria> findById(Integer id) {
		Optional<Categoria> categoria = categoriaDAO.findById(id);
		
		if(categoria.isEmpty()) {
			throw new ObjectNotFoundException("Objeto não encontrado! id: " + id + ", Tipo: " + Categoria.class.getName());
		}
		return categoria;
	}

	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return categoriaDAO.save(obj);
		
	}

	public Categoria update(Categoria obj) {
		findById(obj.getId());
		return categoriaDAO.save(obj);
	}
}
