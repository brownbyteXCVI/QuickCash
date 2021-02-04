package com.example.quickcashg12;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class locationPreferenceTest {
    @Test
    public void locationPreferenceTest() {
        User user1 = new User();
        User user2 = new User();
        User user3 = new User();
        user1.setPreferedLocation("Halifax");
        user2.setPreferedLocation("");
        user3.setPreferedLocation("Dartmouth");
        assertEquals("Halifax", user1.getPreferedLocation());
        assertEquals("", user2.getPreferedLocation());
        assertEquals("Dartmouth", user3.getPreferedLocation());
    }

}