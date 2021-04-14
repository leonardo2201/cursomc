package com.leonardo.lojinha.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.leonardo.lojinha.entity.Pagamento;

@Repository
public interface PagamentoDAO extends JpaRepository<Pagamento, Integer>{

}
