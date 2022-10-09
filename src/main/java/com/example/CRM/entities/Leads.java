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

    public static Leads addLead() throws InterruptedException {

        Leads lead = new Leads();
//        LeadsRepository leadsRepository = null;
        System.out.print("- Introduce a Name: ");
        lead.setName(Navigate.input());
        System.out.print("- Introduce a Phone Number: ");
        lead.setPhoneNumber(Navigate.input());
        System.out.print("- Introduce an Email: ");
        lead.setEmailAddress(Navigate.input());
        System.out.print("- Introduce a Company Name: ");
        lead.setCompanyName(Navigate.input());

//        leadsRepository.save(lead);
//        Leads.addLead(lead);
        System.out.println("\nThe new " + (char)27 + "[33m" + "LEAD" + (char)27 + "[0m" + " is created correctly.");
        System.out.println("Lead { Id: " + lead.getId() + " | Name: " + lead.getName() + " | Phone: " + lead.getPhoneNumber() +
                " | Email: " + lead.getEmailAddress() + " | Company Name: " + lead.getCompanyName() + " }\n");
//        navigate();
        return lead;
    }

    public static void showLeads(LeadsRepository leadsRepository){
        System.out.println("\nLEAD LIST\n===================");
        for (Leads lead : leadsRepository.findAll()){
            System.out.println("Lead { " + lead.getId() + " | Name: " + lead.getName() + " | Phone: " + lead.getPhoneNumber() +
                    " | Email: " + lead.getEmailAddress() + " | Company Name: " + lead.getCompanyName() + " }");
            System.out.println("====================");
        }
        System.out.println("END OF LIST\n");
    }

}
