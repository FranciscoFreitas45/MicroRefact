package com.tech.models.dtos.chatroom;
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
import java.util.List;
import org.springframework.http.ResponseEntity;
public class ChatroomDeleteDTO extends BaseDTO{

 private  List<IStringValidator> USERNAME_VALIDATORS;

 private  List<IStringValidator> ROOM_NAME_VALIDATORS;

 private  List<IStringValidator> ROOM_PASSWORD;

 private  String creator_name;

 private  String room_name;

 private  String room_password;


public String getRoom_name(){
    return room_name;
}


public String getCreator_name(){
    return creator_name;
}


@Override
public String getDTOName(){
    return "ChatroomDeleteDTO";
}


public String getRoom_password(){
    return room_password;
}


public void registerStringValidator(IStringValidator newValidator,ValidationScopes scope){
    switch(scope) {
        case USER_NAME:
            USERNAME_VALIDATORS.add(newValidator);
            USERNAME_VALIDATORS.get(0).setNext(newValidator);
            break;
        case ROOM_NAME:
            ROOM_NAME_VALIDATORS.add(newValidator);
            ROOM_NAME_VALIDATORS.get(0).setNext(newValidator);
            break;
        case PASSWORD:
            ROOM_PASSWORD.add(newValidator);
            ROOM_PASSWORD.get(0).setNext(newValidator);
            break;
        default:
            throw new ValidatorNotListedException();
    }
}


public boolean removeValidator(ValidationScopes scope,int i){
    if (i == 0) {
        // I believe you had forgotten that - Arxa
        return false;
    }
    switch(scope) {
        case USER_NAME:
            if (USERNAME_VALIDATORS.size() >= i + 1) {
                USERNAME_VALIDATORS.get(i - 1).replaceNext(USERNAME_VALIDATORS.get(i).getNext());
                USERNAME_VALIDATORS.remove(i);
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
            if (ROOM_PASSWORD.size() >= i + 1) {
                ROOM_PASSWORD.get(i - 1).replaceNext(ROOM_PASSWORD.get(i).getNext());
                ROOM_PASSWORD.remove(i);
                return true;
            }
            return false;
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


public List<String> getValidatorList(ValidationScopes scope){
    List<String> list = new ArrayList<>();
    int i = 0;
    switch(scope) {
        case USER_NAME:
            for (ICustomValidator vLookUp : USERNAME_VALIDATORS) {
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
        case PASSWORD:
            for (ICustomValidator vLookUp : ROOM_PASSWORD) {
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


public void cleanValidator(){
    USERNAME_VALIDATORS.clear();
    ROOM_NAME_VALIDATORS.clear();
    ROOM_PASSWORD.clear();
    USERNAME_VALIDATORS.add(new EmptyStringValidator());
    ROOM_NAME_VALIDATORS.add(new EmptyStringValidator());
    ROOM_PASSWORD.add(new EmptyStringValidator());
}


@Override
public Pair<Boolean,ResponseEntity> validate(){
    Pair<Boolean, ResponseEntity> currentTest = USERNAME_VALIDATORS.get(0).validate(creator_name);
    if (!currentTest.getLeft()) {
        return currentTest;
    }
    currentTest = ROOM_NAME_VALIDATORS.get(0).validate(room_name);
    if (!currentTest.getLeft()) {
        return currentTest;
    }
    currentTest = ROOM_PASSWORD.get(0).validate(room_password);
    return currentTest;
}


}