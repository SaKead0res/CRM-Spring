package com.example.CRM.entities;

import com.example.CRM.repositories.LeadRepository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

@Entity
public class Lead {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String phoneNumber;
    private String emailAddress;
    private String companyName;

    public Lead(String name, String phoneNumber, String emailAddress, String companyName) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.companyName = companyName;
    }

    public Lead() {
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

    public static Lead addLead(String name, String phoneNumber, String email, String companyName) {

        Lead lead = new Lead();
    if (name.isBlank()) throw new IllegalArgumentException("The Lead name can't be an empty field.");
//        if (phoneNumber.isBlank()) throw new IllegalArgumentException("The Lead phone number can't be an empty field.");
//        System.out.print("- Introduce a Name: ");
        lead.setName(name);
//        System.out.print("- Introduce a Phone Number: ");

        lead.setPhoneNumber(phoneNumber);
//        System.out.print("- Introduce an Email: ");
        lead.setEmailAddress(email);
//        System.out.print("- Introduce a Company Name: ");
        lead.setCompanyName(companyName);


//        System.out.println("\nThe new " + (char)27 + "[33m" + "LEAD" + (char)27 + "[0m" + " is created correctly.");
//        System.out.println("Lead { Id: " + lead.getId() + " |  Name: " + lead.getName() + " | Phone: " + lead.getPhoneNumber() +
//                " | Email: " + lead.getEmailAddress() + " | Company Name: " + lead.getCompanyName() + " }\n");

        return lead;
    }

    public static void showLeads(LeadRepository leadRepository){

        System.out.println("\nLEAD LIST\n===================");

        for (Lead lead : leadRepository.findAll()){
            System.out.println("Lead { Id: " + lead.getId()
                    + " | Name: " + lead.getName()
                    + " | Phone: " + lead.getPhoneNumber()
                    + " | Email: " + lead.getEmailAddress()
                    + " | Company Name: " + lead.getCompanyName() + " }");

            System.out.println("====================");
        }
        System.out.println("END OF LIST\n");
    }

    public static void lookupLead(LeadRepository leadRepository) throws InterruptedException {

        Scanner input = new Scanner(System.in);

        System.out.print("- Introduce the " + (char)27 + "[33m" + "LEAD" + (char)27 + "[0m" + " Id to LOOK: ");

        Long id = null;

        try {
            id = input.nextLong();

        } catch (IllegalArgumentException e){
            System.err.println("Wrong ID format.");
            TimeUnit.MILLISECONDS.sleep(1000);
            lookupLead(leadRepository);
        }

        System.out.println("Lead" +
                "  \n | Id: " + leadRepository.findById(id).get().getId() +
                " |\n | Name: " + leadRepository.findById(id).get().getName() +
                " |\n | Phone: " + leadRepository.findById(id).get().getPhoneNumber() +
                " |\n | Email: " + leadRepository.findById(id).get().getEmailAddress() +
                " |\n | Company Name: " + leadRepository.findById(id).get().getCompanyName() + " |\n");
    }

}
