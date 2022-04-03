package ar.com.veterinaria.app.entities.helper.service;
 import org.springframework.stereotype.Service;
import ar.com.veterinaria.app.bo.validatorPattern.ValidatorPatternServiceHelper;
import ar.com.veterinaria.app.entities.helper.service.contract.UserContractServiceHelper;
import ar.com.veterinaria.app.entities.user.User;
@Service
public class UserServiceHelper implements UserContractServiceHelper{


@Override
public boolean matchPaswordAndConfirmPassword(User user){
    if (user.getPassword().equals(user.getPasswordConfirmation())) {
        return true;
    }
    return false;
}


@Override
public boolean isValidLengthPasword(User user){
    if (user.getPassword().length() <= 8) {
        return false;
    }
    return true;
}


@Override
public boolean isValidPasword(User user){
    if (ValidatorPatternServiceHelper.getPasswordPattern().matcher(user.getPassword()).matches()) {
        return true;
    }
    return false;
}


@Override
public boolean isValidEmail(User user){
    if (ValidatorPatternServiceHelper.getNamePattern().matcher(user.getEmail()).matches()) {
        return true;
    }
    return false;
}


}