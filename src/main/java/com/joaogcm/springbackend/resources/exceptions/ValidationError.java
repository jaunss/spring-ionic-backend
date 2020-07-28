package com.joaogcm.springbackend.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {
	
	private static final long serialVersionUID = 1L;
	
	private List<FieldMessage> erro = new ArrayList<FieldMessage>();
	
	public ValidationError(Long timestamp, Integer status, String error, String message, String path) {
		super(timestamp, status, error, message, path);
		
	}
	
	public List<FieldMessage> getErro() {
		return erro;
	}
	
	public void addError(String campoNome, String mensagem) {
		erro.add(new FieldMessage(campoNome, mensagem));
	}
}