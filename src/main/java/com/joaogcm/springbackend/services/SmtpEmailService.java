package com.joaogcm.springbackend.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class SmtpEmailService extends AbstractEmailService {
	
	@Autowired
	private MailSender emailRemetente;
	
	private static final Logger LOG = LoggerFactory.getLogger(SmtpEmailService.class);
	
	@Override
	public void sendEmail(SimpleMailMessage mensagem) {
		LOG.info("Simulando envio de email...");
		emailRemetente.send(mensagem);
		LOG.info("Email enviado!");
	}
}