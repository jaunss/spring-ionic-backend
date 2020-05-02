package com.joaogcm.springbackend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.joaogcm.springbackend.dto.ClienteDTO;
import com.joaogcm.springbackend.entities.Cliente;
import com.joaogcm.springbackend.repositories.ClienteRepository;
import com.joaogcm.springbackend.services.exceptions.DataIntegrityException;
import com.joaogcm.springbackend.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repository;
	
	public Cliente findById(Integer id) {
		Optional<Cliente> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado id: " + id + ", Causa: " + Cliente.class.getName()));
	}
	
	/* Atualizar uma Cliente */
	public Cliente update(Cliente obj) {
		Cliente newObj = findById(obj.getId());
		updateData(newObj, obj);
		return repository.save(obj);
	}

	/* Remover uma Cliente por id */
	public void deleteById(Integer id) {
		findById(id);
		
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir porque há entidades relacionadas.");
		}
	}
	
	/* Buscar todas as Clientes */
	public List<Cliente> findAll() {
		return repository.findAll();
	}
	
	/* Paginação */
	public Page<Cliente> findPage(Integer pagina, Integer linhasPorPagina, String direcao, String ordenarPor) {
		PageRequest pageRequest = PageRequest.of(pagina, linhasPorPagina, Direction.valueOf(direcao), ordenarPor);
		return repository.findAll(pageRequest);
	}
	
	public Cliente fromDTO(ClienteDTO objDTO) {
		return new Cliente(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(), null, null);
	}
	
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
}