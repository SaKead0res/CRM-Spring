package com.example.CRM.entities;

import com.example.CRM.enums.Product;
import com.example.CRM.enums.Status;

import javax.persistence.*;

@Entity
public class Opportunity {

    @Id
    @GeneratedValue
    private Long id;

    @Embedded
    private Product product;

    private int quantity;

//    @Embedded
    @OneToOne
    static Contact decisionMaker;

    @Embedded
    private Status status;

    @ManyToOne
    @JoinColumn(name = "account")
    private Account account;
}
