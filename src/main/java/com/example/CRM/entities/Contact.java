package com.example.CRM.entities;

import javax.persistence.*;

//@Embeddable

@Embeddable
@Entity
public class Contact extends Leads {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



}
