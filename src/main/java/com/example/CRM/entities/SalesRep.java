package com.example.CRM.entities;

import com.example.CRM.repositories.LeadsRepository;
import com.example.CRM.repositories.SalesRepRepository;

import javax.persistence.*;
import java.util.List;

@Entity
public class SalesRep {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "id")
    List<Leads> leadsList;

    public SalesRep(String name) {
        this.name = name;
    }

    public SalesRep() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static SalesRep addSalesRep(String name) {


        SalesRep salesRep = new SalesRep();

        if (name.isBlank()) throw new IllegalArgumentException("The Sales Responsible name can't be an empty field.");
        salesRep.setName(name);

        return salesRep;
    }

    public static void showSalesReps(SalesRepRepository salesRepRepository){

        System.out.println("\nLEAD LIST\n===================");

        for (SalesRep salesRep : salesRepRepository.findAll()){
//            System.out.println("Lead { Id: " + salesRep.getId()
//                    + " | Name: " + salesRep.getName()
//                    + " | Phone: " + salesRep.getPhoneNumber()
//                    + " | Email: " + salesRep.getEmailAddress()
//                    + " | Company Name: " + leads.getCompanyName() + " }");

            System.out.println("====================");
        }
        System.out.println("END OF LIST\n");
    }




}
