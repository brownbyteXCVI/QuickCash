package com.example.quickcashg12;
import org.junit.Test;
import static org.junit.Assert.*;

public class PasswordValidatorTest {
    @Test
    public void testInvalidPassword() {
        InputValidator validator = new InputValidator("");
        assertFalse(validator.isPasswordValid());
        InputValidator validator2 = new InputValidator("password123");
        assertFalse(validator2.isPasswordValid());
    }

    @Test
    public void testValidPassword() {
        InputValidator validator = new InputValidator("Password123");
        assertTrue(validator.isPasswordValid());
    }

    @Test
    public void testPasswordLength() {
        InputValidator validator = new InputValidator("Pas12");
        assertFalse(validator.passLength());

        InputValidator validator1 = new InputValidator("Passwor12");
        assertTrue(validator1.passLength());
    }
}
