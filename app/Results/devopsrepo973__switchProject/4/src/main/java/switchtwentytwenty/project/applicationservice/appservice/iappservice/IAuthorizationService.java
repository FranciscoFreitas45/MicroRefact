package switchtwentytwenty.project.applicationservice.appservice.iappservice;
 import switchtwentytwenty.project.autentication.SignupDTO;
import switchtwentytwenty.project.domain.share.id.Email;
import switchtwentytwenty.project.exception;
public interface IAuthorizationService {


public boolean accessAccountAuthorization(String username,String accountId)
;

public Email getPersonIDOfUser(String username)
;

public void registerUser(SignupDTO signUpRequest)
;

public String getRole(String role)
;

public boolean accessFamilyCashAccountAuthorization(String role)
;

public String getFamilyID(String username)
;

}