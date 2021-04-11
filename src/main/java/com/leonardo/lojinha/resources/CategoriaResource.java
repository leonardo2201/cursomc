package com.leonardo.lojinha.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.leonardo.lojinha.entity.Categoria;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {

	@RequestMapping(method=RequestMethod.GET)
	public List<Categoria> Listar() {
		
		Categoria cat1 = new Categoria(1, "informatica");
		Categoria cat2 = new Categoria(2, "Escritorio");
		
		List<Categoria> list = new ArrayList<>();
		list.add(cat2);
		list.add(cat1);
		
		return list;
	}
}
