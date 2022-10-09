package com.example.CRM.entities;

import javax.persistence.*;

//@Embeddable

@Entity
public class Contact extends Leads {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account")
    private Account account;

    @OneToOne
    @JoinColumn(name = "Opportunity")
    private Opportunity opportunity;



}
