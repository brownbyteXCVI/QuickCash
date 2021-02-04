package com.example.quickcashg12;

import java.util.ArrayList;
import java.util.Objects;

public class User {
    //attributes
    public  String name, email,E, creditcardNum, pin, securityCode,area;

    private String userId;

    // these are the jobs for which this employee has applied to
    private ArrayList<Job> appliedJobs;

    // This will store the jobs that this employer has posted and will be used to connect
    // with employees
    private ArrayList<Job> postedJobs;

    //constructor
    public User(){ appliedJobs = new ArrayList<>(); postedJobs = new ArrayList<>();}

    public User(String name, String email, String E, String creditcardNum, String pin, String securityCode) {
        this.name = name;
        this.email = email;
        this.E = E;
        this.creditcardNum = creditcardNum;
        this.pin = pin;
        this.securityCode = securityCode;

        this.appliedJobs = new ArrayList<>();
        this.postedJobs = new ArrayList<>();
    }
    public void setPreferedLocation(String loc){
        area = loc;
    }

    public String getPreferedLocation(){
        return this.area;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * This method allows the employee to apply to a job. This method does two things,
     * 1. add the applied jobs to candidates list
     * 2. add the userId of the emplyee to the job passed as argument
     * @param selectedJob
     */
    public void applyToJob(Job selectedJob) {
        // TODO: add the selectedJob to the applied Jobs list
        appliedJobs.add(selectedJob);
        // TODO: add this users userId to the selectedJob
        selectedJob.setEmployerId(this.userId);
//        appliedJobs.add(selectedJob);
    }



    public ArrayList<Job> getAppliedJobs() {
        return appliedJobs;
    }

    /**
     * This will add a job to the list of posted jobs maintained by this employer
     * @param postedJob
     */
    public void addToPostedJobs(Job postedJob) {
        postedJobs.add(postedJob);
    }

    /**
     * Fetches the list of jobs posted by this employer
     * @return
     */
    public ArrayList<Job> getPostedJobs() {
        return postedJobs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return name.equals(user.name) &&
                email.equals(user.email) &&
                userId.equals(user.userId);
    }

    @Override
    public int hashCode() {
        return name.hashCode() +  email.hashCode() + userId.hashCode();
    }

    //    public String getName() {
//        return name;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public String getE() {
//        return E;
//    }
}
