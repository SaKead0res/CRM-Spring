package com.example.CRM;

import com.example.CRM.entities.Contact;
import com.example.CRM.entities.Leads;
import com.example.CRM.entities.Opportunity;
import com.example.CRM.enums.Product;
import com.example.CRM.enums.Status;
import com.example.CRM.repositories.OpportunityRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class OpportunityTest {

    @Autowired
    OpportunityRepository opportunityRepository;

    @Test
    @DisplayName("Add Opportunity works")
    void addOpportunityWorks () {

        Opportunity opportunity = Opportunity.addOpportunity(
                Product.FLATBED,111, Status.OPEN);

        assertEquals(Product.FLATBED,opportunity.getProduct());
        assertEquals(111,opportunity.getQuantity());
        assertEquals(Status.OPEN,opportunity.getStatus());
    }

    @Test
    @DisplayName("Add Opportunity throws IllegalArgumentException")
    void addOpportunityThrowsExceptionWhenIsEmpty() {

        assertThrows(IllegalArgumentException.class, () -> {
            Opportunity.addOpportunity(null,111, Status.OPEN);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            Opportunity.addOpportunity(Product.HYBRID,-15, Status.OPEN);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            Opportunity.addOpportunity(Product.HYBRID,111, null);
        });
    }
}
