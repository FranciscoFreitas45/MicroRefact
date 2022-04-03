package com.tech.models.dtos;
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
public class MessageHistoryRequestDTO extends BaseDTO{

 private  List<IStringValidator> ROOM_NAME_VALIDATORS;

 private  List<IStringValidator> MEMBER_NAME_VALIDATORS;

 private  List<IFloatValidator> LAT_VALIDATORS;

 private  List<IFloatValidator> LNG_VALIDATORS;

 private  String room_name;

 private  String member_name;

 private  Long lat;

 private  Long lng;

public MessageHistoryRequestDTO() {
}
public Long getLat(){
    return lat;
}


public String getRoom_name(){
    return room_name;
}


@Override
public String getDTOName(){
    return "MessageHistoryRequestDTO";
}


public void registerStringValidator(IStringValidator strVal,ValidationScopes scope){
    switch(scope) {
        case ROOM_NAME:
            ROOM_NAME_VALIDATORS.add(strVal);
            ROOM_NAME_VALIDATORS.get(0).setNext(strVal);
            break;
        case USER_NAME:
            MEMBER_NAME_VALIDATORS.add(strVal);
            MEMBER_NAME_VALIDATORS.get(0).setNext(strVal);
            break;
        default:
            throw new ValidatorNotListedException();
    }
}


public Long getLng(){
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
        case USER_NAME:
            if (MEMBER_NAME_VALIDATORS.size() >= i + 1) {
                MEMBER_NAME_VALIDATORS.get(i - 1).replaceNext(MEMBER_NAME_VALIDATORS.get(i).getNext());
                MEMBER_NAME_VALIDATORS.remove(i);
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
            for (ICustomValidator vLookUp : MEMBER_NAME_VALIDATORS) {
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


public void cleanValidator(){
    // adeiazzei ti lista kai bazei to prwto stoixeio
    {
        ROOM_NAME_VALIDATORS.clear();
        ROOM_NAME_VALIDATORS.add(new EmptyStringValidator());
        MEMBER_NAME_VALIDATORS.clear();
        MEMBER_NAME_VALIDATORS.add(new EmptyStringValidator());
        LAT_VALIDATORS.clear();
        LAT_VALIDATORS.add(new EmptyFloatValidator());
        LNG_VALIDATORS.clear();
        LNG_VALIDATORS.add(new EmptyFloatValidator());
    }
}


@Override
public Pair<Boolean,ResponseEntity> validate(){
    Pair<Boolean, ResponseEntity> currentTest;
    currentTest = ROOM_NAME_VALIDATORS.get(0).validate(room_name);
    if (!currentTest.getLeft()) {
        return currentTest;
    }
    currentTest = MEMBER_NAME_VALIDATORS.get(0).validate(member_name);
    if (!currentTest.getLeft()) {
        return currentTest;
    }
    currentTest = LAT_VALIDATORS.get(0).validate(lat);
    if (!currentTest.getLeft()) {
        return currentTest;
    }
    currentTest = LNG_VALIDATORS.get(0).validate(lng);
    return currentTest;
}


public void registerFloatValidator(IFloatValidator strVal,ValidationScopes scope){
    switch(scope) {
        case LATITUDE:
            LAT_VALIDATORS.add(strVal);
            LAT_VALIDATORS.get(0).setNext(strVal);
            break;
        case LONGITUDE:
            LNG_VALIDATORS.add(strVal);
            LNG_VALIDATORS.get(0).setNext(strVal);
            break;
        default:
            throw new ValidatorNotListedException();
    }
}


}