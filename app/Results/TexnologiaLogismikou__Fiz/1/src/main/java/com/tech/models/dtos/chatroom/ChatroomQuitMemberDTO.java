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
public class ChatroomQuitMemberDTO extends BaseDTO{

 private  List<IStringValidator> ROOM_NAME_VALIDATORS;

 private  List<IStringValidator> USER_NAME_VALIDATORS;

 private  String room_name;

 private  String user_name;

public ChatroomQuitMemberDTO() {
}
public String getRoom_name(){
    return room_name;
}


@Override
public String getDTOName(){
    return "ChatroomQuitMemberDTO";
}


public void registerStringValidator(IStringValidator strVal,ValidationScopes scope){
    switch(scope) {
        case ROOM_NAME:
            ROOM_NAME_VALIDATORS.add(strVal);
            ROOM_NAME_VALIDATORS.get(0).setNext(strVal);
            break;
        case USER_NAME:
            USER_NAME_VALIDATORS.add(strVal);
            USER_NAME_VALIDATORS.get(0).setNext(strVal);
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
        case USER_NAME:
            if (USER_NAME_VALIDATORS.size() >= i + 1) {
                USER_NAME_VALIDATORS.get(i - 1).replaceNext(USER_NAME_VALIDATORS.get(i).getNext());
                USER_NAME_VALIDATORS.remove(i);
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
        case ROOM_NAME:
            for (ICustomValidator vLookUp : ROOM_NAME_VALIDATORS) {
                if (vLookUp.getName().equals("Empty")) {
                    continue;
                }
                i++;
                list.add(i + ": " + vLookUp.getName());
            }
            return list;
        case USER_NAME:
            for (ICustomValidator vLookUp : USER_NAME_VALIDATORS) {
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
    ROOM_NAME_VALIDATORS.clear();
    USER_NAME_VALIDATORS.clear();
    ROOM_NAME_VALIDATORS.add(new EmptyStringValidator());
    USER_NAME_VALIDATORS.add(new EmptyStringValidator());
}


public String getUser_name(){
    return user_name;
}


@Override
public Pair<Boolean,ResponseEntity> validate(){
    Pair<Boolean, ResponseEntity> currentTest;
    currentTest = ROOM_NAME_VALIDATORS.get(0).validate(room_name);
    if (!currentTest.getLeft()) {
        return currentTest;
    }
    currentTest = USER_NAME_VALIDATORS.get(0).validate(user_name);
    return currentTest;
}


}