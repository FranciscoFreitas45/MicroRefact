package switchtwentytwenty.project.interfaceadaptor.implcontroller.account;
 import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.IAccountService;
import switchtwentytwenty.project.dto.outdto.PersonalCashAccountOutDTO;
import switchtwentytwenty.project.interfaceadaptor.icontroller.account.IGetListOfAccountsController;
import java.util.List;
@RestController
@AllArgsConstructor
@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:3001", "http://localhost:3002" }, maxAge = 3600)
public class GetListOfAccountsController implements IGetListOfAccountsController{

@Autowired
 private  IAccountService accountService;


@GetMapping(value = "/users/{personId}/accounts")
public ResponseEntity<Object> getListOfAccountsController(String personId){
    try {
        List<PersonalCashAccountOutDTO> accountList = accountService.getListOfAccounts(personId);
        return new ResponseEntity<>(accountList, HttpStatus.OK);
    } catch (Exception exception) {
        String msg = exception.getMessage();
        return new ResponseEntity<>(msg, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}


}