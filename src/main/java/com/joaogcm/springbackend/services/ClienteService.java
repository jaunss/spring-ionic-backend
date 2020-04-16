package com.joaogcm.springbackend.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joaogcm.springbackend.entities.Cliente;
import com.joaogcm.springbackend.repositories.ClienteRepository;
import com.joaogcm.springbackend.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repository;
	
	public Cliente findById(Integer id) {
		Optional<Cliente> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado id: " + id + ", Causa: " + Cliente.class.getName()));
	}
}