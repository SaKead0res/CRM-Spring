package com.example.CRM;

import com.example.CRM.entities.Account;
import com.example.CRM.entities.Contact;
import com.example.CRM.entities.Leads;
import com.example.CRM.entities.Opportunity;
import com.example.CRM.enums.Industries;
import com.example.CRM.enums.Product;
import com.example.CRM.enums.Status;
import com.example.CRM.repositories.AccountRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class AccountTest {

    @Autowired
    AccountRepository accountRepository;

    @Test
    @DisplayName("Add Account Works")
    void addAccountWorks () {

        Account account = Account.addAccount(
                Industries.MANUFACTURING, 777, "City", "Country");

        assertEquals(Industries.MANUFACTURING, account.getIndustry());
        assertEquals(777, account.getEmployeeCount());
        assertEquals("City", account.getCity());
        assertEquals("Country", account.getCountry());
    }

    @Test
    @DisplayName("Add Account throws IllegalArgumentException")
    void addAccountThrowsExceptionWhenIsEmpty() {

        assertThrows(IllegalArgumentException.class, () -> {
            Account.addAccount(
                    null, 777, "City", "Country");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            Account.addAccount(
                    Industries.MANUFACTURING, -10, "City", "Country");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            Account.addAccount(
                    Industries.MANUFACTURING, 777, "", "Country");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            Account.addAccount(
                    Industries.MANUFACTURING, 777, "City", "");
        });
    }

}
