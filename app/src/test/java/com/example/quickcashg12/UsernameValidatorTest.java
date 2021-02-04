package com.example.quickcashg12;

import org.junit.Test;
import static org.junit.Assert.*;

public class UsernameValidatorTest {
    @Test
    public void testEmptyUsername() {
        InputValidator inputValidator = new InputValidator("Pass123");
        inputValidator.setUserName("");
        assertFalse(inputValidator.checkName());
    }

    @Test
    public void testValidUsername() {
        InputValidator validator = new InputValidator("Pass123");
        validator.setUserName("Username");
        assertTrue(validator.checkName());
    }
}
