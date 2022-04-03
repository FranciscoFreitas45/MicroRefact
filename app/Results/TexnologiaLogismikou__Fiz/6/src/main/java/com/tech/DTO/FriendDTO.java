package com.tech.DTO;
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
public class FriendDTO extends BaseDTO{

 private  List<IStringValidator> USERNAME_VALIDATORS;

 private  String username;

 private  String friendname;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://1";


@Override
public String getDTOName(){
    return "FriendDTO";
}


public String getFriendname(){
    return friendname;
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
        default:
            throw new ValidatorNotListedException();
    }
}


public String getUsername(){
    return username;
}


@Override
public Pair<Boolean,ResponseEntity> validate(){
    Pair<Boolean, ResponseEntity> currentTest;
    currentTest = USERNAME_VALIDATORS.get(0).validate(username);
    if (!currentTest.getLeft()) {
        return currentTest;
    }
    currentTest = USERNAME_VALIDATORS.get(0).validate(friendname);
    return currentTest;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/validate"))

;
Pair<Boolean,ResponseEntity> aux = restTemplate.getForObject(builder.toUriString(),Pair<Boolean,ResponseEntity>.class);
return aux;
}


}