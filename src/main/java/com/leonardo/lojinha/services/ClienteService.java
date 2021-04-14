package com.leonardo.lojinha.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leonardo.lojinha.entity.Cliente;
import com.leonardo.lojinha.repositories.ClienteDAO;
import com.leonardo.lojinha.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteDAO categoriaDAO;

	public Optional<Cliente> findByID(Integer id) {
		Optional<Cliente> categoria = categoriaDAO.findById(id);
		
		if(categoria.isEmpty()) {
			throw new ObjectNotFoundException("Objeto não encontrado! id: " + id + ", Tipo: " + Cliente.class.getName());
		}
		return categoria;
	}
}
