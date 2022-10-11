package com.example.CRM.entities;

import com.example.CRM.enums.Industries;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.cache.annotation.SpringCacheAnnotationParser;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Account {

    @Id
    @GeneratedValue()
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

}
