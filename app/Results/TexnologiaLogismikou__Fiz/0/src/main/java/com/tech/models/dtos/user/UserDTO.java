package com.tech.models.dtos.user;
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
import com.tech.DTO.ICustomValidator;
import com.tech.DTO.ICustomValidator;
import com.tech.DTO.ICustomValidator;
import com.tech.DTO.ICustomValidator;
import com.tech.DTO.ICustomValidator;
import com.tech.DTO.ICustomValidator;
import com.tech.DTO.ICustomValidator;
import com.tech.DTO.ICustomValidator;
import com.tech.DTO.ICustomValidator;
import com.tech.DTO.ICustomValidator;
import com.tech.DTO.ICustomValidator;
import com.tech.DTO.ICustomValidator;
public class UserDTO extends BaseDTO{

 private  List<IStringValidator> USER_NAME_VALIDATORS;

 private  List<IStringValidator> PASSWORD_VALIDATORS;

 private  List<IStringValidator> EMAIL_VALIDATORS;

 private  List<IStringValidator> STRING_VALIDATORS;

 private  List<IStringValidator> PROFILE_PHOTO_VALIDATORS;

 private  List<IStringValidator> STATUS_VALIDATORS;

 private  String username;

 private  String password;

 private  boolean enabled;

 private  boolean hasRoom;

 private  String email;

 private  String profile_photo;

 private  String status;

 private  String last_name;

 private  Date birthday;

 private  String hometown;

 private  String firstname;

public UserDTO() {
}
public String getFirstname(){
    return firstname;
}


public Date getBirthday(){
    return birthday;
}


@Override
public String getDTOName(){
    return "UserDTO";
}


public void registerStringValidator(IStringValidator strVal,ValidationScopes scope){
    switch(scope) {
        case USER_NAME:
            USER_NAME_VALIDATORS.add(strVal);
            USER_NAME_VALIDATORS.get(0).setNext(strVal);
            break;
        case PASSWORD:
            PASSWORD_VALIDATORS.add(strVal);
            PASSWORD_VALIDATORS.get(0).setNext(strVal);
            break;
        case EMAIL:
            EMAIL_VALIDATORS.add(strVal);
            EMAIL_VALIDATORS.get(0).setNext(strVal);
            break;
        case STRING:
            STRING_VALIDATORS.add(strVal);
            STRING_VALIDATORS.get(0).setNext(strVal);
            break;
        case PROFILE_PHOTO:
            PROFILE_PHOTO_VALIDATORS.add(strVal);
            PROFILE_PHOTO_VALIDATORS.get(0).setNext(strVal);
            break;
        case STATUS:
            STATUS_VALIDATORS.add(strVal);
            STATUS_VALIDATORS.get(0).setNext(strVal);
            break;
        default:
            throw new ValidatorNotListedException();
    }
}


public void registerValidator(ICustomValidator newValidator,ValidationScopes scope){
    if (newValidator instanceof IStringValidator) {
        registerStringValidator((IStringValidator) newValidator, scope);
    } else {
        throw new InappropriateValidatorException();
    }
}


public String getHometown(){
    return hometown;
}


public String getStatus(){
    return status;
}


public void cleanValidator(){
    USER_NAME_VALIDATORS.clear();
    PASSWORD_VALIDATORS.clear();
    EMAIL_VALIDATORS.clear();
    STRING_VALIDATORS.clear();
    PROFILE_PHOTO_VALIDATORS.clear();
    STATUS_VALIDATORS.clear();
    USER_NAME_VALIDATORS.add(new EmptyStringValidator());
    PASSWORD_VALIDATORS.add(new EmptyStringValidator());
    EMAIL_VALIDATORS.add(new EmptyStringValidator());
    STRING_VALIDATORS.add(new EmptyStringValidator());
    PROFILE_PHOTO_VALIDATORS.add(new EmptyStringValidator());
    STATUS_VALIDATORS.add(new EmptyStringValidator());
}


public String getUsername(){
    return username;
}


public boolean isHasRoom(){
    return hasRoom;
}


public String getPassword(){
    return password;
}


public String getLast_name(){
    return last_name;
}


public boolean isEnabled(){
    return enabled;
}


public boolean removeValidator(ValidationScopes scope,int i){
    if (i == 0) {
        return false;
    }
    switch(scope) {
        case USER_NAME:
            if (USER_NAME_VALIDATORS.size() >= i + 1) {
                USER_NAME_VALIDATORS.get(i - 1).replaceNext(USER_NAME_VALIDATORS.get(i).getNext());
                USER_NAME_VALIDATORS.remove(i);
                return true;
            }
            return false;
        case PASSWORD:
            if (PASSWORD_VALIDATORS.size() >= i + 1) {
                PASSWORD_VALIDATORS.get(i - 1).replaceNext(PASSWORD_VALIDATORS.get(i).getNext());
                PASSWORD_VALIDATORS.remove(i);
                return true;
            }
            return false;
        case EMAIL:
            if (EMAIL_VALIDATORS.size() >= i + 1) {
                EMAIL_VALIDATORS.get(i - 1).replaceNext(EMAIL_VALIDATORS.get(i).getNext());
                EMAIL_VALIDATORS.remove(i);
                return true;
            }
            return false;
        case STRING:
            if (STRING_VALIDATORS.size() >= i + 1) {
                STRING_VALIDATORS.get(i - 1).replaceNext(STRING_VALIDATORS.get(i).getNext());
                STRING_VALIDATORS.remove(i);
                return true;
            }
            return false;
        case PROFILE_PHOTO:
            if (PROFILE_PHOTO_VALIDATORS.size() >= i + 1) {
                PROFILE_PHOTO_VALIDATORS.get(i - 1).replaceNext(PROFILE_PHOTO_VALIDATORS.get(i).getNext());
                PROFILE_PHOTO_VALIDATORS.remove(i);
                return true;
            }
            return false;
        case STATUS:
            if (STATUS_VALIDATORS.size() >= i + 1) {
                STATUS_VALIDATORS.get(i - 1).replaceNext(STATUS_VALIDATORS.get(i).getNext());
                STATUS_VALIDATORS.remove(i);
                return true;
            }
            return false;
        default:
            throw new ValidatorNotListedException();
    }
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
        case STRING:
            for (ICustomValidator vLookUp : STRING_VALIDATORS) {
                if (vLookUp.getName().equals("Empty")) {
                    continue;
                }
                i++;
                list.add(i + ": " + vLookUp.getName());
            }
            return list;
        case PROFILE_PHOTO:
            for (ICustomValidator vLookUp : PROFILE_PHOTO_VALIDATORS) {
                if (vLookUp.getName().equals("Empty")) {
                    continue;
                }
                i++;
                list.add(i + ": " + vLookUp.getName());
            }
            return list;
        case STATUS:
            for (ICustomValidator vLookUp : STATUS_VALIDATORS) {
                if (vLookUp.getName().equals("Empty")) {
                    continue;
                }
                i++;
                list.add(i + ": " + vLookUp.getName());
            }
            return list;
        default:
            throw new ValidatorNotListedException();
    }
}


public String getEmail(){
    return email;
}


public String getProfile_photo(){
    return profile_photo;
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
    currentTest = PROFILE_PHOTO_VALIDATORS.get(0).validate(profile_photo);
    if (!currentTest.getLeft()) {
        return currentTest;
    }
    currentTest = STRING_VALIDATORS.get(0).validate(hometown);
    if (!currentTest.getLeft()) {
        return currentTest;
    }
    currentTest = STATUS_VALIDATORS.get(0).validate(status);
    if (!currentTest.getLeft()) {
        return currentTest;
    }
    currentTest = USER_NAME_VALIDATORS.get(0).validate(firstname);
    return currentTest;
}


}