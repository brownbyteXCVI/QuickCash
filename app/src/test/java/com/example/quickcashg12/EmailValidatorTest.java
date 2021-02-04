package com.example.quickcashg12;

import org.junit.Test;
import static org.junit.Assert.*;

public class EmailValidatorTest {
    @Test
    public void testEmptyEmail() {
        InputValidator validator = new InputValidator("Password123");
        validator.seteMail("");
        assertFalse(validator.checkEmail());
    }
}
