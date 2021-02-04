package com.example.quickcashg12;

public enum JobType {
    ACCOUNTING("Accounting"),
    ADMINISTRATION("Administration"),
    EDUCATION("Education"),
    ENGINEERING("Engineering"),
    FINANCE("Finance"),
    HEALTHCARE("Health Care"),
    HOSPITALITY("Hospitality"),
    IT("IT"),
    MAINTENANCE("Maintenance"),
    MANUFACTURING("Manufacturing"),
    MARKETING("Marketing"),
    SALES("Sales"),
    SCIENCE("Science");

    private String jobType;

    JobType(String jobType) {
        this.jobType = jobType;
    }

    public String getJobType(){
        return jobType;
    }
}
