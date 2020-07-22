package com.joaogcm.springbackend.services;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.joaogcm.springbackend.dto.ClienteDTO;
import com.joaogcm.springbackend.dto.ClienteNewDTO;
import com.joaogcm.springbackend.entities.Cidade;
import com.joaogcm.springbackend.entities.Cliente;
import com.joaogcm.springbackend.entities.Endereco;
import com.joaogcm.springbackend.entities.enums.PerfilCliente;
import com.joaogcm.springbackend.entities.enums.TipoCliente;
import com.joaogcm.springbackend.repositories.ClienteRepository;
import com.joaogcm.springbackend.repositories.EnderecoRepository;
import com.joaogcm.springbackend.security.UserSS;
import com.joaogcm.springbackend.services.exceptions.AuthorizationException;
import com.joaogcm.springbackend.services.exceptions.DataIntegrityException;
import com.joaogcm.springbackend.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private S3Service s3Service;
	
	public Cliente findById(Integer id) {
		UserSS user = UserService.userAuthenticated();
		if (user == null || !user.hasRole(PerfilCliente.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizationException("Acesso negado!");
		}
		
		Optional<Cliente> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado id: " + id + ", Causa: " + Cliente.class.getName()));
	}
	
	/* Inserir uma Categoria */
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = repository.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
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
		return new Cliente(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(), null, null, null);
	}
	
	public Cliente fromDTO(ClienteNewDTO objDTO) {
		Cliente cli = new Cliente(null, objDTO.getNome(), objDTO.getEmail(), objDTO.getCpfOuCnpj(), TipoCliente.toEnum(objDTO.getTipo()), bCryptPasswordEncoder.encode(objDTO.getSenha()));
		Cidade cid = new Cidade(objDTO.getCidadeId(), null, null);
		Endereco end = new Endereco(null, objDTO.getLogradouro(), objDTO.getNumero(), objDTO.getComplemento(), objDTO.getBairro(), objDTO.getCep(), cli, cid);
		cli.getEnderecos().add(end);
		cli.getTelefones().add(objDTO.getTelefone1());
		if (objDTO.getTelefone2() != null) {
			cli.getTelefones().add(objDTO.getTelefone2());
		}
		
		if (objDTO.getTelefone3() != null) {
			cli.getTelefones().add(objDTO.getTelefone3());
		}
		return cli;
	}
	
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
	
	public URI uploadProfilePicture(MultipartFile multiPartFile) {
		return s3Service.uploadFile(multiPartFile);
	}
}