package com.example.quickcashg12;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class JobApplicationTest {

    ArrayList<User> employees;
    ArrayList<User> employers;
    ArrayList<Job> jobs;

    @Before
    public void init() {
        employees = new ArrayList<>();
        employers = new ArrayList<>();
        jobs = new ArrayList<>();

        User employer1 = new User();
        employer1.name = "Sundar Pichai";
        employer1.email = "sundar.pichai@gmail.com";
        employer1.setUserId("1");

        User employer2 = new User();
        employer2.name = "Steve Jobs";
        employer2.email = "steve.jobs@apple.com";
        employer2.setUserId("2");

        User employee1 = new User();
        employee1.name = "Bill Gates";
        employee1.email = "bill.gates@microsoft.com";
        employee1.setUserId("11");

        User employee2 = new User();
        employee2.name = "Jeff Bezos";
        employee2.email = "jeff.bezos@amazon.com";
        employee2.setUserId("12");

        User employee3 = new User();
        employee3.name = "Mark Zuckerberg";
        employee3.email = "mark.zuckerberg@facebook.com";
        employee3.setUserId("13");

        User employee4 = new User();
        employee4.name = "Elon Musk";
        employee4.email = "elon.musk@spacex.com";
        employee4.setUserId("14");

        employers.add(employer1);
        employers.add(employer2);

        employees.add(employee1);
        employees.add(employee2);
        employees.add(employee3);
        employees.add(employee4);

        jobs.add(new Job("Software Developer", "Canada", 80000, JobType.IT));
        Job currentJob = jobs.get(0);
        currentJob.setJobId("1");
        currentJob.setEmployerId("1");
        currentJob.setCategory("Junior Developer");
        currentJob.setLanguageRequired("English");
        currentJob.setTerm("Full-Time");

        jobs.add(new Job("Web Developer", "Canada", 60000, JobType.IT));
        currentJob = jobs.get(1);
        currentJob.setJobId("2");
        currentJob.setEmployerId("2");
        currentJob.setCategory("Senior Developer");
        currentJob.setLanguageRequired("English");
        currentJob.setTerm("Full-Time");

        jobs.add(new Job("Electronics Engineer", "India", 65000, JobType.ENGINEERING));
        currentJob = jobs.get(2);
        currentJob.setJobId("3");
        currentJob.setEmployerId("1");
        currentJob.setCategory("Senior Engineer");
        currentJob.setLanguageRequired("Hindi");
        currentJob.setTerm("Full-Time");

        jobs.add(new Job("Mechanical Engineer", "Taiwan", 50000, JobType.ENGINEERING));
        currentJob = jobs.get(3);
        currentJob.setJobId("4");
        currentJob.setEmployerId("2");
        currentJob.setCategory("Power Plant Engineer");
        currentJob.setLanguageRequired("Chinese");
        currentJob.setTerm("Contractual");

        jobs.add(new Job("Lawn Mower", "USA", 30000, JobType.MAINTENANCE));
        currentJob = jobs.get(4);
        currentJob.setJobId("5");
        currentJob.setEmployerId("2");
        currentJob.setCategory("Specialist");
        currentJob.setLanguageRequired("English");
        currentJob.setTerm("Part-Time");
    }

    @Test
    public void applyToJobTest() {
        User employee1 = employees.get(0);
        User employee2 = employees.get(1);
        User employee3 = employees.get(2);

        Job job1 = jobs.get(0);
        Job job2 = jobs.get(1);
        Job job3 = jobs.get(2);
        Job job4 = jobs.get(3);
        Job job5 = jobs.get(4);

        employee1.applyToJob(job1);
        employee1.applyToJob(job2);
        employee2.applyToJob(job3);
        employee3.applyToJob(job4);
        employee3.applyToJob(job5);

        ArrayList<Job> appliedJobs1 = employee1.getAppliedJobs();
        ArrayList<Job> appliedJobs2 = employee2.getAppliedJobs();
        ArrayList<Job> appliedJobs3 = employee3.getAppliedJobs();

        assertTrue(appliedJobs1.contains(job1) && appliedJobs1.contains(job2));
        assertTrue(appliedJobs2.contains(job3));
        assertTrue(appliedJobs3.contains(job4) && appliedJobs3.contains(job5));
    }

}
