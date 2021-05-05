package com.leonardo.lojinha.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leonardo.lojinha.entity.Categoria;
import com.leonardo.lojinha.entity.Pedido;
import com.leonardo.lojinha.repositories.PedidoDAO;
import com.leonardo.lojinha.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoDAO pedidoDAO;

	public Optional<Pedido> findByID(Integer id) {
		Optional<Pedido> pedido = pedidoDAO.findById(id);
		
		if(pedido.isEmpty()) {
			throw new ObjectNotFoundException("Objeto não encontrado! id: " + id + ", Tipo: " + Categoria.class.getName());
		}
		return pedido;
	}
}
