package com.joaogcm.springbackend.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.joaogcm.springbackend.dto.ClienteDTO;
import com.joaogcm.springbackend.entities.Cliente;
import com.joaogcm.springbackend.repositories.ClienteRepository;
import com.joaogcm.springbackend.resources.exceptions.FieldMessage;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private ClienteRepository repository;
	
	@Override
	public void initialize(ClienteUpdate ann) {
		
	}

	@Override
	public boolean isValid(ClienteDTO objDTO, ConstraintValidatorContext context) {
		
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer uriId = Integer.parseInt(map.get("id"));
		
		List<FieldMessage> list = new ArrayList<FieldMessage>();
		
		Cliente aux = repository.findByEmail(objDTO.getEmail());
		if (aux != null && !aux.getId().equals(uriId)) {
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