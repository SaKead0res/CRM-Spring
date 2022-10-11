package com.example.CRM;

import com.example.CRM.entities.Leads;
import com.example.CRM.entities.Opportunity;
import com.example.CRM.enums.Product;
import com.example.CRM.enums.Status;
import com.example.CRM.menu.Navigate;
import com.example.CRM.repositories.LeadsRepository;
import com.example.CRM.repositories.OpportunityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CrmApplication implements CommandLineRunner{

	@Autowired
	LeadsRepository leadsRepository;

	@Autowired
	OpportunityRepository opportunityRepository;

	public static void main(String[] args) {
		SpringApplication.run(CrmApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		leadsRepository.saveAll(List.of(
				new Leads("Julia Roberts", "+34 56436546", "julia.r@hotmail.com", "Movistar"),
				new Leads("George Clooney", "+41 78658554", "clooney.fckr@gmail.es", "Orange"),
				new Leads("Susan Sarandon", "+38 97781234", "susan.sar@yahoo.net", "PepePhone")
		));

		opportunityRepository.saveAll(List.of(
				new Opportunity(Product.BOX, 200, Status.OPEN, null),
				new Opportunity(Product.HYBRID, 1100, Status.CLOSED_LOST, null),
				new Opportunity(Product.FLATBED, 660, Status.CLOSED_WON, null)
		));
		Navigate.navigate(leadsRepository);
	}

}
