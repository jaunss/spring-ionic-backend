package com.joaogcm.springbackend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.joaogcm.springbackend.entities.Categoria;
import com.joaogcm.springbackend.entities.Produto;
import com.joaogcm.springbackend.repositories.CategoriaRepository;
import com.joaogcm.springbackend.repositories.ProdutoRepository;
import com.joaogcm.springbackend.services.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository repository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Produto findById(Integer id) {
		Optional<Produto> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Causa: " + Produto.class.getName())
				);
	}
	
	public Page<Produto> search(String nome, List<Integer> ids, Integer pagina, Integer linhasPorPagina, String direcao, String ordenarPor){
		PageRequest pageRequest = PageRequest.of(pagina, linhasPorPagina, Direction.valueOf(direcao), ordenarPor);
		List<Categoria> categorias = categoriaRepository.findAllById(ids);
		return repository.search(nome, categorias, pageRequest);
	}
}