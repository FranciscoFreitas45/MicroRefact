package ar.com.veterinaria.app.entities.helper.service.contract;
 import ar.com.veterinaria.app.entities.user.User;
public interface UserContractServiceHelper {


public boolean matchPaswordAndConfirmPassword(User user)
;

public boolean isValidLengthPasword(User user)
;

public boolean isValidPasword(User user)
;

public boolean isValidEmail(User user)
;

}