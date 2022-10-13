package com.example.CRM.entities;

import com.example.CRM.enums.Industries;
import com.example.CRM.repositories.AccountRepository;
import javax.persistence.*;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Industries industry;

    private int employeeCount;
    private String city;
    private String country;

    @OneToMany(mappedBy = "id")
    List<Contact> accountContactList;

    @OneToMany(mappedBy = "id")
    List<Opportunity> accountOpportunityList;

    public Account(Industries industry, int employeeCount, String city, String country) {
        this.industry = industry;
        this.employeeCount = employeeCount;
        this.city = city;
        this.country = country;
    }

    public Account() {

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

    public static Account addAccount(Industries industry, int employeeCount, String city, String country) {


        Account account = new Account();
//        if (product.()) throw new IllegalArgumentException("The Lead name can't be an empty field.");
//        if (phoneNumber.isBlank()) throw new IllegalArgumentException("The Lead phone number can't be an empty field.");
        account.setIndustry(industry);
        account.setEmployeeCount(employeeCount);
        account.setCity(city);
        account.setCountry(country);

        return account;
    }

    public static void showAccounts(AccountRepository accountRepository) {

        System.out.println("\nACCOUNT LIST\n===================");

        for (Account account : accountRepository.findAll()) {
            System.out.println("Account { Id: " + account.getId()
                    + " | Industry: " + account.getIndustry()
                    + " | Nº Employees: " + account.getEmployeeCount()
                    + " | City: " + account.getCity()
                    + " | Country: " + account.getCountry() + " }");

            System.out.println("====================");
        }
        System.out.println("END OF LIST\n");
    }

    public static void lookupAccount(AccountRepository accountRepository) throws InterruptedException {

        Scanner input = new Scanner(System.in);

        System.out.print("- Introduce the " + (char) 27 + "[33m" + "ACCOUNT" + (char) 27 + "[0m" + " Id to LOOK: ");

        Long id = null;

        try {
            id = input.nextLong();

        } catch (IllegalArgumentException e) {
            System.err.println("Wrong ID format.");
            TimeUnit.MILLISECONDS.sleep(1000);
            lookupAccount(accountRepository);
        }

        System.out.println("Account" +
                "  \n | Id: " + accountRepository.findById(id).get().getId() +
                " |\n | Type of Industry: " + accountRepository.findById(id).get().getIndustry() +
                " |\n | Nº Employees: " + accountRepository.findById(id).get().getEmployeeCount() +
                " |\n | City: " + accountRepository.findById(id).get().getCity() +
                " |\n | Country: " + accountRepository.findById(id).get().getCountry() +
                " |\n");
//        System.out.println(" |\n | Contacts of the Account: ");
//        for (Contact contact : accountRepository.findById(id).get().accountContactList) {
//            System.out.println(
//                    "Contact {ID: " + contact.getId() +
//                            " | Name: " + contact.getName() +
//                            " | Phone: " + contact.getPhoneNumber() +
//                            " | Email: " + contact.getEmailAddress() +
//                            " | Company Name: " + contact.getCompanyName() + " }\n");
//        }
//        System.out.println(" |\n | Opportunities of the Account: ");
//        for (Opportunity opportunity : accountRepository.findById(id).get().accountOpportunityList) {
//            System.out.println(
//                    "Opportunity {ID: " + opportunity.getId() +
//                            " | Product: " + opportunity.getProduct() +
//                            " | Interested Quantity: " + opportunity.getQuantity() +
//                            " | Status: " + opportunity.getStatus() +
//                            " }\n");
//        }
    }
}
