package com.example.CRM;

import com.example.CRM.entities.Contact;
import com.example.CRM.entities.Leads;
import com.example.CRM.repositories.ContactRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class ContactTest {

    @Autowired
    ContactRepository contactRepository;

    @Test
    @DisplayName("Add Contact Works")
    void addContactWorks (){

        Contact contact = Contact.addContact(
                new Leads("TestName", "TestPhone", "TestEmail", "TestCompany", null));

        assertEquals("TestName", contact.getName());
        assertEquals("TestPhone", contact.getPhoneNumber());
        assertEquals("TestEmail", contact.getEmailAddress());
        assertEquals("TestCompany", contact.getCompanyName());

    }

    @Test
    @DisplayName("Add Contact throws IllegalArgumentException")
    void addContactThrowsExceptionWhenIsEmpty() {

        assertThrows(IllegalArgumentException.class, () -> {
            Contact.addContact(new Leads("", "test", "test", "test",null));
        });

        assertThrows(IllegalArgumentException.class, () -> {
            Contact.addContact(new Leads("test", "", "test", "test",null));
        });

        assertThrows(IllegalArgumentException.class, () -> {
            Contact.addContact(new Leads("test", "test", "", "test",null));
        });

        assertThrows(IllegalArgumentException.class, () -> {
            Contact.addContact(new Leads("test", "test", "test", "",null));
        });
    }
}
