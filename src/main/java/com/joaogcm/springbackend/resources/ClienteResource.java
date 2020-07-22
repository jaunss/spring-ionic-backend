package com.joaogcm.springbackend.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.joaogcm.springbackend.dto.ClienteDTO;
import com.joaogcm.springbackend.dto.ClienteNewDTO;
import com.joaogcm.springbackend.entities.Cliente;
import com.joaogcm.springbackend.services.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

	@Autowired
	private ClienteService cliente;

	@GetMapping(value = "/{id}")
	public ResponseEntity<Cliente> findById(@PathVariable Integer id) {
		Cliente obj = cliente.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	/* Inserir uma Categoria */
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody ClienteNewDTO objDTO) {
		Cliente obj = cliente.fromDTO(objDTO);
		obj = cliente.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	/* Atualizar uma Cliente */
	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> update(@PathVariable Integer id, @Valid @RequestBody ClienteDTO objDTO) {
		Cliente obj = cliente.fromDTO(objDTO);
		obj.setId(id);
		obj = cliente.update(obj);
		return ResponseEntity.noContent().build();
	}

	/* Remover uma Cliente por id */
	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
		cliente.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	/* Buscar todas as Clientes */
	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping
	public ResponseEntity<List<ClienteDTO>> findAll() {
		List<Cliente> list = cliente.findAll();
		List<ClienteDTO> listDTO = list.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}

	/* Paginação */
	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping(value = "/pagina")
	public ResponseEntity<Page<ClienteDTO>> findPage(
			@RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
			@RequestParam(value = "linhasPorPagina", defaultValue = "24") Integer linhasPorPagina,
			@RequestParam(value = "direcao", defaultValue = "ASC") String direcao,
			@RequestParam(value = "ordenarPor", defaultValue = "nome") String ordenarPor) {
		Page<Cliente> list = cliente.findPage(pagina, linhasPorPagina, direcao, ordenarPor);
		Page<ClienteDTO> listDTO = list.map(obj -> new ClienteDTO(obj));
		return ResponseEntity.ok().body(listDTO);
	}
	
	@PostMapping(value = "/picture")
	public ResponseEntity<?> uploadProfilePicture(@RequestParam(name = "file") MultipartFile multiPartFile) {
		URI uri = cliente.uploadProfilePicture(multiPartFile);
		return ResponseEntity.created(uri).build();
	}
}