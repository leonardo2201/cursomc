package com.leonardo.lojinha.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.leonardo.lojinha.entity.ItemPedido;

@Repository
public interface ItemPedidoDAO extends JpaRepository<ItemPedido, Integer>{

}
