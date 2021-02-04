package com.example.quickcashg12;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;

public class JobFilterTest {
    private JobFilterProcessor filterProcessor;
    private ArrayList<Job> jobs;

    @Before
    public void init() {
        jobs = new ArrayList<>();

        jobs.add(new Job("Software Developer", "Canada", 80000, JobType.IT));
        Job currentJob = jobs.get(0);
        currentJob.setCategory("Junior Developer");
        currentJob.setLanguageRequired("English");
        currentJob.setTerm("Full-Time");

        jobs.add(new Job("Web Developer", "Canada", 60000, JobType.IT));
        currentJob = jobs.get(1);
        currentJob.setCategory("Senior Developer");
        currentJob.setLanguageRequired("English");
        currentJob.setTerm("Full-Time");

        jobs.add(new Job("Electronics Engineer", "India", 65000, JobType.ENGINEERING));
        currentJob = jobs.get(2);
        currentJob.setCategory("Senior Engineer");
        currentJob.setLanguageRequired("Hindi");
        currentJob.setTerm("Full-Time");

        jobs.add(new Job("Mechanical Engineer", "Taiwan", 50000, JobType.ENGINEERING));
        currentJob = jobs.get(3);
        currentJob.setCategory("Power Plant Engineer");
        currentJob.setLanguageRequired("Chinese");
        currentJob.setTerm("Contractual");

        jobs.add(new Job("Lawn Mower", "USA", 30000, JobType.MAINTENANCE));
        currentJob = jobs.get(4);
        currentJob.setCategory("Specialist");
        currentJob.setLanguageRequired("English");
        currentJob.setTerm("Part-Time");

        jobs.add(new Job("Research Associate Applied Physics", "USA", 40000, JobType.SCIENCE));
        currentJob = jobs.get(5);
        currentJob.setCategory("Research Associate");
        currentJob.setLanguageRequired("English");
        currentJob.setTerm("Contractual");

        jobs.add(new Job("Branch Manager", "Brazil", 50000, JobType.FINANCE));
        currentJob = jobs.get(6);
        currentJob.setCategory("Manager");
        currentJob.setLanguageRequired("Portuguese");
        currentJob.setTerm("Full-Time");

        jobs.add(new Job("Hotel Manager", "England", 35000, JobType.HOSPITALITY));
        currentJob = jobs.get(7);
        currentJob.setCategory("Manager");
        currentJob.setLanguageRequired("English");
        currentJob.setTerm("Full-Time");

        jobs.add(new Job("Chef", "Ireland", 40000, JobType.HOSPITALITY));
        currentJob = jobs.get(8);
        currentJob.setCategory("Head Chef");
        currentJob.setLanguageRequired("Irish");
        currentJob.setTerm("Full-Time");

        jobs.add(new Job("Daytime Nurse", "Australia", 20000, JobType.HEALTHCARE));
        currentJob = jobs.get(9);
        currentJob.setCategory("Head Nurse");
        currentJob.setLanguageRequired("English");
        currentJob.setTerm("Part-Time");

        jobs.add(new Job("Orthopaedist", "Spain", 75000, JobType.HEALTHCARE));
        currentJob = jobs.get(10);
        currentJob.setCategory("Specialist");
        currentJob.setLanguageRequired("Spanish");
        currentJob.setTerm("Contractual");

        jobs.add(new Job("Data Analyst", "India", 65000, JobType.IT));
        currentJob = jobs.get(11);
        currentJob.setCategory("Data Scientist");
        currentJob.setLanguageRequired("English");
        currentJob.setTerm("Full-Time");

        filterProcessor = new JobFilterProcessor(jobs);
    }

    @Test
    public void titleFilterTest() {
        ArrayList<Job> filteredList = filterProcessor.filterBasedOnTitle("Developer");
        ArrayList<Job> expectedList = new ArrayList<>();
        expectedList.add(new Job("Software Developer", "Canada", 80000, JobType.IT));
        Job currentJob = expectedList.get(0);
        currentJob.setCategory("Junior Developer");
        currentJob.setLanguageRequired("English");
        currentJob.setTerm("Full-Time");
        expectedList.add(new Job("Web Developer", "Canada", 60000, JobType.IT));
        currentJob = expectedList.get(1);
        currentJob.setCategory("Senior Developer");
        currentJob.setLanguageRequired("English");
        currentJob.setTerm("Full-Time");

        // this is to make sure that original list was not returned by the filter method
        assertNotEquals(filteredList, jobs);
        assertEquals(expectedList.toString(), filteredList.toString());

        filteredList = filterProcessor.filterBasedOnTitle("Manager");
        expectedList = new ArrayList<>();
        expectedList.add(new Job("Branch Manager", "Brazil", 50000, JobType.FINANCE));
        currentJob = expectedList.get(0);
        currentJob.setCategory("Manager");
        currentJob.setLanguageRequired("Portuguese");
        currentJob.setTerm("Full-Time");
        expectedList.add(new Job("Hotel Manager", "England", 35000, JobType.HOSPITALITY));
        currentJob = expectedList.get(1);
        currentJob.setCategory("Manager");
        currentJob.setLanguageRequired("English");
        currentJob.setTerm("Full-Time");

        assertEquals(expectedList.toString(), filteredList.toString());

        filteredList = filterProcessor.filterBasedOnTitle("Artist");
        expectedList = new ArrayList<>();
        assertEquals(expectedList.toString(), filteredList.toString());
    }

    @Test
    public void locationFilterTest() {
        ArrayList<Job> filteredList = filterProcessor.filterBasedOnLocation("Canada");
        ArrayList<Job> expectedList = new ArrayList<>();
        expectedList.add(new Job("Software Developer", "Canada", 80000, JobType.IT));
        Job currentJob = expectedList.get(0);
        currentJob.setCategory("Junior Developer");
        currentJob.setLanguageRequired("English");
        currentJob.setTerm("Full-Time");
        expectedList.add(new Job("Web Developer", "Canada", 60000, JobType.IT));
        currentJob = expectedList.get(1);
        currentJob.setCategory("Senior Developer");
        currentJob.setLanguageRequired("English");
        currentJob.setTerm("Full-Time");

        // this is to make sure that original list was not returned by the filter method
        assertNotEquals(filteredList, jobs);
        assertEquals(expectedList.toString(), filteredList.toString());

        filteredList = filterProcessor.filterBasedOnLocation("India");
        expectedList = new ArrayList<>();
        expectedList.add(new Job("Electronics Engineer", "India", 65000, JobType.ENGINEERING));
        currentJob = expectedList.get(0);
        currentJob.setCategory("Senior Engineer");
        currentJob.setLanguageRequired("Hindi");
        currentJob.setTerm("Full-Time");
        expectedList.add(new Job("Data Analyst", "India", 65000, JobType.IT));
        currentJob = expectedList.get(1);
        currentJob.setCategory("Data Scientist");
        currentJob.setLanguageRequired("English");
        currentJob.setTerm("Full-Time");

        assertEquals(expectedList.toString(), filteredList.toString());

        filteredList = filterProcessor.filterBasedOnLocation("United States");
        expectedList = new ArrayList<>();
        assertEquals(expectedList.toString(), filteredList.toString());
    }

    @Test
    public void salaryRangeFilterTest() {
        int minSalary = 50_000;
        int maxSalary = 100_000;

        ArrayList<Job> filteredList = filterProcessor.filterBasedOnSalaryRange(minSalary, maxSalary);

        ArrayList<Job> expectedList = new ArrayList<>();
        expectedList.add(new Job("Software Developer", "Canada", 80_000, JobType.IT));
        Job currentJob = expectedList.get(0);
        currentJob.setCategory("Junior Developer");
        currentJob.setLanguageRequired("English");
        currentJob.setTerm("Full-Time");
        expectedList.add(new Job("Web Developer", "Canada", 60_000, JobType.IT));
        currentJob = expectedList.get(1);
        currentJob.setCategory("Senior Developer");
        currentJob.setLanguageRequired("English");
        currentJob.setTerm("Full-Time");
        expectedList.add(new Job("Electronics Engineer", "India", 65_000, JobType.ENGINEERING));
        currentJob = expectedList.get(2);
        currentJob.setCategory("Senior Engineer");
        currentJob.setLanguageRequired("Hindi");
        currentJob.setTerm("Full-Time");
        expectedList.add(new Job("Mechanical Engineer", "Taiwan", 50_000, JobType.ENGINEERING));
        currentJob = expectedList.get(3);
        currentJob.setCategory("Power Plant Engineer");
        currentJob.setLanguageRequired("Chinese");
        currentJob.setTerm("Contractual");
        expectedList.add(new Job("Branch Manager", "Brazil", 50_000, JobType.FINANCE));
        currentJob = expectedList.get(4);
        currentJob.setCategory("Manager");
        currentJob.setLanguageRequired("Portuguese");
        currentJob.setTerm("Full-Time");
        expectedList.add(new Job("Orthopaedist", "Spain", 75_000, JobType.HEALTHCARE));
        currentJob = expectedList.get(5);
        currentJob.setCategory("Specialist");
        currentJob.setLanguageRequired("Spanish");
        currentJob.setTerm("Contractual");
        expectedList.add(new Job("Data Analyst", "India", 65_000, JobType.IT));
        currentJob = expectedList.get(6);
        currentJob.setCategory("Data Scientist");
        currentJob.setLanguageRequired("English");
        currentJob.setTerm("Full-Time");

        assertEquals(expectedList.toString(), filteredList.toString());

        filteredList = filterProcessor.filterBasedOnSalaryRange(100_000, 1_000_000);
        assertEquals(0, filteredList.size());
    }

    @Test
    public void originalJobListTest() {
        // apply some other filters before checking the original list that was passed.
        // There should be no modification in the original ArrayList
        filterProcessor.filterBasedOnLocation("Canada");
        filterProcessor.filterBasedOnTitle("Developer");

        assertEquals(jobs.toString(), filterProcessor.getOriginalJobList().toString());
    }

    @Test
    public void languageRequiredFilterTest() {
        String requiredLanguage = "English";

        ArrayList<Job> filteredList = filterProcessor.filterBasedOnLanguageRequired(requiredLanguage);

        ArrayList<Job> expectedList = new ArrayList<>();
        expectedList.add(new Job("Software Developer", "Canada", 80000, JobType.IT));
        Job currentJob = expectedList.get(0);
        currentJob.setCategory("Junior Developer");
        currentJob.setLanguageRequired("English");
        currentJob.setTerm("Full-Time");

        expectedList.add(new Job("Web Developer", "Canada", 60000, JobType.IT));
        currentJob = expectedList.get(1);
        currentJob.setCategory("Senior Developer");
        currentJob.setLanguageRequired("English");
        currentJob.setTerm("Full-Time");

        expectedList.add(new Job("Lawn Mower", "USA", 30000, JobType.MAINTENANCE));
        currentJob = expectedList.get(2);
        currentJob.setCategory("Specialist");
        currentJob.setLanguageRequired("English");
        currentJob.setTerm("Part-Time");

        expectedList.add(new Job("Research Associate Applied Physics", "USA", 40000, JobType.SCIENCE));
        currentJob = expectedList.get(3);
        currentJob.setCategory("Research Associate");
        currentJob.setLanguageRequired("English");
        currentJob.setTerm("Contractual");

        expectedList.add(new Job("Hotel Manager", "England", 35000, JobType.HOSPITALITY));
        currentJob = expectedList.get(4);
        currentJob.setCategory("Manager");
        currentJob.setLanguageRequired("English");
        currentJob.setTerm("Full-Time");

        expectedList.add(new Job("Daytime Nurse", "Australia", 20000, JobType.HEALTHCARE));
        currentJob = expectedList.get(5);
        currentJob.setCategory("Head Nurse");
        currentJob.setLanguageRequired("English");
        currentJob.setTerm("Part-Time");

        expectedList.add(new Job("Data Analyst", "India", 65000, JobType.IT));
        currentJob = expectedList.get(6);
        currentJob.setCategory("Data Scientist");
        currentJob.setLanguageRequired("English");
        currentJob.setTerm("Full-Time");

        assertEquals(expectedList.toString(), filteredList.toString());

        requiredLanguage = "Spanish";
        filteredList = filterProcessor.filterBasedOnLanguageRequired(requiredLanguage);

        expectedList = new ArrayList<>();
        expectedList.add(new Job("Orthopaedist", "Spain", 75000, JobType.HEALTHCARE));
        currentJob = expectedList.get(0);
        currentJob.setCategory("Specialist");
        currentJob.setLanguageRequired("Spanish");
        currentJob.setTerm("Contractual");

        assertEquals(expectedList.toString(), filteredList.toString());
    }

    @Test
    public void jobTermFilterTest() {
        String jobTerm = "Contractual";

        ArrayList<Job> filteredList = filterProcessor.filterBasedOnJobTerm(jobTerm);

        ArrayList<Job> expectedList = new ArrayList<>();
        expectedList.add(new Job("Mechanical Engineer", "Taiwan", 50000, JobType.ENGINEERING));
        Job currentJob = expectedList.get(0);
        currentJob.setCategory("Power Plant Engineer");
        currentJob.setLanguageRequired("Chinese");
        currentJob.setTerm("Contractual");

        expectedList.add(new Job("Research Associate Applied Physics", "USA", 40000, JobType.SCIENCE));
        currentJob = expectedList.get(1);
        currentJob.setCategory("Research Associate");
        currentJob.setLanguageRequired("English");
        currentJob.setTerm("Contractual");

        expectedList.add(new Job("Orthopaedist", "Spain", 75000, JobType.HEALTHCARE));
        currentJob = expectedList.get(2);
        currentJob.setCategory("Specialist");
        currentJob.setLanguageRequired("Spanish");
        currentJob.setTerm("Contractual");

        assertEquals(expectedList.toString(), filteredList.toString());

        jobTerm = "Part-Time";
        filteredList = filterProcessor.filterBasedOnJobTerm(jobTerm);

        expectedList = new ArrayList<>();
        expectedList.add(new Job("Lawn Mower", "USA", 30000, JobType.MAINTENANCE));
        currentJob = expectedList.get(0);
        currentJob.setCategory("Specialist");
        currentJob.setLanguageRequired("English");
        currentJob.setTerm("Part-Time");

        expectedList.add(new Job("Daytime Nurse", "Australia", 20000, JobType.HEALTHCARE));
        currentJob = expectedList.get(1);
        currentJob.setCategory("Head Nurse");
        currentJob.setLanguageRequired("English");
        currentJob.setTerm("Part-Time");

        assertEquals(expectedList.toString(), filteredList.toString());
    }

    @Test
    public void jobCategoryFilterTest() {
        String jobCategory = "Manager";

        ArrayList<Job> filteredList = filterProcessor.filterBasedOnCategory(jobCategory);

        ArrayList<Job> expectedList = new ArrayList<>();
        expectedList.add(new Job("Branch Manager", "Brazil", 50000, JobType.FINANCE));
        Job currentJob = expectedList.get(0);
        currentJob.setCategory("Manager");
        currentJob.setLanguageRequired("Portuguese");
        currentJob.setTerm("Full-Time");

        expectedList.add(new Job("Hotel Manager", "England", 35000, JobType.HOSPITALITY));
        currentJob = expectedList.get(1);
        currentJob.setCategory("Manager");
        currentJob.setLanguageRequired("English");
        currentJob.setTerm("Full-Time");

        assertEquals(expectedList.toString(), filteredList.toString());

        jobCategory = "Junior Developer";
        filteredList = filterProcessor.filterBasedOnCategory(jobCategory);

        expectedList = new ArrayList<>();
        expectedList.add(new Job("Software Developer", "Canada", 80000, JobType.IT));
        currentJob = expectedList.get(0);
        currentJob.setCategory("Junior Developer");
        currentJob.setLanguageRequired("English");
        currentJob.setTerm("Full-Time");

        assertEquals(expectedList.toString(), filteredList.toString());
    }
}
