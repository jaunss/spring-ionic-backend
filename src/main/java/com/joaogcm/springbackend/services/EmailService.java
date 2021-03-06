package com.joaogcm.springbackend.services;

import org.springframework.mail.SimpleMailMessage;

import com.joaogcm.springbackend.entities.Cliente;
import com.joaogcm.springbackend.entities.Pedido;

public interface EmailService {
	
	public void sendOrderConfirmationEmail(Pedido obj);
	public void sendEmail(SimpleMailMessage msg);
	public void sendNewPasswordEmail(Cliente cliente, String newPassword);
}