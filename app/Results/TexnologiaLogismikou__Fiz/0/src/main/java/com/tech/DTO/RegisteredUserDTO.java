package com.tech.DTO;
 import com.tech.configurations.tools.Pair;
import com.tech.configurations.tools.ValidationScopes;
import com.tech.configurations.tools.customvalidators.elements.EmptyStringValidator;
import com.tech.configurations.tools.customvalidators.interfaces.ICustomValidator;
import com.tech.configurations.tools.customvalidators.interfaces.IStringValidator;
import com.tech.exceptions.customexceptions.InappropriateValidatorException;
import com.tech.exceptions.customexceptions.ValidatorNotListedException;
import com.tech.models.dtos.superclass.BaseDTO;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.springframework.http.ResponseEntity;
public class RegisteredUserDTO extends BaseDTO{

 private  List<IStringValidator> USER_NAME_VALIDATORS;

 private  List<IStringValidator> PASSWORD_VALIDATORS;

 private  List<IStringValidator> EMAIL_VALIDATORS;

 private  String username;

 private  String password;

 private  String email;

 private  String last_name;

 private  String firstname;

 private  Date birthday;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://1";

public RegisteredUserDTO() {
}
public String getFirstname(){
    return firstname;
}


public Date getBirthday(){
    return birthday;
}


@Override
public String getDTOName(){
    return "RegisteredUserDTO";
}


public String getUsername(){
    return username;
}


public String getPassword(){
    return password;
}


public String getLast_name(){
    return last_name;
}


public List<String> getValidatorList(ValidationScopes scope){
    List<String> list = new ArrayList<>();
    int i = 0;
    switch(scope) {
        case USER_NAME:
            for (ICustomValidator vLookUp : USER_NAME_VALIDATORS) {
                if (vLookUp.getName().equals("Empty")) {
                    continue;
                }
                i++;
                list.add(i + ": " + vLookUp.getName());
            }
            return list;
        case PASSWORD:
            for (ICustomValidator vLookUp : PASSWORD_VALIDATORS) {
                if (vLookUp.getName().equals("Empty")) {
                    continue;
                }
                i++;
                list.add(i + ": " + vLookUp.getName());
            }
            return list;
        case EMAIL:
            for (ICustomValidator vLookUp : EMAIL_VALIDATORS) {
                if (vLookUp.getName().equals("Empty")) {
                    continue;
                }
                i++;
                list.add(i + ": " + vLookUp.getName());
            }
            return list;
        // case BIRTHDAY:
        // for(ICustomValidator vLookUp:BIRTHDAY_VALIDATORS)
        // {
        // if(vLookUp.getName().equals("Empty")) { continue; }
        // i++;
        // list.add(i + ": " + vLookUp.getName());
        // }
        // return list;
        default:
            throw new ValidatorNotListedException();
    }
}


public String getEmail(){
    return email;
}


@Override
public Pair<Boolean,ResponseEntity> validate(){
    Pair<Boolean, ResponseEntity> currentTest = USER_NAME_VALIDATORS.get(0).validate(username);
    if (!currentTest.getLeft()) {
        return currentTest;
    }
    currentTest = PASSWORD_VALIDATORS.get(0).validate(password);
    if (!currentTest.getLeft()) {
        return currentTest;
    }
    currentTest = EMAIL_VALIDATORS.get(0).validate(email);
    if (!currentTest.getLeft()) {
        return currentTest;
    }
    currentTest = USER_NAME_VALIDATORS.get(0).validate(last_name);
    if (!currentTest.getLeft()) {
        return currentTest;
    }
    currentTest = USER_NAME_VALIDATORS.get(0).validate(firstname);
    // if(!currentTest.getLeft())
    // {
    // return currentTest;
    // }
    // currentTest = BIRTHDAY_VALIDATORS.get(0).validate(birthday);
    return currentTest;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/validate"))

;
Pair<Boolean,ResponseEntity> aux = restTemplate.getForObject(builder.toUriString(),Pair<Boolean,ResponseEntity>.class);
return aux;
}


}