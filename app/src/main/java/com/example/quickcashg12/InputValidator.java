package com.example.quickcashg12;

import android.util.Patterns;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidator {
    private String password;
    private String userName;
    private String eMail;
    private String creditCardNum;
    private String pin;
    private String securityCode;
    private String address;
    private String description;

    public InputValidator() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public InputValidator(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    public String geteMail() {
        return eMail;
    }

    public String getCreditCardNum() {
        return creditCardNum;
    }

    public String getPin() {
        return pin;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public void setCreditCardNum(String creditCardNum) {
        this.creditCardNum = creditCardNum;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }


    public boolean isPasswordValid() {
        if (!password.matches(".*[A-Z].*") || !password.matches(".*[a-z].*") || !password.matches(".*\\d.*")) {
            return false;
        }
        return true;
    }

    public boolean checkAddress() {
        if (address.isEmpty())
            return false;
        else return true;
    }

    public boolean checkName() {
        if (userName.isEmpty()) {
            return false;
        } else return true;
    }

    public boolean checkPass() {
        if (password.isEmpty()) {
            return false;
        } else return true;
    }

    public boolean checkEmail() {
        if (eMail.isEmpty()) {
            return false;
        } else {
            // emailRegex is email pattern for matching an email
            String emailRegex = "^[a-zA-Z0-9_.-]+@[a-zA-Z0-9]+([.][a-zA-Z0-9.-]+)+$";
            Pattern emailPattern = Pattern.compile(emailRegex);
            Matcher emailMatcher = emailPattern.matcher(eMail);

            if (emailMatcher.matches()) {
                return true;
            } else {
                return false;
            }
        }
    }

    public boolean passLength() {
        if (password.length() < 6) return false;
        else return true;
    }

    public boolean creditcardLength() {

        if (creditCardNum.length() == 16) {
            return true;
        } else return false;
    }

    public boolean PinLength() {
        if (pin.length() == 6 || pin.length() == 4) {
            return true;
        } else return false;
    }

    public boolean securityCodeLength() {
        if (securityCode.length() == 3) {
            return true;
        } else return false;
    }

    public boolean isDigit(String str) {
        if (str.isEmpty()) {
            return false;
        }
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    public boolean checker() {
        return (checkEmail() && checkName() && checkPass()
                && creditcardLength() && PinLength() && securityCodeLength()
                && isDigit(creditCardNum) && isDigit(pin) && isDigit(securityCode));
    }

    public boolean checkDescription() {
        if(description.isEmpty())
            return false;
        else return true;
    }


}
