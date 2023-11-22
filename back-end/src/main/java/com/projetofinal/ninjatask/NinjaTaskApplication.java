package com.projetofinal.ninjatask;

import com.projetofinal.ninjatask.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class NinjaTaskApplication {
	@Autowired
	private EmailService senderService;

	public static void main(String[] args) {
		SpringApplication.run(NinjaTaskApplication.class, args);
	}

//	@EventListener(ApplicationReadyEvent.class)
//	public void sendEmail(){
//		senderService.sendEmail("fehihat163@inkiny.com",
//				"esse é o assunto", "esse é o texto do email");
//	}
}
