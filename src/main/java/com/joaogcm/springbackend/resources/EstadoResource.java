package com.joaogcm.springbackend.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joaogcm.springbackend.dto.CidadeDTO;
import com.joaogcm.springbackend.dto.EstadoDTO;
import com.joaogcm.springbackend.entities.Cidade;
import com.joaogcm.springbackend.entities.Estado;
import com.joaogcm.springbackend.services.CidadeService;
import com.joaogcm.springbackend.services.EstadoService;

@RestController
@RequestMapping(value = "/estados")
public class EstadoResource {
	
	@Autowired
	private EstadoService estadoService;
	
	@Autowired
	private CidadeService cidadeService;
	
	@GetMapping
	public ResponseEntity<List<EstadoDTO>> findAll() {
		List<Estado> obj = estadoService.findAll();
		List<EstadoDTO> objDTO = obj.stream().map(list -> new EstadoDTO(list)).collect(Collectors.toList());
		return ResponseEntity.ok().body(objDTO);
	}
	
	@GetMapping(value = "/{estadoId}/cidades")
	public ResponseEntity<List<CidadeDTO>> findCidades(@PathVariable Integer estadoId) {
		List<Cidade> obj = cidadeService.findByEstado(estadoId);
		List<CidadeDTO> objDTO = obj.stream().map(list -> new CidadeDTO(list)).collect(Collectors.toList());
		return ResponseEntity.ok().body(objDTO);
	}
}