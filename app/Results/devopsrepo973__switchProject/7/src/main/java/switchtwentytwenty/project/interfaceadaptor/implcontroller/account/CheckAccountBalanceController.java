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
import switchtwentytwenty.project.domain.share.MoneyValue;
import switchtwentytwenty.project.exception;
import switchtwentytwenty.project.interfaceadaptor.icontroller.account.ICheckAccountBalanceController;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import switchtwentytwenty.project.DTO.MoneyValue;
@RestController
@AllArgsConstructor
@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:3001", "http://localhost:3002" }, maxAge = 3600)
public class CheckAccountBalanceController implements ICheckAccountBalanceController{

@Autowired
 private  IAccountService service;


@GetMapping("members/{holderID}/accounts/{accountID}/balance")
public ResponseEntity<Object> checkAccountBalance(String holderID,String accountID){
    try {
        MoneyValue balance = service.checkAccountBalance(accountID, holderID);
        return new ResponseEntity<>(balance.toString(), HttpStatus.OK);
    } catch (InvalidEmailException | ElementNotFoundException | InvalidAccountOwner | AccountNotCreatedException | InvalidDateException | InvalidVATException | InvalidPersonNameException | InstantiationException | ClassNotFoundException | NoSuchMethodException | InvocationTargetException | IllegalAccessException | IOException exception) {
        String errorMessage = "Error: " + exception.getMessage();
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }
}


}