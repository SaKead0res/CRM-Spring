package com.example.CRM;

import com.example.CRM.entities.Leads;
import com.example.CRM.repositories.LeadsRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class LeadsTest {

    @Autowired
    LeadsRepository leadsRepository;

//    List<Leads> leadsList;

    @BeforeEach
    void setUp() {

        leadsRepository.saveAll(List.of(
                new Leads("Julia Roberts", "+34 56436546", "julia.r@hotmail.com", "Movistar"),
                new Leads("George Clooney", "+41 78658554", "clooney.fckr@gmail.es", "Orange"),
                new Leads("Susan Sarandon", "+38 97781234", "susan.sar@yahoo.net", "PepePhone")
        ));

    }

    @Test
    @DisplayName("Find Lead by Id")
    void findLeadByNameWorks () {

        Optional<Leads> leadsOptional = leadsRepository.findByName("Julia Roberts");

        Assertions.assertTrue(leadsOptional.isPresent());

        assertEquals("Julia Roberts", leadsOptional.get().getName());
        assertEquals("1", leadsOptional.get().getId());
    }

}
