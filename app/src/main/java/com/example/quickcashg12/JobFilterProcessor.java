package com.example.quickcashg12;

import java.util.ArrayList;

public class JobFilterProcessor {
    private ArrayList<Job> jobs;

    public JobFilterProcessor(ArrayList<Job> jobs) {
        this.jobs = jobs;
    }

    /**
     * This method filters the job based on title. The jobs that contians the given substring
     * will be returned as a new ArrayList. The original jobs ArrayList won't be changed.
     * @param titleSubstring the substring present in the title that is to be filtered
     * @return An ArrayList containing the filtered Jobs based on title
     */
    public ArrayList<Job> filterBasedOnTitle(String titleSubstring) {
        ArrayList<Job> filteredJobs = new ArrayList<>();
        for(int i = 0; i < jobs.size(); i++){
            if(jobs.get(i).getName().contains(titleSubstring)) {
                filteredJobs.add(jobs.get(i));
            }
        }
        return filteredJobs;
    }

    /**
     * This method filters the job list based on the salary range. A new ArrayList will be returned.
     * @param minimumSalary
     * @param maximumSalary
     * @return An ArrayList containing the filtered Jobs based on salary range
     */
    public ArrayList<Job> filterBasedOnSalaryRange(int minimumSalary, int maximumSalary) {
        ArrayList<Job> filteredJobs = new ArrayList<>();
        for (int i = 0; i< jobs.size(); i++){
            Job currJob = jobs.get(i);
            if(currJob.getSalary() >= minimumSalary && currJob.getSalary() <= maximumSalary) {
                filteredJobs.add(currJob);
            }
        }
        return filteredJobs;
    }

    /**
     * This method filters jobs based on exact location between jobs
     * @param location
     * @return An ArrayList containing the filtered Jobs based on location
     */
    public ArrayList<Job> filterBasedOnLocation(String location) {
        ArrayList<Job> filteredJobs = new ArrayList<>();
        for(int i = 0; i < jobs.size(); i++) {
            if(jobs.get(i).getLocation().equals(location)) {
                filteredJobs.add(jobs.get(i));
            }
        }
        return filteredJobs;
    }

    /**
     * This method will return the original job list that was passed before applying any filters
     * This will be helpful when all the filters are removed
     * @return Original ArrayList containing all the jobs before any filtering
     */
    public ArrayList<Job> getOriginalJobList() {
        return jobs;
    }

    /**
     * This method filters jobs according to the language required for the job
     * @param languageRequired
     * @return An ArrayList containing the filtered Jobs based on language required
     */
    public ArrayList<Job> filterBasedOnLanguageRequired(String languageRequired) {
        ArrayList<Job> filteredJobs = new ArrayList<>();
        for(int i = 0; i < jobs.size(); i++) {
            if(jobs.get(i).getLanguageRequired().equalsIgnoreCase(languageRequired)) {
                filteredJobs.add(jobs.get(i));
            }
        }
        return filteredJobs;
    }

    /**
     * This method filters job based on the job term such as Full-Term/Contractual/Intern
     * @param term
     * @return An ArrayList containing the filtered Jobs based on job term
     */
    public ArrayList<Job> filterBasedOnJobTerm(String term) {
        ArrayList<Job> filteredJobs = new ArrayList<>();
        for(int i = 0; i < jobs.size(); i++) {
            if(jobs.get(i).getTerm().equalsIgnoreCase(term)) {
                filteredJobs.add(jobs.get(i));
            }
        }
        return filteredJobs;
    }

    /**
     * This method filters job based on category such as job position
     * @param category
     * @return An ArrayList containing the filtered Jobs based on job category
     */
    public ArrayList<Job> filterBasedOnCategory(String category) {
        ArrayList<Job> filteredJobs = new ArrayList<>();
        for(int i = 0; i < jobs.size(); i++) {
            if(jobs.get(i).getCategory().equalsIgnoreCase(category)) {
                filteredJobs.add(jobs.get(i));
            }
        }
        return filteredJobs;
    }
}
