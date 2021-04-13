package com.leonardo.lojinha;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.leonardo.lojinha.entity.Categoria;
import com.leonardo.lojinha.entity.Cidade;
import com.leonardo.lojinha.entity.Estado;
import com.leonardo.lojinha.entity.Produto;
import com.leonardo.lojinha.repositories.CategoriaDAO;
import com.leonardo.lojinha.repositories.CidadeDAO;
import com.leonardo.lojinha.repositories.EstadoDAO;
import com.leonardo.lojinha.repositories.ProdutoDAO;

@SpringBootApplication
public class LojinhaApplication implements CommandLineRunner{
	
	@Autowired
	private CategoriaDAO dao;
	
	@Autowired
	private ProdutoDAO pdao;
	
	@Autowired
	private CidadeDAO cdao;
	
	@Autowired
	private EstadoDAO edao;

	public static void main(String[] args) {
		SpringApplication.run(LojinhaApplication.class, args);
	}
	
	@Override
	public void run(String...args) throws Exception{
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		dao.saveAll(Arrays.asList(cat1, cat2));
		pdao.saveAll(Arrays.asList(p1, p2, p3));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est1.getCidades().addAll(Arrays.asList(c2, c3));
		
		edao.saveAll(Arrays.asList(est1, est2));
		cdao.saveAll(Arrays.asList(c1, c2, c3));
		
	}

}
