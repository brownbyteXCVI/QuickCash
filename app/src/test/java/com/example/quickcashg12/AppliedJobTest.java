package com.example.quickcashg12;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AppliedJobTest {

    private InputValidator inputValidator;

    @Before
    public void init() {
        inputValidator = new InputValidator();
    }

    @Test
    public void invalidEmployeeEmailTest() {
        String email = "";
        inputValidator.seteMail(email);
        assertFalse(inputValidator.checkEmail());

        email = "test@gmail";
        inputValidator.seteMail(email);
        assertFalse(inputValidator.checkEmail());
    }

    @Test
    public void validEmployeeEmailTest() {
        String email = "test@gmail.com";
        inputValidator.seteMail(email);
        assertTrue(inputValidator.checkEmail());

        email = "test123@gmail.co.us";
        inputValidator.seteMail(email);
        assertTrue(inputValidator.checkEmail());

        email = "test007@test.com";
        inputValidator.seteMail(email);
        assertTrue(inputValidator.checkEmail());
    }

    @Test
    public void validEmployeeNameTest() {
        String name = "Rayyan";
        inputValidator.setUserName(name);
        assertTrue(inputValidator.checkName());

        name = "Rayyan Rafan";
        inputValidator.setUserName(name);
        assertTrue(inputValidator.checkName());
    }

    @Test
    public void emptyEmployeeNameTest() {
        String name = "";
        inputValidator.setUserName(name);
        assertFalse(inputValidator.checkName());
    }

    @Test
    public void emptyEmployeeAddressTest() {
        String address = "";
        inputValidator.setAddress(address);
        assertFalse(inputValidator.checkAddress());
    }

    @Test
    public void validEmployeeAddressTest() {
        String address = "The Cupboard Under the Stairs, 4, Private Drive, Little Winging, Surrey";
        inputValidator.setAddress(address);
        assertTrue(inputValidator.checkAddress());
    }

    @Test
    public void emptyEmployeeDescriptionTest() {
        String description = "";
        inputValidator.setDescription(description);
        assertFalse(inputValidator.checkDescription());
    }

    @Test
    public void validEmployeeDescriptionTest() {
        String description = "This is a test description for the job";
        inputValidator.setDescription(description);
        assertTrue(inputValidator.checkDescription());
    }
}
