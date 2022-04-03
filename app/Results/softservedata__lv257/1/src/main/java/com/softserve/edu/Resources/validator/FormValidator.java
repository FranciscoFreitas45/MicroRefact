package com.softserve.edu.Resources.validator;
 import com.softserve.edu.Resources.dto.UserDTO;
import com.softserve.edu.Resources.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
@Component
public class FormValidator implements Validator{

@Autowired
 private  UserService userService;


@Override
public boolean supports(Class<?> aClass){
    return UserDTO.class.isAssignableFrom(aClass);
}


@Override
public void validate(Object o,Errors errors){
    UserDTO userDTO = (UserDTO) o;
    if (!userDTO.getPassword().equals(userDTO.getConfirmPassword())) {
        errors.rejectValue("confirmPassword", "confirmPassword.notequal");
    }
}


}