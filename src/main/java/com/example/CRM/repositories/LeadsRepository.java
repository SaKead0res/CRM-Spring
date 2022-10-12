package com.example.CRM.repositories;

import com.example.CRM.entities.Leads;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;


@Repository
public interface LeadsRepository extends JpaRepository<Leads, Long> {

    List<Leads> findByName (String name);

//    Leads addLead(String name, String phoneNumber, String email, String companyName);

//    Optional<Leads> getById(Long id);
}
