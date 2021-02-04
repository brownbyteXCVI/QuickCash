package com.example.quickcashg12;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class JobInputTest {

    private JobInputValidator jobValdiator;

    @Before
    public void init() {
        jobValdiator = new JobInputValidator();
    }

    @Test
    public void invalidJobTitleTest() {
        String jobTitle = "";
        assertFalse(jobValdiator.validateJobTitle(jobTitle));

        jobTitle = "Software_Developer";
        assertFalse(jobValdiator.validateJobTitle(jobTitle));

        jobTitle = "WebDeveloper 101";
        assertFalse(jobValdiator.validateJobTitle(jobTitle));
    }

    @Test
    public void validJobTitleTest() {
        String jobTitle = "Software Developer";
        assertTrue(jobValdiator.validateJobTitle(jobTitle));

        jobTitle = "Android Developer";
        assertTrue(jobValdiator.validateJobTitle(jobTitle));

        jobTitle = "Mason";
        assertTrue(jobValdiator.validateJobTitle(jobTitle));
    }

    @Test
    public void invalidJobLocationTest() {
        String location = "";
        assertFalse(jobValdiator.validateJobLocation(location));

        location = "Nevada_101";
        assertFalse(jobValdiator.validateJobLocation(location));

        location = "Area$51";
        assertFalse(jobValdiator.validateJobLocation(location));
    }

    @Test
    public void validJobLocationTest() {
        String location = "Canada";
        assertTrue(jobValdiator.validateJobLocation(location));

        location = "New Zealand";
        assertTrue(jobValdiator.validateJobLocation(location));

        location = "Area 51";
        assertTrue(jobValdiator.validateJobLocation(location));
    }

    @Test
    public void invalidJobSalaryTest() {
        String salary = "";
        assertFalse(jobValdiator.validateJobSalary(salary));

        salary = "One Hundred Thousand";
        assertFalse(jobValdiator.validateJobSalary(salary));

        salary = "100000a";
        assertFalse(jobValdiator.validateJobSalary(salary));
    }

    @Test
    public void validJobSalaryTest() {
        String salary = "150000";
        assertTrue(jobValdiator.validateJobSalary(salary));
    }

}
