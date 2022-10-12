package com.example.CRM;

import com.example.CRM.entities.*;
import com.example.CRM.enums.Commands;
import com.example.CRM.enums.Industries;
import com.example.CRM.enums.Product;
import com.example.CRM.enums.Status;
import com.example.CRM.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class CrmApplication implements CommandLineRunner{

	@Autowired
	LeadsRepository leadsRepository;

	@Autowired
	OpportunityRepository opportunityRepository;

	@Autowired
	ContactRepository contactRepository;

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	SalesRepRepository salesRepRepository;

	public static void main(String[] args) {
		SpringApplication.run(CrmApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		leadsRepository.saveAll(List.of(
				new Leads("Julia Roberts", "+34 56436546", "julia.r@hotmail.com", "Movistar", null),
				new Leads("George Clooney", "+41 78658554", "clooney.fckr@gmail.es", "Orange",null),
				new Leads("Susan Sarandon", "+38 97781234", "susan.sar@yahoo.net", "PepePhone",null)
		));

		opportunityRepository.saveAll(List.of(
				new Opportunity(Product.BOX, 200, Status.OPEN, null),
				new Opportunity(Product.HYBRID, 1100, Status.CLOSED_LOST, null),
				new Opportunity(Product.FLATBED, 660, Status.CLOSED_WON, null)
		));
//		Navigate.navigate(leadsRepository);

		navigate();



	}

	public void navigate() throws InterruptedException {

		Leads leads = new Leads();
		Contact contact = new Contact();
		Opportunity opportunity = new Opportunity();
		Account account = new Account();
		SalesRep salesRep = new SalesRep();



		System.out.print("{  LEADS : " + leadsRepository.count() + "    } ");
		System.out.print("{ OPPORTUNITIES : " + opportunityRepository.count() + " } ");
		System.out.println("{  ACCOUNTS : " + accountRepository.count() + "   } ");
		System.out.println("{  SALESREP : " + salesRepRepository.count() + "   } ");

		Commands command = null;
		try {
			command = Commands.valueOf( input("Please, enter a command: ") );//esta funcion intenta dar a la variable command el valor de una String(input())
//                                                  pero como es de clase Enum solo cogera el valor en caso de que exista en la
//                                                  clase Enum Commands.
		} catch ( IllegalArgumentException e ) {
			System.err.println( "This command doesn't exists. Try again or type <help>." );
			TimeUnit.MILLISECONDS.sleep(1000);
			navigate();
		}

		switch (command) {
			case NEWLEAD:
				try {
					leads = Leads.addLead(
							input("- Introduce a Name: "),
							input("- Introduce a Phone Number: "),
							input("- Introduce an Email: "),
							input("- Introduce a Company Name: ")
					);

					leads.setSalesRep(salesRepRepository.findById(Long.parseLong(input("- Introduce The SalesRep id: "))).get());

				} catch (IllegalArgumentException e) {
					System.err.println(e.getMessage());
					navigate();
				}

				System.out.println("\nThe new " + (char) 27 + "[33m" + "LEAD" + (char) 27 + "[0m" + " is created correctly.");

				System.out.println(
						"Lead {ID: " + leads.getId() +
								" | Name: " + leads.getName() +
								" | Phone: " + leads.getPhoneNumber() +
								" | Email: " + leads.getEmailAddress() +
								" | Company Name: " + leads.getCompanyName() + " }\n"
				);

				leadsRepository.save(leads);

				break;
			case SHOWLEADS:
				Leads.showLeads(leadsRepository);
				break;
			case SHOWOPPORTUNITIES:
				Opportunity.showOpportunities(opportunityRepository); // HECHO
				break;
			case SHOWACCOUNTS:
//                Account.showAccounts();
				break;
			case LOOKUPLEAD:
				Leads.lookupLead(leadsRepository);
				break;
			case LOOKUPACCOUNT:
//                Account.lookupAccount();
				break;
			case LOOKUPOPPORTUNITY:
				Opportunity.lookupOpportunity(opportunityRepository);   //HECHO
				break;
			case CONVERT:
				try {
					contact = Contact.addContact(Leads.convertLead(leadsRepository));

					System.out.println("\nThe new " + (char)27 + "[33m" + "CONTACT" + (char)27 + "[0m" + " is created correctly.");

					System.out.println(
							"Contact {ID: " + contact.getId() +
									" | Name: " + contact.getName() +
									" | Phone: " + contact.getPhoneNumber() +
									" | Email: " + contact.getEmailAddress() +
									" | Company Name: " + contact.getCompanyName() + " }\n"
					);


				}catch (IllegalArgumentException e) {
					System.err.println(e.getMessage());
					navigate();
				}
				try {

					opportunity = Opportunity.addOpportunity(
							Product.valueOf(input("- Introduce the Interested Product: ")),
							Integer.parseInt(input("- Introduce the Interested Quantity: ")),
							Status.OPEN);

					System.out.println("\nThe new " + (char)27 + "[33m" + "OPPORTUNITY" + (char)27 + "[0m" + " is created correctly.");

					System.out.println(
							"Opportunity {ID: " + opportunity.getId() +
									" | Product: " + opportunity.getProduct() +
									" | Interested Quantity: " + opportunity.getQuantity() +
									" | Status: " + opportunity.getStatus() +
									" | Related Account: " + opportunity.getAccount() + " }\n"
					);


				}catch (IllegalArgumentException e) {
					System.err.println(e.getMessage());
					navigate();
				}

				try {

					account = accountRepository.save(Account.addAccount(
							Industries.valueOf(input("- Introduce the Industries: ")),
							Integer.parseInt(input("- Introduce the Employee Count: ")),
							input("- Introduce the City: "),
							input("- Introduce the Country: ")
					));


					System.out.println("\nThe new " + (char)27 + "[33m" + "ACCOUNT" + (char)27 + "[0m" + " is created correctly.");

					System.out.println(
							"Contact {ID: " + account.getId() +
									" | Industries: " + account.getIndustry() +
									" | Employee Count: " + account.getEmployeeCount() +
									" | City: " + account.getCity() +
									" | Country: " + account.getCountry() + " }\n"
					);


				}catch (IllegalArgumentException e) {
					System.err.println(e.getMessage());
					navigate();
				}

				contact.setAccount(account);
				opportunity.setAccount(account);
				opportunityRepository.save(opportunity);


				contact.setOpportunity(opportunity);

				contactRepository.save(contact);

				break;
			case CLOSED_WON:
//                Opportunity.closedWon();
				break;
			case CLOSED_LOST:
//                Opportunity.closedLost();
				break;
			case NEWSALESREP:
				salesRep = SalesRep.addSalesRep(input("- Introduce the SalesRep name: "));
				salesRepRepository.save(salesRep);
				break;
			case HELP:
				help();
				break;
			case EXIT:
				System.exit(0);
				break;
			default:
				break;
		}
		navigate();
	}

	public static String input(String input) {
		Scanner myScanner = new Scanner(System.in);
		System.out.println(input);
		String userInput = myScanner.nextLine();
		return userInput.replace(" ","").toUpperCase();
	}

	public void help() throws InterruptedException {
		System.out.println("List of " + (char)27 + "[33m" + "COMMANDS" + (char)27 + "[0m" + " available:");
		System.out.print("{   NEWLEAD   } ");
		System.out.print("{  SHOWLEADS   } ");
		System.out.print("{  CONVERT   } ");
		System.out.print("{ SHOWOPPORTUNITIES } ");
		System.out.println("{  CLOSED_WON   } ");
		System.out.print("{ CLOSED_LOST } ");
		System.out.print("{ SHOWACCOUNTS } ");
		System.out.print("{ LOOKUPLEAD } ");
		System.out.print("{ LOOKUPOPPORTUNITY } ");
		System.out.print("{ LOOKUPACCOUNT } ");
		System.out.println("\n=================");
		navigate();
	}
}
