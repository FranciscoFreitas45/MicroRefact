package com.tech.DTO;
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

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://1";

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


public Long getLng(){
    return lng;
}


public String getMember_name(){
    return member_name;
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
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/validate"))

;
Pair<Boolean,ResponseEntity> aux = restTemplate.getForObject(builder.toUriString(),Pair<Boolean,ResponseEntity>.class);
return aux;
}


}