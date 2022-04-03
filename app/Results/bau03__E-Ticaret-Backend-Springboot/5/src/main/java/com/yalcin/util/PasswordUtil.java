package com.yalcin.util;
 import java.util.regex;
public class PasswordUtil {


public boolean isValidPassword(String password){
    // Regex to check valid password.
    String regex = "^(?=.*[0-9])" + "(?=.*[a-z])(?=.*[A-Z])" + "(?=\\S+$).{6,20}$";
    // Compile the ReGex
    Pattern p = Pattern.compile(regex);
    // If the password is empty
    // return false
    if (password == null) {
        return false;
    }
    // Pattern class contains matcher() method
    // to find matching between given password
    // and regular expression.
    Matcher m = p.matcher(password);
    // Return if the password
    // matched the ReGex
    return m.matches();
}


}