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
public class ChatroomLocationUpdateDTO extends BaseDTO{

 private  List<IStringValidator> ROOM_NAME_VALIDATORS;

 private  List<IFloatValidator> LATITUDE_VALIDATORS;

 private  List<IFloatValidator> LONGITUDE_VALIDATORS;

 private  float lng;

 private  float lat;

 private  String room_name;

public ChatroomLocationUpdateDTO() {
}
public float getLat(){
    return lat;
}


public String getRoom_name(){
    return room_name;
}


@Override
public String getDTOName(){
    return "ChatroomLocationUpdateDTO";
}


public void registerStringValidator(IStringValidator strVal,ValidationScopes scope){
    switch(scope) {
        case ROOM_NAME:
            ROOM_NAME_VALIDATORS.add(strVal);
            ROOM_NAME_VALIDATORS.get(0).setNext(strVal);
            break;
        default:
            throw new ValidatorNotListedException();
    }
}


public float getLng(){
    return lng;
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
        case LATITUDE:
            if (LATITUDE_VALIDATORS.size() >= i + 1) {
                LATITUDE_VALIDATORS.get(i - 1).replaceNext(LATITUDE_VALIDATORS.get(i).getNext());
                LATITUDE_VALIDATORS.remove(i);
                return true;
            }
            return false;
        case LONGITUDE:
            if (LONGITUDE_VALIDATORS.size() >= i + 1) {
                LONGITUDE_VALIDATORS.get(i - 1).replaceNext(LONGITUDE_VALIDATORS.get(i).getNext());
                LONGITUDE_VALIDATORS.remove(i);
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
    } else if (newValidator instanceof IFloatValidator) {
        registerFloatValidator((IFloatValidator) newValidator, scope);
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
        case LATITUDE:
            for (ICustomValidator vLookUp : LATITUDE_VALIDATORS) {
                if (vLookUp.getName().equals("Empty")) {
                    continue;
                }
                i++;
                list.add(i + ": " + vLookUp.getName());
            }
            return list;
        case LONGITUDE:
            for (ICustomValidator vLookUp : LONGITUDE_VALIDATORS) {
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
    LATITUDE_VALIDATORS.clear();
    LONGITUDE_VALIDATORS.clear();
    ROOM_NAME_VALIDATORS.add(new EmptyStringValidator());
    LATITUDE_VALIDATORS.add(new EmptyFloatValidator());
    LONGITUDE_VALIDATORS.add(new EmptyFloatValidator());
}


@Override
public Pair<Boolean,ResponseEntity> validate(){
    Pair<Boolean, ResponseEntity> currentTest = ROOM_NAME_VALIDATORS.get(0).validate(room_name);
    if (!currentTest.getLeft()) {
        return currentTest;
    }
    currentTest = LONGITUDE_VALIDATORS.get(0).validate(lng);
    if (!currentTest.getLeft()) {
        return currentTest;
    }
    currentTest = LATITUDE_VALIDATORS.get(0).validate(lat);
    return currentTest;
}


public void registerFloatValidator(IFloatValidator strVal,ValidationScopes scope){
    switch(scope) {
        case LATITUDE:
            LATITUDE_VALIDATORS.add(strVal);
            LATITUDE_VALIDATORS.get(0).setNext(strVal);
            break;
        case LONGITUDE:
            LONGITUDE_VALIDATORS.add(strVal);
            LONGITUDE_VALIDATORS.get(0).setNext(strVal);
            break;
        default:
            throw new ValidatorNotListedException();
    }
}


}