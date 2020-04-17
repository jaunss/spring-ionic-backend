package com.joaogcm.springbackend.entities.enums;

public enum EstadoPagamento {
	
	PENDENTE(1, "Pagamento Pendente"),
	QUITADO(2, "Pagamento Quitado"),
	CANCELADO(3, "Pagamento Cancelado");
	
	private Integer codigo;
	private String descricao;
	
	private EstadoPagamento(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	public Integer getCodigo() {
		return codigo;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public static EstadoPagamento toEnum(Integer codigo) {
		if (codigo == null) {
			return null;
		}
		
		for (EstadoPagamento x : EstadoPagamento.values()) {
			if (codigo.equals(x.getCodigo())) {
				return x;
			}
		}
		throw new IllegalArgumentException("id inválido: " + codigo);
	}
}