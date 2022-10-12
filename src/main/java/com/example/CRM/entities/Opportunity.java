package com.example.CRM.entities;

import com.example.CRM.enums.Product;
import com.example.CRM.enums.Status;
import com.example.CRM.repositories.LeadsRepository;
import com.example.CRM.repositories.OpportunityRepository;

import javax.persistence.*;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

@Entity
public class Opportunity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //    @Embedded
    @Enumerated(EnumType.STRING)
    private Product product;

    private int quantity;

    //    @Embedded
    @OneToOne
    @JoinColumn(name = "id")
    static Contact decisionMaker;

    //    @Embedded
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "account")
    private Account account;

    public Opportunity(Product product, int quantity, Status status, Account account) {
        this.product = product;
        this.quantity = quantity;
        this.status = status;
        this.account = account;
    }

    public Opportunity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public static Contact getDecisionMaker() {
        return decisionMaker;
    }

    public static void setDecisionMaker(Contact decisionMaker) {
        Opportunity.decisionMaker = decisionMaker;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public static Opportunity addOpportunity(Product product, int quantity, Status status) {


        Opportunity opportunity = new Opportunity();
//        if (product.()) throw new IllegalArgumentException("The Lead name can't be an empty field.");
//        if (phoneNumber.isBlank()) throw new IllegalArgumentException("The Lead phone number can't be an empty field.");
        opportunity.setProduct(product);
        opportunity.setQuantity(quantity);
        opportunity.setStatus(status);
//        opportunity.


//        System.out.println("\nThe new " + (char)27 + "[33m" + "OPPORTUNITY" + (char)27 + "[0m" + " is created correctly.");
//        System.out.println("Opportunity {" + /*ID: " + leads.getId() + */" | Name: " + leads.getName() + " | Phone: " + leads.getPhoneNumber() +
//                " | Email: " + leads.getEmailAddress() + " | Company Name: " + leads.getCompanyName() + " }\n");

        return opportunity;
    }
    public static void showOpportunities(OpportunityRepository OpportunityRepository) {

        System.out.println("\nOPPORTUNITY LIST\n===================");

        for (Opportunity opportunity : OpportunityRepository.findAll()) {
            System.out.println("Lead { Id: " + opportunity.getId()
                    + " | Name: " + opportunity.getProduct()
                    + " | Phone: " + opportunity.getQuantity()
                    + " | Email: " + opportunity.getStatus()
                    + " | Company Name: " + opportunity.getAccount() + " }");

            System.out.println("====================");
        }
        System.out.println("END OF LIST\n");
    }

    public static void lookupOpportunity(OpportunityRepository opportunityRepository) throws InterruptedException {

        Scanner input = new Scanner(System.in);

        System.out.print("- Introduce the " + (char)27 + "[33m" + "OPPORTUNITY" + (char)27 + "[0m" + " Id to LOOK: ");

        Long id = null;

        try {
            id = input.nextLong();

        } catch (IllegalArgumentException e){
            System.err.println("Wrong ID format.");
            TimeUnit.MILLISECONDS.sleep(1000);
            lookupOpportunity(opportunityRepository);
        }

        System.out.println("Opportunity" +
                "  \n | Id: " + opportunityRepository.findById(id).get().getId() +
                " |\n | Interested Product: " + opportunityRepository.findById(id).get().getProduct() +
                " |\n | Interested Quantity: " + opportunityRepository.findById(id).get().getQuantity() +
                " |\n | Status: " + opportunityRepository.findById(id).get().getStatus() +
                " |\n | Related Account: " + opportunityRepository.findById(id).get().getAccount() + " |\n");
    }

    public static void closeWonOpportunity(OpportunityRepository opportunityRepository) throws InterruptedException {

        Scanner input = new Scanner(System.in);

        System.out.print("- Introduce the " + (char)27 + "[33m" + "OPPORTUNITY" + (char)27 + "[0m" + " Id to CLOSE WON: ");

        Long id = null;

        try {
            id = input.nextLong();

        } catch (IllegalArgumentException e){
            System.err.println("Wrong ID format.");
            TimeUnit.MILLISECONDS.sleep(1000);
            closeWonOpportunity(opportunityRepository);
        }

        opportunityRepository.findById(id).get().setStatus(Status.CLOSED_WON);
        System.out.println("Opportunity" +
                "  \n | Id: " + opportunityRepository.findById(id).get().getId() +
                " |\n | Interested Product: " + opportunityRepository.findById(id).get().getProduct() +
                " |\n | Interested Quantity: " + opportunityRepository.findById(id).get().getQuantity() +
                " |\n | Status: " + opportunityRepository.findById(id).get().getStatus() +
                " |\n | Related Account: " + opportunityRepository.findById(id).get().getAccount() + " |\n");
    }

    public static void closeLostOpportunity(OpportunityRepository opportunityRepository) throws InterruptedException {

        Scanner input = new Scanner(System.in);

        System.out.print("- Introduce the " + (char)27 + "[33m" + "OPPORTUNITY" + (char)27 + "[0m" + " Id to CLOSE LOST: ");

        Long id = null;

        try {
            id = input.nextLong();

        } catch (IllegalArgumentException e){
            System.err.println("Wrong ID format.");
            TimeUnit.MILLISECONDS.sleep(1000);
            closeLostOpportunity(opportunityRepository);
        }

        opportunityRepository.findById(id).get().setStatus(Status.CLOSED_LOST);
        System.out.println("Opportunity" +
                "  \n | Id: " + opportunityRepository.findById(id).get().getId() +
                " |\n | Interested Product: " + opportunityRepository.findById(id).get().getProduct() +
                " |\n | Interested Quantity: " + opportunityRepository.findById(id).get().getQuantity() +
                " |\n | Status: " + opportunityRepository.findById(id).get().getStatus() +
                " |\n | Related Account: " + opportunityRepository.findById(id).get().getAccount() + " |\n");
    }

}
