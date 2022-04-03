package com.tech.controllers;
 import com.tech.configurations.tools.Pair;
import com.tech.configurations.tools.Responses;
import com.tech.configurations.tools.ValidationScopes;
import com.tech.configurations.tools.customvalidators.elements.EmptyStringValidator;
import com.tech.configurations.tools.customvalidators.interfaces.ICustomValidator;
import com.tech.configurations.tools.customvalidators.interfaces.IStringValidator;
import com.tech.controllers.superclass.BaseController;
import com.tech.exceptions.customexceptions.InappropriateValidatorException;
import com.tech.exceptions.customexceptions.ValidatorNotListedException;
import com.tech.models.dtos.user.UserDTO;
import com.tech.models.entities.user.User;
import com.tech.models.entities.user.UserInfo;
import com.tech.services.interfaces.IUserInfoService;
import com.tech.services.interfaces.IUserService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.tech.DTO.ICustomValidator;
import com.tech.DTO.ICustomValidator;
@Controller
@RequestMapping("/user")
public class UserController extends BaseController{

@Autowired
 private IUserService service;

@Autowired
 private IUserInfoService infoService;

 private  List<IStringValidator> USER_NAME_VALIDATORS;


@RequestMapping(value = "/{username}/modify", method = RequestMethod.GET)
public HttpEntity<String> deactivateUser(String username,UserDTO userDTO){
    Pair<Boolean, ResponseEntity> response = userDTO.validate();
    if (!response.getLeft()) {
        return response.getRight();
    }
    if (!service.checkUsername(username)) {
        return new ResponseEntity<>(Responses.NOT_AVAILABLE.getData(), HttpStatus.NOT_FOUND);
    }
    Long userId = service.getUserByUsername(username).getId();
    User user = new User(userId, userDTO);
    UserInfo userInfo = new UserInfo(userId, userDTO);
    service.modifyUser(user);
    infoService.modifyUserInfo(userInfo);
    return new ResponseEntity<>(Responses.SUCCESS.getData(), HttpStatus.OK);
}


public void registerStringValidator(IStringValidator strVal,ValidationScopes scope){
    switch(scope) {
        case USER_NAME:
            USER_NAME_VALIDATORS.add(strVal);
            USER_NAME_VALIDATORS.get(0).setNext(strVal);
            break;
        default:
            throw new ValidatorNotListedException();
    }
}


@RequestMapping(value = "/{username}", method = RequestMethod.GET)
public HttpEntity<JSONObject> loadUserProfile(String username){
    JSONObject json = new JSONObject();
    Pair<Boolean, ResponseEntity> response = validate(username);
    if (!response.getLeft()) {
        json.put("response", response.getRight().getBody());
        return new ResponseEntity<>(json, HttpStatus.NOT_ACCEPTABLE);
    }
    if (!service.checkUsername(username)) {
        json.put("response", Responses.NOT_AVAILABLE.getData());
        return new ResponseEntity<>(json, HttpStatus.NOT_FOUND);
    }
    User user = service.getUserByUsername(username);
    UserInfo userInfo = infoService.getUserInfoByUserId(user.getId());
    json.put("username", user.getUsername());
    json.put("firstname", userInfo.getFirstName());
    json.put("last_name", userInfo.getLastName());
    json.put("profile_photo", userInfo.getProfilePhoto());
    json.put("email", userInfo.getEmail());
    json.put("birthday", userInfo.getBirthday());
    json.put("hometown", userInfo.getHometown());
    json.put("status", userInfo.getStatus());
    json.put("response", Responses.SUCCESS.getData());
    return new ResponseEntity<>(json, HttpStatus.OK);
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
    USER_NAME_VALIDATORS.clear();
    USER_NAME_VALIDATORS.add(new EmptyStringValidator());
}


public Pair<Boolean,ResponseEntity> validate(String username){
    return USER_NAME_VALIDATORS.get(0).validate(username);
}


}