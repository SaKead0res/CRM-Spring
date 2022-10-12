package com.example.CRM.entities;

import com.example.CRM.enums.Industries;
import com.example.CRM.repositories.AccountRepository;
import com.example.CRM.repositories.OpportunityRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.cache.annotation.SpringCacheAnnotationParser;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated
    private Industries industry;

    private int employeeCount;
    private String city;
    private String country;

    @OneToMany(mappedBy = "id")
    List<Contact> accountContactList;

    @OneToMany(mappedBy = "id")
    List<Opportunity> accountOpportunityList;

    public Account(Industries industry, int employeeCount, String city, String country,
                   List<Contact> accountContactList, List<Opportunity> accountOpportunityList) {
        this.industry = industry;
        this.employeeCount = employeeCount;
        this.city = city;
        this.country = country;
        this.accountContactList = accountContactList;
        this.accountOpportunityList = accountOpportunityList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Industries getIndustry() {
        return industry;
    }

    public void setIndustry(Industries industry) {
        this.industry = industry;
    }

    public int getEmployeeCount() {
        return employeeCount;
    }

    public void setEmployeeCount(int employeeCount) {
        this.employeeCount = employeeCount;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<Contact> getAccountContactList() {
        return accountContactList;
    }

    public void setAccountContactList(List<Contact> accountContactList) {
        this.accountContactList = accountContactList;
    }

    public List<Opportunity> getAccountOpportunityList() {
        return accountOpportunityList;
    }

    public void setAccountOpportunityList(List<Opportunity> accountOpportunityList) {
        this.accountOpportunityList = accountOpportunityList;
    }


//    public static void showAccounts(AccountRepository accountRepository) {
//
//        System.out.println("\nACCOUNT LIST\n===================");
//
//        for (Account account : AccountRepository.findAll()) {
//            System.out.println("Account { Id: " + account.getId()
//                    + " | Name: " + account.getIndustry()
//                    + " | Phone: " + account.get()
//                    + " | Email: " + account.getStatus()
//                    + " | Company Name: " + account.getAccount() + " }");
//
//            System.out.println("====================");
//        }
//        System.out.println("END OF LIST\n");
//    }

//    public static void lookupOpportunity(OpportunityRepository opportunityRepository) throws InterruptedException {
//
//        Scanner input = new Scanner(System.in);
//
//        System.out.print("- Introduce the " + (char)27 + "[33m" + "OPPORTUNITY" + (char)27 + "[0m" + " Id to LOOK: ");
//
//        Long id = null;
//
//        try {
//            id = input.nextLong();
//
//        } catch (IllegalArgumentException e){
//            System.err.println("Wrong ID format.");
//            TimeUnit.MILLISECONDS.sleep(1000);
//            lookupOpportunity(opportunityRepository);
//        }
//
//        System.out.println("Opportunity" +
//                "  \n | Id: " + opportunityRepository.findById(id).get().getId() +
//                " |\n | Interested Product: " + opportunityRepository.findById(id).get().getProduct() +
//                " |\n | Interested Quantity: " + opportunityRepository.findById(id).get().getQuantity() +
//                " |\n | Status: " + opportunityRepository.findById(id).get().getStatus() +
//                " |\n | Related Account: " + opportunityRepository.findById(id).get().getAccount() + " |\n");
//    }

}
