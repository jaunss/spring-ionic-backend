package com.joaogcm.springbackend.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.joaogcm.springbackend.entities.PagamentoComBoleto;

@Service
public class BoletoService {
	
	public void preencherPagamentoComBoleto(PagamentoComBoleto pagto, Date dataDoPedido) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dataDoPedido);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		pagto.setDataVencimento(cal.getTime());
	}
}