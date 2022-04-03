package com.tech.controllers;
 import com.tech.configurations.tools;
import com.tech.configurations.tools.customvalidators.elements.EmptyStringValidator;
import com.tech.configurations.tools.customvalidators.interfaces.ICustomValidator;
import com.tech.configurations.tools.customvalidators.interfaces.IStringValidator;
import com.tech.controllers.methodcontainer.FileWorkAround;
import com.tech.controllers.superclass.BaseController;
import com.tech.exceptions.customexceptions.InappropriateValidatorException;
import com.tech.exceptions.customexceptions.ValidatorNotListedException;
import com.tech.models.entities.ImagesMod;
import com.tech.services.interfaces.IImagesService;
import com.tech.services.interfaces.IUserService;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.tech.Interface.IUserService;
import com.tech.DTO.ICustomValidator;
import com.tech.DTO.ICustomValidator;
@CrossOrigin(origins = Host.apache)
@RestController
@RequestMapping("/images")
public class ImagesController extends BaseController{

 private  FileWorkAround fileWorkAround;

@Autowired
 private IImagesService service;

@Autowired
 private IUserService userService;

 private  List<IStringValidator> USER_NAME_VALIDATORS;

public ImagesController() {
    fileWorkAround = new FileWorkAround();
}
public void setFileWorkAround(FileWorkAround fWA){
    fileWorkAround = fWA;
}


@RequestMapping(method = RequestMethod.POST)
public HttpEntity<String> loadImages(MultipartFile file,String name){
    Pair<Boolean, ResponseEntity> response = validate(name);
    if (!response.getLeft()) {
        return response.getRight();
    }
    if (!userService.checkUsername(name)) {
        return new ResponseEntity<>(Responses.NOT_AVAILABLE.getData(), HttpStatus.NOT_FOUND);
    }
    Long sm = userService.getUserByUsername(name).getId();
    if (!file.isEmpty()) {
        try {
            byte[] bytes = file.getBytes();
            ImagesMod img = new ImagesMod(sm);
            fileWorkAround.fileWorkAroundCall(img.getImagePath(), bytes, StandardOpenOption.CREATE);
            service.addImage(img);
            return new ResponseEntity<>(Responses.SUCCESS.getData(), HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(Responses.FILE_ERROR.getData(), HttpStatus.NOT_MODIFIED);
        }
    } else {
        return new ResponseEntity<>(Responses.FILE_WAS_EMPTY.getData(), HttpStatus.NO_CONTENT);
    }
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


@RequestMapping("/get/{arithName:^[1-9][0-9]+\\.}{extension}")
@ResponseBody
public byte[] handle(String arithName,String extension){
    if (extension.equalsIgnoreCase("jpg")) {
        Long num = Long.parseLong(arithName.substring(0, arithName.length() - 1));
        if (service.checkImagesByHashtag(num)) {
            String path = service.getImageByHashtag(num).getImagePath();
            return Files.readAllBytes(new File(path).toPath());
        } else {
            return Files.readAllBytes(new File(Attr.NO_IMAGE_FOUND.getData()).toPath());
        }
    }
    return null;
}


public void cleanValidator(){
    USER_NAME_VALIDATORS.clear();
    USER_NAME_VALIDATORS.add(new EmptyStringValidator());
}


public Pair<Boolean,ResponseEntity> validate(String username){
    return USER_NAME_VALIDATORS.get(0).validate(username);
}


}