package ar.com.veterinaria.app.bo.validatorPattern;
 import java.util.regex.Pattern;
public class ValidatorPatternServiceHelper {

 private  String NAME_REGEX;

 private  Pattern NAME_PATTERN;

 private  String NUMBER_REGEX;

 private  Pattern NUMBER_PATTERN;

 private  String NUMBER_LETTER_REGEX;

 private  Pattern NUMBER_LETTER_PATTERN;

 private  String EMAIL_REGEX;

 private  Pattern EMAIL_PATTERN;

 private  String PASSWORD_REGEX;

 private  Pattern PASSWORD_PATTERN;


public Pattern getNumberPattern(){
    return NUMBER_PATTERN;
}


public Pattern getEmailPattern(){
    return EMAIL_PATTERN;
}


public Pattern getNamePattern(){
    return NAME_PATTERN;
}


public Pattern getPasswordPattern(){
    return PASSWORD_PATTERN;
}


public Pattern getNumberLetterPattern(){
    return NUMBER_LETTER_PATTERN;
}


}