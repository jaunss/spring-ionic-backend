package com.joaogcm.springbackend.entities.enums;

public enum PerfilCliente {
	
	ADMIN(1, "ROLE_ADMIN"),
	CLIENTE(2, "ROLE_CLIENTE");
	
	private Integer status;
	private String descricao;
	
	private PerfilCliente(Integer status, String descricao) {
		this.status = status;
		this.descricao = descricao;
	}
	
	public Integer getStatus() {
		return status;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public static PerfilCliente status(Integer codigo) {
		if (codigo == null) {
			return null;
		}
		
		for (PerfilCliente perfil : PerfilCliente.values()) {
			if (codigo.equals(perfil.getStatus())) {
				return perfil;
			}
		}
		throw new IllegalArgumentException("Código inválido! Id: " + codigo);
	}
}