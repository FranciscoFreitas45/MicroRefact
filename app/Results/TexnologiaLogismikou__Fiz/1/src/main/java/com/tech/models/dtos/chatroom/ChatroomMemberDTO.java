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
public class ChatroomMemberDTO extends BaseDTO{

 private  List<IStringValidator> USER_NAME_VALIDATORS;

 private  List<IStringValidator> PASSWORD_VALIDATORS;

 private  List<IStringValidator> ROOM_NAME_VALIDATORS;

 private  List<IStringValidator> ROOM_METHOD;

 private  String room_name;

 private  String member_name;

 private  String password;

 private  String method;


public String getRoom_name(){
    return room_name;
}


public String getPassword(){
    return password;
}


@Override
public String getDTOName(){
    return "ChatroomNewMemberDTO";
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
        case ROOM_NAME:
            ROOM_NAME_VALIDATORS.add(strVal);
            ROOM_NAME_VALIDATORS.get(0).setNext(strVal);
            break;
        case MODE:
            ROOM_METHOD.add(strVal);
            ROOM_METHOD.get(0).setNext(strVal);
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
        case ROOM_NAME:
            if (ROOM_NAME_VALIDATORS.size() >= i + 1) {
                ROOM_NAME_VALIDATORS.get(i - 1).replaceNext(ROOM_NAME_VALIDATORS.get(i).getNext());
                ROOM_NAME_VALIDATORS.remove(i);
                return true;
            }
            return false;
        case MODE:
            if (ROOM_METHOD.size() >= i + 1) {
                ROOM_METHOD.get(i - 1).replaceNext(ROOM_METHOD.get(i).getNext());
                ROOM_METHOD.remove(i);
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
            for (ICustomValidator vLookUp : ROOM_METHOD) {
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


public String getMethod(){
    return method;
}


public void cleanValidator(){
    USER_NAME_VALIDATORS.clear();
    PASSWORD_VALIDATORS.clear();
    ROOM_NAME_VALIDATORS.clear();
    ROOM_METHOD.clear();
    USER_NAME_VALIDATORS.add(new EmptyStringValidator());
    PASSWORD_VALIDATORS.add(new EmptyStringValidator());
    ROOM_NAME_VALIDATORS.add(new EmptyStringValidator());
    ROOM_METHOD.add(new EmptyStringValidator());
}


@Override
public Pair<Boolean,ResponseEntity> validate(){
    Pair<Boolean, ResponseEntity> currentTest = USER_NAME_VALIDATORS.get(0).validate(member_name);
    if (!currentTest.getLeft()) {
        return currentTest;
    }
    currentTest = PASSWORD_VALIDATORS.get(0).validate(password);
    if (!currentTest.getLeft()) {
        return currentTest;
    }
    currentTest = ROOM_NAME_VALIDATORS.get(0).validate(room_name);
    if (!currentTest.getLeft()) {
        return currentTest;
    }
    currentTest = ROOM_METHOD.get(0).validate(method);
    return currentTest;
}


}