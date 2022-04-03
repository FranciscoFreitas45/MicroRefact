package com.netease.util;
 import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class ValidateUtil {


public boolean validateMobile(String mobile){
    if (mobile == null || mobile.trim().equals("")) {
        return false;
    }
    String pat = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
    Pattern p = Pattern.compile(pat);
    Matcher match = p.matcher(mobile);
    return match.matches();
}


public boolean validateCredential_number(String credential_number){
    if (credential_number == null || credential_number.trim().equals("")) {
        return false;
    }
    // 18位身份证
    String pat = "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X)$";
    Pattern p = Pattern.compile(pat);
    Matcher match = p.matcher(credential_number);
    // 15位身份证
    String patSecond = "/^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$/";
    Pattern pSecond = Pattern.compile(patSecond);
    Matcher matchSecond = pSecond.matcher(credential_number);
    return match.matches() || matchSecond.matches();
}


public boolean validatePassword(String password){
    if (password == null || password.trim().equals("")) {
        return false;
    }
    String pat = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$";
    Pattern p = Pattern.compile(pat);
    Matcher match = p.matcher(password);
    return match.matches();
}


public boolean validateEmail(String email){
    if (email == null || email.trim().equals("")) {
        return false;
    }
    String pat = "[a-zA-Z0-9_\\-\\.]+@[a-zA-Z0-9]+(\\.(com))";
    Pattern p = Pattern.compile(pat);
    Matcher match = p.matcher(email);
    return match.matches();
}


public boolean validateLogin_name(String login_name){
    if (login_name == null || login_name.trim().equals("")) {
        return false;
    }
    String pat = "^[A-Za-z0-9-_/]{6,16}$";
    Pattern p = Pattern.compile(pat);
    Matcher match = p.matcher(login_name);
    return match.matches();
}


}