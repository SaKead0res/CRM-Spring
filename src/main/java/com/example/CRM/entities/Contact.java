package com.example.CRM.entities;

import javax.persistence.*;

//@Embeddable

@Entity
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String phoneNumber;
    private String emailAddress;
    private String companyName;


    @ManyToOne
    @JoinColumn(name = "account")
    private Account account;

    @OneToOne
    @JoinColumn(name = "Opportunity")
    private Opportunity opportunity;

    public Contact(String name, String phoneNumber, String emailAddress, String companyName, Account account, Opportunity opportunity) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.companyName = companyName;
        this.account = account;
        this.opportunity = opportunity;
    }

    public Contact() {
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

    public void setId(Long id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Opportunity getOpportunity() {
        return opportunity;
    }

    public void setOpportunity(Opportunity opportunity) {
        this.opportunity = opportunity;
    }

    public static Contact addContact(Leads lead) {

        Contact contact = new Contact();

        if (lead.getName().isEmpty()) throw new IllegalArgumentException("The Contact name can't be an empty field.");
       contact.setName(lead.getName());

        if (lead.getPhoneNumber().isEmpty()) throw new IllegalArgumentException("The Contact phone number can't be an empty field.");
        contact.setPhoneNumber(lead.getPhoneNumber());

        if (lead.getEmailAddress().isEmpty()) throw new IllegalArgumentException("The Contact email adress can't be an empty field.");
        contact.setEmailAddress(lead.getEmailAddress());

        if (lead.getCompanyName().isEmpty()) throw new IllegalArgumentException("The Contact company name can't be an empty field.");
        contact.setCompanyName(lead.getCompanyName());


        contact.setName(lead.getName());
        contact.setPhoneNumber(lead.getPhoneNumber());
        contact.setEmailAddress(lead.getEmailAddress());
        contact.setCompanyName(lead.getCompanyName());


        return contact;
    }
}
