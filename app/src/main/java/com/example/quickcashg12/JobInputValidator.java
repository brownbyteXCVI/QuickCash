package com.example.quickcashg12;

public class JobInputValidator {

    public JobInputValidator(){}
    /**
     * Method to validate the job title
     * @param title
     * @return
     */
    public boolean validateJobTitle(String title) {
        if(title == null || title.length() == 0)
            return false;

        // should contain only Alphabets or spaces
        for(int index = 0; index < title.length(); index++) {
            char currentChar = title.charAt(index);

            if(!Character.isLetter(currentChar) && currentChar != ' ')
                return false;
        }

        return true;
    }

    /**
     * Method to validate Job location
     * @param location
     * @return
     */
    public boolean validateJobLocation(String location) {
        if(location == null || location.length() == 0)
            return false;

        // should contain only Alphanumeric or space
        for(int index = 0; index < location.length(); index++) {
            char currentChar = location.charAt(index);

            if(!Character.isLetterOrDigit(currentChar) && currentChar != ' ')
                return false;
        }

        return true;
    }

    /**
     * Method to validate Salary
     * @param salary
     * @return
     */
    public boolean validateJobSalary(String salary) {
        if(salary == null || salary.length() == 0)
            return false;

        // must be a valid whole number
        for(int index = 0; index < salary.length(); index++) {
            char currentChar = salary.charAt(index);

            if(!Character.isDigit(currentChar))
                return false;
        }

        return true;
    }
}
