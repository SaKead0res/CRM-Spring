package com.example.CRM;

import com.example.CRM.entities.Leads;
import com.example.CRM.repositories.LeadsRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;


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

    @AfterEach
    void tearDown() {

        leadsRepository.deleteAll();
    }

    @Test
    @DisplayName("Find Lead by Name")
    void findLeadByNameWorks () {

        List<Leads> leads = leadsRepository.findByName("Julia Roberts");

        Assertions.assertTrue(!leads.isEmpty());
//
        assertEquals("Julia Roberts", leads.get(0).getName());
//        assertEquals("1", leadsOptional.get().getId());
    }

    @Test
    @DisplayName("Add Lead Works")
    void addLeadWorks (){

        Leads test1 = Leads.addLead("TestName", "TestPhone", "TestEmail", "TestCompany");

//        assertEquals("TestName", test1.getName());
    }

//        //TODO  AQUI VA EL EJEMPLO DE MOCKITO
//
//        Scanner scanner = new Scanner(System.in);
//
//        when(scanner.nextLine())
//                .thenReturn("Julia Roberts", "+34 56436546", "julia.r@hotmail.com", "Movistar");
////
////        phoneBookService.register(momContactName, momPhoneNumber);
////
////        verify(phoneBookRepository)
////                .insert(momContactName, momPhoneNumber);
//
//
//        Leads.addLead(scanner);
//
//        assertTrue(leadsRepository.findById(1L).isPresent());
//
////        Optional<Leads> leadsOptional = leadsRepository.findByName("Julia Roberts");
////
////        Assertions.assertTrue(leadsOptional.isPresent());
////
////        assertEquals("Julia Roberts", leadsOptional.get().getName());
////        assertEquals("1", leadsOptional.get().getId());
//    }



}
