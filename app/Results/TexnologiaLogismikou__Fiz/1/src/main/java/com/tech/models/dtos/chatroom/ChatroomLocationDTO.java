package com.tech.models.dtos.chatroom;
 import com.tech.configurations.tools.Pair;
import com.tech.configurations.tools.ValidationScopes;
import com.tech.configurations.tools.customvalidators.elements.EmptyFloatValidator;
import com.tech.configurations.tools.customvalidators.elements.EmptyStringValidator;
import com.tech.configurations.tools.customvalidators.interfaces.ICustomValidator;
import com.tech.configurations.tools.customvalidators.interfaces.IFloatValidator;
import com.tech.configurations.tools.customvalidators.interfaces.INumberValidator;
import com.tech.configurations.tools.customvalidators.interfaces.IStringValidator;
import com.tech.exceptions.customexceptions.InappropriateValidatorException;
import com.tech.exceptions.customexceptions.ValidatorNotListedException;
import com.tech.models.dtos.superclass.BaseDTO;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.http.ResponseEntity;
public class ChatroomLocationDTO extends BaseDTO{

 private  List<IFloatValidator> LATITUDE_VALIDATORS;

 private  List<IFloatValidator> LONGITUDE_VALIDATORS;

 private  float lng;

 private  float lat;

public ChatroomLocationDTO() {
}
public float getLat(){
    return lat;
}


@Override
public String getDTOName(){
    return "ChatroomNewMemberDTO";
}


public float getLng(){
    return lng;
}


public boolean removeValidator(ValidationScopes scope,int i){
    if (i == 0) {
        return false;
    }
    switch(scope) {
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
    if (newValidator instanceof IFloatValidator) {
        registerFloatValidator((IFloatValidator) newValidator, scope);
    } else {
        throw new InappropriateValidatorException();
    }
}


public List<String> getValidatorList(ValidationScopes scope){
    List<String> list = new ArrayList<>();
    int i = 0;
    switch(scope) {
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
    LATITUDE_VALIDATORS.clear();
    LONGITUDE_VALIDATORS.clear();
    LATITUDE_VALIDATORS.add(new EmptyFloatValidator());
    LONGITUDE_VALIDATORS.add(new EmptyFloatValidator());
}


@Override
public Pair<Boolean,ResponseEntity> validate(){
    Pair<Boolean, ResponseEntity> currentTest = LONGITUDE_VALIDATORS.get(0).validate(lng);
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