package com.example.quickcashg12;
import java.util.ArrayList;

/**
 * This class represents a Job
 */
public class Job {
    /**
     * This is the name of the job
     */

    //Name, location and salary of a job
    private String name;
    private String location;
    private int salary;

    private String term;
    private String category;
    private int yearsOfExperienceRequired;
    private String languageRequired;
    private String jobId;
    private String employerId;

    // an enum representing a job type
    private JobType jobType;
    //private String jobType2;

    // this will store the ids if the employees who have assigned to this job
    private ArrayList<Integer> registeredEmployees;

    public Job() {
        registeredEmployees = new ArrayList<>();
    }

    public Job(String name, String location, int salary, JobType jobType) {
        this.name = name;
        this.location = location;
        this.salary = salary;
        this.jobType = jobType;

        this.registeredEmployees = new ArrayList<>();
    }

    public Job(String name) {
        this.name = name;
    }

    // accessor method
    public String getName() {
        return name;
    }

    // getter method
    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public JobType getJobType() {
        return jobType;
    }


    public void setJobType(JobType jobType) {
        this.jobType = jobType;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getYearsOfExperienceRequired() {
        return yearsOfExperienceRequired;
    }

    public void setYearsOfExperienceRequired(int yearsOfExperienceRequired) {
        this.yearsOfExperienceRequired = yearsOfExperienceRequired;
    }

    public String getLanguageRequired() {
        return languageRequired;
    }

    public void setLanguageRequired(String languageRequired) {
        this.languageRequired = languageRequired;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getEmployerId() {
        return employerId;
    }

    public void setEmployerId(String employerId) {
        this.employerId = employerId;
    }

    public void applyToThisJob(int userId) {
        registeredEmployees.add(userId);
    }

    public ArrayList<Integer> getRegisteredEmployees() {
        return registeredEmployees;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Job job = (Job) o;
        return jobId.equals(job.jobId);
    }

    @Override
    public int hashCode() {
        return jobId.hashCode();
    }

    @Override
    public String toString() {
        return "Job{" +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", salary=" + salary +
                ", term='" + term + '\'' +
                ", category='" + category + '\'' +
                ", yearsOfExperienceRequired=" + yearsOfExperienceRequired +
                ", languareRequired='" + languageRequired + '\'' +
                ", jobId=" + jobId +
                ", employerId=" + employerId +
                ", jobType=" + jobType +
                '}';
    }
}
