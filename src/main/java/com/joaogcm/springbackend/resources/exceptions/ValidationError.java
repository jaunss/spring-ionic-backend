package com.joaogcm.springbackend.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {
	
	private static final long serialVersionUID = 1L;
	
	private List<FieldMessage> erro = new ArrayList<FieldMessage>();
	
	public ValidationError(Integer status, String mensagem, Long timestamp) {
		super(status, mensagem, timestamp);
		
	}
	
	public List<FieldMessage> getErro() {
		return erro;
	}
	
	public void addError(String campoNome, String mensagem) {
		erro.add(new FieldMessage(campoNome, mensagem));
	}
}