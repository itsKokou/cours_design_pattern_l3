package com.ism.gestioncommande;

import com.ism.gestioncommande.data.entities.Client;
import com.ism.gestioncommande.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.thymeleaf.util.DateUtils;


@SpringBootApplication
public class GestionCommandeApplication implements CommandLineRunner {

	public static void main(String[] args) {SpringApplication.run(GestionCommandeApplication.class, args);
	}

	@Autowired
	private ClientService clientService;
	@Override
	public void run(String... args) throws Exception {
		//List<Client> clients = clientService.getAll();
		//clients.forEach(System.out::println);
//		clients.stream().forEach(client -> {
//			System.out.println(client.getNomComplet());
//		});

	}

}
