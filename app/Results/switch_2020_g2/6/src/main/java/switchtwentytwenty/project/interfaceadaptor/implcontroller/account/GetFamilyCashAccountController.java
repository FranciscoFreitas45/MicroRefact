package switchtwentytwenty.project.interfaceadaptor.implcontroller.account;
 import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.IAccountService;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.IAuthorizationService;
import switchtwentytwenty.project.dto.outdto.FamilyCashAccountOutDTO;
import switchtwentytwenty.project.interfaceadaptor.icontroller.account.IGetFamilyCashAccountController;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import switchtwentytwenty.project.Interface.IAuthorizationService;
@RestController
@AllArgsConstructor
@CrossOrigin(origins = { "*" }, maxAge = 3600)
public class GetFamilyCashAccountController implements IGetFamilyCashAccountController{

@Autowired
 private  IAccountService accountService;

@Autowired
 private  IAuthorizationService authorizationService;


@GetMapping(value = "/family/account/")
public ResponseEntity<Object> getFamilyCashAccount(HttpServletRequest request){
    try {
        Principal principal = request.getUserPrincipal();
        String username = principal.getName();
        String role = authorizationService.getRole(principal.toString());
        FamilyCashAccountOutDTO familyCashAccount = accountService.getFamilyCashAccount(username, role);
        return new ResponseEntity<>(familyCashAccount, HttpStatus.OK);
    } catch (Exception exception) {
        String msg = exception.getMessage();
        return new ResponseEntity<>(msg, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}


}