package com.example.CRM.entities;

import com.example.CRM.enums.Product;
import com.example.CRM.enums.Status;

import javax.persistence.*;

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
}
