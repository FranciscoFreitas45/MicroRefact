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
public class ChatroomWhitelistDTO extends BaseDTO{

 private  List<IStringValidator> ROOM_NAME_VALIDATORS;

 private  List<IStringValidator> MEMBER_NAME_VALIDATORS;

 private  List<IStringValidator> ROOM_MODE_VALIDATORS;

 private  String room_name;

 private  String member_name;

 private  String mode;


public String getRoom_name(){
    return room_name;
}


@Override
public String getDTOName(){
    return "ChatroomWhitelistDTO";
}


public void registerStringValidator(IStringValidator strVal,ValidationScopes scope){
    switch(scope) {
        case ROOM_NAME:
            ROOM_NAME_VALIDATORS.add(strVal);
            ROOM_NAME_VALIDATORS.get(0).setNext(strVal);
            break;
        case MODE:
            ROOM_MODE_VALIDATORS.add(strVal);
            ROOM_MODE_VALIDATORS.get(0).setNext(strVal);
            break;
        case USER_NAME:
            MEMBER_NAME_VALIDATORS.add(strVal);
            MEMBER_NAME_VALIDATORS.get(0).setNext(strVal);
            break;
        default:
            throw new ValidatorNotListedException();
    }
}


public boolean removeValidator(ValidationScopes scope,int i){
    if (i == 0) {
        return false;
    }
    switch(scope) {
        case ROOM_NAME:
            if (ROOM_NAME_VALIDATORS.size() >= i + 1) {
                ROOM_NAME_VALIDATORS.get(i - 1).replaceNext(ROOM_NAME_VALIDATORS.get(i).getNext());
                ROOM_NAME_VALIDATORS.remove(i);
                return true;
            }
            return false;
        case MODE:
            if (ROOM_MODE_VALIDATORS.size() >= i + 1) {
                ROOM_MODE_VALIDATORS.get(i - 1).replaceNext(ROOM_MODE_VALIDATORS.get(i).getNext());
                ROOM_MODE_VALIDATORS.remove(i);
                return true;
            }
            return false;
        case USER_NAME:
            if (MEMBER_NAME_VALIDATORS.size() >= i + 1) {
                MEMBER_NAME_VALIDATORS.get(i - 1).replaceNext(MEMBER_NAME_VALIDATORS.get(i).getNext());
                MEMBER_NAME_VALIDATORS.remove(i);
                return true;
            }
            return false;
        default:
            throw new ValidatorNotListedException();
    }
}


public String getMember_name(){
    return member_name;
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
            for (ICustomValidator vLookUp : ROOM_MODE_VALIDATORS) {
                if (vLookUp.getName().equals("Empty")) {
                    continue;
                }
                i++;
                list.add(i + ": " + vLookUp.getName());
            }
            return list;
        case USER_NAME:
            for (ICustomValidator vLookUp : MEMBER_NAME_VALIDATORS) {
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


public void cleanValidator(){
    ROOM_NAME_VALIDATORS.clear();
    MEMBER_NAME_VALIDATORS.clear();
    ROOM_MODE_VALIDATORS.clear();
    MEMBER_NAME_VALIDATORS.add(new EmptyStringValidator());
    ROOM_MODE_VALIDATORS.add(new EmptyStringValidator());
    ROOM_NAME_VALIDATORS.add(new EmptyStringValidator());
}


@Override
public Pair<Boolean,ResponseEntity> validate(){
    Pair<Boolean, ResponseEntity> currentTest = MEMBER_NAME_VALIDATORS.get(0).validate(member_name);
    if (!currentTest.getLeft()) {
        return currentTest;
    }
    currentTest = ROOM_MODE_VALIDATORS.get(0).validate(mode);
    if (!currentTest.getLeft()) {
        return currentTest;
    }
    currentTest = ROOM_NAME_VALIDATORS.get(0).validate(room_name);
    return currentTest;
}


}