package com.example.CRM.entities;

import com.example.CRM.menu.Navigate;
import com.example.CRM.repositories.LeadsRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

@Entity
public class Leads {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String phoneNumber;
    private String emailAddress;
    private String companyName;

    public Leads(String name, String phoneNumber, String emailAddress, String companyName) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.companyName = companyName;
    }

    public Leads() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Long getId() {
        return id;
    }

    public static Leads addLead(Scanner scanner) {

        Leads lead = new Leads();

        System.out.print("- Introduce a Name: ");
        lead.setName(scanner.nextLine());
        System.out.print("- Introduce a Phone Number: ");
        lead.setPhoneNumber(scanner.nextLine());
        System.out.print("- Introduce an Email: ");
        lead.setEmailAddress(scanner.nextLine());
        System.out.print("- Introduce a Company Name: ");
        lead.setCompanyName(scanner.nextLine());


//        System.out.println("\nThe new " + (char)27 + "[33m" + "LEAD" + (char)27 + "[0m" + " is created correctly.");
//        System.out.println("Lead { Id: " + lead.getId() + " |  Name: " + lead.getName() + " | Phone: " + lead.getPhoneNumber() +
//                " | Email: " + lead.getEmailAddress() + " | Company Name: " + lead.getCompanyName() + " }\n");

        return lead;
    }

    public static void showLeads(LeadsRepository leadsRepository){

        System.out.println("\nLEAD LIST\n===================");

        for (Leads lead : leadsRepository.findAll()){
            System.out.println("Lead { Id: " + lead.getId()
                    + " | Name: " + lead.getName()
                    + " | Phone: " + lead.getPhoneNumber()
                    + " | Email: " + lead.getEmailAddress()
                    + " | Company Name: " + lead.getCompanyName() + " }");

            System.out.println("====================");
        }
        System.out.println("END OF LIST\n");
    }

    public static void lookupLead(LeadsRepository leadsRepository) throws InterruptedException {

        Scanner input = new Scanner(System.in);

        System.out.print("- Introduce the " + (char)27 + "[33m" + "LEAD" + (char)27 + "[0m" + " Id to LOOK: ");

        Long id = null;

        try {
            id = input.nextLong();

        } catch (IllegalArgumentException e){
            System.err.println("Wrong ID format.");
            TimeUnit.MILLISECONDS.sleep(1000);
            lookupLead(leadsRepository);
        }

        System.out.println("Lead" +
                "  \n | Id: " + leadsRepository.findById(id).get().getId() +
                " |\n | Name: " + leadsRepository.findById(id).get().getName() +
                " |\n | Phone: " + leadsRepository.findById(id).get().getPhoneNumber() +
                " |\n | Email: " + leadsRepository.findById(id).get().getEmailAddress() +
                " |\n | Company Name: " + leadsRepository.findById(id).get().getCompanyName() + " |\n");
    }

}
