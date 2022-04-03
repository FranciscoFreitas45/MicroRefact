package com.poseidon.user.validator;
 import com.poseidon.user.domain.UserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
public class UserValidator implements Validator{

 private  Logger logger;


@Override
public boolean supports(Class<?> classInstance){
    return classInstance.equals(UserVO.class);
}


@Override
public void validate(Object userVo,Errors errors){
    logger.info(" Inside the validate method");
    UserVO user = (UserVO) userVo;
    if (user.getName().trim().length() == 0) {
        errors.rejectValue("name", "error.required.user.name", "user Name is required");
    }
    if (user.getPassword().trim().length() == 0) {
        errors.rejectValue("password", "error.required.password", "Password is required");
    }
}


}