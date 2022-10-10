package com.example.CRM;

import com.example.CRM.entities.Leads;
import com.example.CRM.menu.Navigate;
import com.example.CRM.repositories.LeadsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CrmApplication implements CommandLineRunner{

	@Autowired
	LeadsRepository leadsRepository;

	public static void main(String[] args) {
		SpringApplication.run(CrmApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Navigate.navigate(leadsRepository);
	}

//	private static final Logger log = LoggerFactory.getLogger(CrmApplication.class);

//	@Bean
//	public CommandLineRunner demo(LeadsRepository leadsRepository) {
//		return (args) -> {
//
//			// ESTO ES UN EJEMPLO PARA AÑADIR NEW LEAD
//
//
//
////			leadsRepository.save(new Leads("Julia Roberts", "+34 56436546", "julia.r@hotmail.com", "Movistar"));
////			leadsRepository.save(new Leads("George Clooney", "+41 78658554", "clooney.fckr@gmail.es", "Orange"));
////			leadsRepository.save(new Leads("Susan Sarandon", "+38 97781234", "susan.sar@yahoo.net", "PepePhone"));
//////			leadsRepository.save(new Leads("David", "Palmer"));
//////			leadsRepository.save(new Leads("Michelle", "Dessler"));
////
////
////			// fetch all customers
////			System.out.println();
////			System.out.println("-------------------------------");
////			System.out.println("Leads found with findAll():");
////			System.out.println("-------------------------------");
////			System.out.println();
////
////			for (Leads lead : leadsRepository.findAll()) {
////				System.out.println(lead.getName());
////			}
////			System.out.println();
////
////			System.out.println();
////			System.out.println("-------------------------------");
////			System.out.println("Probando Leads.addLead()");
////			System.out.println("-------------------------------");
////			System.out.println();
////
////			leadsRepository.save(Leads.addLead());
//
//			// fetch an individual customer by ID
////			Leads lead = leadsRepository.findById(1L);		NO FUNCIONA PROBLEMA CON EL LONG
////			Customer customer = repository.findById(1L);
////			log.info("Customer found with findById(1L):");
////			log.info("--------------------------------");
////			log.info(customer.toString());
////			log.info("");
////
////			// fetch customers by last name
////			log.info("Customer found with findByLastName('Bauer'):");
////			log.info("--------------------------------------------");
////			repository.findByLastName("Bauer").forEach(bauer -> {
////				log.info(bauer.toString());
////			});
////			// for (Customer bauer : repository.findByLastName("Bauer")) {
////			//  log.info(bauer.toString());
//			// }
////			log.info("");
//		};
//	}

}
