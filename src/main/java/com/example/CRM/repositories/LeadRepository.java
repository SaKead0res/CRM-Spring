package com.example.CRM.repositories;

import com.example.CRM.entities.Lead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;


@Repository
public interface LeadRepository extends JpaRepository<Lead, Long> {

    List<Lead> findByName (String name);

//    Optional<Leads> getById(Long id);
}
