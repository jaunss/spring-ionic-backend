package com.joaogcm.springbackend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.joaogcm.springbackend.entities.Categoria;
import com.joaogcm.springbackend.repositories.CategoriaRepository;
import com.joaogcm.springbackend.services.exceptions.DataIntegrityException;
import com.joaogcm.springbackend.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repository;
	
	/* Buscar uma Categoria por id */
	public Categoria findById(Integer id) {
		Optional<Categoria> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Causa: " + Categoria.class.getName())
				);
	}
	
	/* Inserir uma Categoria */
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repository.save(obj);
	}
	
	/* Atualizar uma Categoria */
	public Categoria update(Categoria obj) {
		findById(obj.getId());
		return repository.save(obj);
	}
	
	/* Remover uma Categoria por id */
	public void deleteById(Integer id) {
		findById(id);
		
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma Categoria que possui Produtos.");
		}
	}
	
	/* Buscar todas as Categorias */
	public List<Categoria> findAll() {
		return repository.findAll();
	}
	
	/* Paginação */
	public Page<Categoria> findPage(Integer pagina, Integer linhasPorPagina, String direcao, String ordenarPor) {
		PageRequest pageRequest = PageRequest.of(pagina, linhasPorPagina, Direction.valueOf(direcao), ordenarPor);
		return repository.findAll(pageRequest);
	}
}