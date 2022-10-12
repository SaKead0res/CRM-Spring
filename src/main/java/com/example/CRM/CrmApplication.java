package com.example.CRM;

import com.example.CRM.entities.Leads;
import com.example.CRM.entities.Opportunity;
import com.example.CRM.enums.Commands;
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
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

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
//		Navigate.navigate(leadsRepository);

		navigate();



	}

	public void navigate() throws InterruptedException {



		System.out.print("{  LEADS : " + leadsRepository.count() + "    } ");
		System.out.print("{ OPPORTUNITIES : " + /*Opportunity.opportunityList.size() + */" } ");
		System.out.println("{  ACCOUNTS : " + /*Account.accountList.size() + */"   } ");

		System.out.print("Please, enter a command: ");

		Commands command = null;
		try {
			command = Commands.valueOf( input() );//esta funcion intenta dar a la variable command el valor de una String(input())
//                                                  pero como es de clase Enum solo cogera el valor en caso de que exista en la
//                                                  clase Enum Commands.
		} catch ( IllegalArgumentException e ) {
			System.err.println( "This command doesn't exists. Try again or type <help>." );
			TimeUnit.MILLISECONDS.sleep(1000);
			navigate();
		}

		switch (command) {
			case NEWLEAD:
				leadsRepository.save(Leads.addLead(new Scanner(System.in)));
				break;
			case SHOWLEADS:
				Leads.showLeads(leadsRepository);
				break;
			case SHOWOPPORTUNITIES:
//                Opportunity.showOpportunities();
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
//                Opportunity.lookupOpportunity();
				break;
			case CONVERT:
//                Leads.convert();
				break;
			case CLOSED_WON:
//                Opportunity.closedWon();
				break;
			case CLOSED_LOST:
//                Opportunity.closedLost();
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

	public static String input() {
		Scanner myScanner = new Scanner(System.in);

		String userInput = myScanner.nextLine();
//
////        PROBANDO LOOKUPLEADID
//
//        List<String> inputArray = new ArrayList<>(List.of(userInput.trim().split(" ")));
//        String inputFirstPart = inputArray.get(0) + inputArray.get(1);
//        int id;
//        System.out.println(inputFirstPart);
////        if (Integer.parseInt(inputArray.get(inputArray.size() - 1)))
//        try{
//            id = Integer.parseInt(inputArray.get(inputArray.size() - 1));
//        } catch (IllegalArgumentException e){
//            System.err.println("Wrong ID format. Try again.");
//            input();
//        }
//        String upperCaseInput = userInput.toUpperCase().replace(" ", "");
//        return inputFirstPart.toUpperCase();
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
