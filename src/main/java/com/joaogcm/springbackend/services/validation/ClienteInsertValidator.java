package com.joaogcm.springbackend.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.joaogcm.springbackend.dto.ClienteNewDTO;
import com.joaogcm.springbackend.entities.Cliente;
import com.joaogcm.springbackend.entities.enums.TipoCliente;
import com.joaogcm.springbackend.repositories.ClienteRepository;
import com.joaogcm.springbackend.resources.exceptions.FieldMessage;
import com.joaogcm.springbackend.services.validation.util.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
	
	@Autowired
	private ClienteRepository repository;
	
	@Override
	public void initialize(ClienteInsert ann) {
		
	}

	@Override
	public boolean isValid(ClienteNewDTO objDTO, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<FieldMessage>();
		
		if (objDTO.getTipo().equals(TipoCliente.PESSOAFISICA.getCodigo()) && !BR.isValidCPF(objDTO.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CPF inválido."));
		}
		
		if (objDTO.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCodigo()) && !BR.isValidCNPJ(objDTO.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido."));
		}
		
		Cliente aux = repository.findByEmail(objDTO.getEmail());
		if (aux != null) {
			list.add(new FieldMessage("email", "Email já existente."));
		}
		
		// inclua os testes aqui, inserindo erros na lista
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMensagem()).addPropertyNode(e.getCampoNome())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}