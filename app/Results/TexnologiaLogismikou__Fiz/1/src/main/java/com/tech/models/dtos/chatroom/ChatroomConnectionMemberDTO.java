package com.tech.models.dtos.chatroom;
 import com.tech.configurations.tools.Pair;
import com.tech.configurations.tools.ValidationScopes;
import com.tech.configurations.tools.customvalidators.elements.EmptyFloatValidator;
import com.tech.configurations.tools.customvalidators.elements.EmptyStringValidator;
import com.tech.configurations.tools.customvalidators.interfaces.ICustomValidator;
import com.tech.configurations.tools.customvalidators.interfaces.IFloatValidator;
import com.tech.configurations.tools.customvalidators.interfaces.IStringValidator;
import com.tech.exceptions.customexceptions.InappropriateValidatorException;
import com.tech.exceptions.customexceptions.ValidatorNotListedException;
import com.tech.models.dtos.superclass.BaseDTO;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.http.ResponseEntity;
public class ChatroomConnectionMemberDTO extends BaseDTO{

 private  List<IStringValidator> MEMBERNAME_VALIDATORS;

 private  List<IStringValidator> ROOM_NAME_VALIDATORS;

 private  List<IStringValidator> MODE_VALIDATORS;

 private  List<IStringValidator> PASSWORD_VALIDATORS;

 private  List<IFloatValidator> LAT_VALIDATORS;

 private  List<IFloatValidator> LNG_VALIDATORS;

 private  String room_name;

 private  String member_name;

 private  String password;

 private  String mode;

 private  float lat;

 private  float lng;


public float getLat(){
    return lat;
}


@Override
public String getDTOName(){
    return "ChatroomConnectionMemberDTO";
}


public void registerStringValidator(IStringValidator newValidator,ValidationScopes scope){
    switch(scope) {
        case USER_NAME:
            MEMBERNAME_VALIDATORS.add(newValidator);
            MEMBERNAME_VALIDATORS.get(0).setNext(newValidator);
            break;
        case ROOM_NAME:
            ROOM_NAME_VALIDATORS.add(newValidator);
            ROOM_NAME_VALIDATORS.get(0).setNext(newValidator);
            break;
        case MODE:
            MODE_VALIDATORS.add(newValidator);
            MODE_VALIDATORS.get(0).setNext(newValidator);
            break;
        case PASSWORD:
            PASSWORD_VALIDATORS.add(newValidator);
            PASSWORD_VALIDATORS.get(0).setNext(newValidator);
            break;
        default:
            throw new ValidatorNotListedException();
    }
}


public float getLng(){
    return lng;
}


public String getMember_name(){
    return member_name;
}


public void registerValidator(ICustomValidator newValidator,ValidationScopes scope){
    if (newValidator instanceof IStringValidator) {
        registerStringValidator((IStringValidator) newValidator, scope);
    } else if (newValidator instanceof IFloatValidator) {
        registerFloatValidator((IFloatValidator) newValidator, scope);
    } else {
        throw new InappropriateValidatorException();
    }
}


public void cleanValidator(){
    MEMBERNAME_VALIDATORS.clear();
    ROOM_NAME_VALIDATORS.clear();
    MODE_VALIDATORS.clear();
    PASSWORD_VALIDATORS.clear();
    LAT_VALIDATORS.clear();
    LNG_VALIDATORS.clear();
    MEMBERNAME_VALIDATORS.add(new EmptyStringValidator());
    ROOM_NAME_VALIDATORS.add(new EmptyStringValidator());
    MODE_VALIDATORS.add(new EmptyStringValidator());
    PASSWORD_VALIDATORS.add(new EmptyStringValidator());
    LAT_VALIDATORS.add(new EmptyFloatValidator());
    LNG_VALIDATORS.add(new EmptyFloatValidator());
}


public void registerFloatValidator(IFloatValidator newValidator,ValidationScopes scope){
    switch(scope) {
        case LATITUDE:
            LAT_VALIDATORS.add(newValidator);
            LAT_VALIDATORS.get(0).setNext(newValidator);
            break;
        case LONGITUDE:
            LNG_VALIDATORS.add(newValidator);
            LNG_VALIDATORS.get(0).setNext(newValidator);
            break;
        default:
            throw new ValidatorNotListedException();
    }
}


public String getRoom_name(){
    return room_name;
}


public String getPassword(){
    return password;
}


public boolean removeValidator(ValidationScopes scope,int i){
    if (i == 0) {
        return false;
    }
    switch(scope) {
        case USER_NAME:
            if (MEMBERNAME_VALIDATORS.size() >= i + 1) {
                MEMBERNAME_VALIDATORS.get(i - 1).replaceNext(MEMBERNAME_VALIDATORS.get(i).getNext());
                MEMBERNAME_VALIDATORS.remove(i);
                return true;
            }
            return false;
        case ROOM_NAME:
            if (ROOM_NAME_VALIDATORS.size() >= i + 1) {
                ROOM_NAME_VALIDATORS.get(i - 1).replaceNext(ROOM_NAME_VALIDATORS.get(i).getNext());
                ROOM_NAME_VALIDATORS.remove(i);
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
        case MODE:
            if (MODE_VALIDATORS.size() >= i + 1) {
                MODE_VALIDATORS.get(i - 1).replaceNext(MODE_VALIDATORS.get(i).getNext());
                MODE_VALIDATORS.remove(i);
                return true;
            }
            return false;
        case LATITUDE:
            if (LAT_VALIDATORS.size() >= i + 1) {
                LAT_VALIDATORS.get(i - 1).replaceNext(LAT_VALIDATORS.get(i).getNext());
                LAT_VALIDATORS.remove(i);
                return true;
            }
            return false;
        case LONGITUDE:
            if (LNG_VALIDATORS.size() >= i + 1) {
                LNG_VALIDATORS.get(i - 1).replaceNext(LNG_VALIDATORS.get(i).getNext());
                LNG_VALIDATORS.remove(i);
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
            for (ICustomValidator vLookUp : MEMBERNAME_VALIDATORS) {
                if (vLookUp.getName().equals("Empty")) {
                    continue;
                }
                i++;
                list.add(i + ": " + vLookUp.getName());
            }
            return list;
        case ROOM_NAME:
            for (ICustomValidator vLookUp : ROOM_NAME_VALIDATORS) {
                if (vLookUp.getName().equals("Empty")) {
                    continue;
                }
                i++;
                list.add(i + ": " + vLookUp.getName());
            }
            return list;
        case MODE:
            for (ICustomValidator vLookUp : MODE_VALIDATORS) {
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
        case LATITUDE:
            for (ICustomValidator vLookUp : LAT_VALIDATORS) {
                if (vLookUp.getName().equals("Empty")) {
                    continue;
                }
                i++;
                list.add(i + ": " + vLookUp.getName());
            }
            return list;
        case LONGITUDE:
            for (ICustomValidator vLookUp : LNG_VALIDATORS) {
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


public String getMode(){
    return mode;
}


@Override
public Pair<Boolean,ResponseEntity> validate(){
    Pair<Boolean, ResponseEntity> currentTest = MEMBERNAME_VALIDATORS.get(0).validate(member_name);
    if (!currentTest.getLeft()) {
        return currentTest;
    }
    currentTest = ROOM_NAME_VALIDATORS.get(0).validate(room_name);
    if (!currentTest.getLeft()) {
        return currentTest;
    }
    currentTest = MODE_VALIDATORS.get(0).validate(mode);
    if (!currentTest.getLeft()) {
        return currentTest;
    }
    currentTest = LAT_VALIDATORS.get(0).validate(lat);
    if (!currentTest.getLeft()) {
        return currentTest;
    }
    currentTest = LNG_VALIDATORS.get(0).validate(lng);
    if (!currentTest.getLeft()) {
        return currentTest;
    }
    currentTest = PASSWORD_VALIDATORS.get(0).validate(password);
    return currentTest;
}


}