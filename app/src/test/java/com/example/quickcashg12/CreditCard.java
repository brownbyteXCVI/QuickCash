package com.example.quickcashg12;

import org.junit.Test;

import static org.junit.Assert.*;

public class CreditCard {

    @Test
    public void testCardLength() {
        InputValidator test1 = new InputValidator();
        test1.setCreditCardNum("8736714836718256");
        assertTrue(test1.creditcardLength());

        InputValidator test2 = new InputValidator();
        test2.setCreditCardNum("457828987863");
        assertFalse(test2.creditcardLength());
    }

    @Test
    public void testPinLength() {
        InputValidator test1 = new InputValidator();
        test1.setPin("2815");
        assertTrue(test1.PinLength());

        InputValidator test2 = new InputValidator();
        test2.setPin("28");
        assertFalse(test2.PinLength());

    }

    @Test
    public void testSecurityCodeLength() {
        InputValidator test1 = new InputValidator();
        test1.setSecurityCode("287");
        assertTrue(test1.securityCodeLength());

        InputValidator test2 = new InputValidator();
        test2.setSecurityCode("64623");
        assertFalse(test2.securityCodeLength());

    }

    @Test
    public void testIsDigit() {
        InputValidator test1 = new InputValidator("457828987863");
        assertTrue(test1.isDigit(test1.getPassword()));


        InputValidator test2 = new InputValidator("a58354654");
        assertFalse(test2.isDigit(test2.getPassword()));

    }
}