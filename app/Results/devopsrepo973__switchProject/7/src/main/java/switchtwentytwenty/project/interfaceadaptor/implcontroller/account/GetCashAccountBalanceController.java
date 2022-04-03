package switchtwentytwenty.project.interfaceadaptor.implcontroller.account;
 import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation;
import switchtwentytwenty.project.exception.BusinessErrorMessage;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.IAccountService;
import switchtwentytwenty.project.domain.share.MoneyValue;
import switchtwentytwenty.project.dto.outdto.CashAccountBalanceOutDTO;
import switchtwentytwenty.project.exception;
import switchtwentytwenty.project.interfaceadaptor.icontroller.account.IGetCashAccountBalanceController;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
@RestController
@AllArgsConstructor
@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:3001", "http://localhost:3002" }, maxAge = 3600)
public class GetCashAccountBalanceController implements IGetCashAccountBalanceController{

@Autowired
 private  IAccountService accountService;


@GetMapping("users/{personId}/accounts/{accountId}/balance")
public ResponseEntity<Object> getCashAccountBalance(String personId,String accountId){
    try {
        MoneyValue value = accountService.getCashAccountBalance(personId, accountId);
        double cashAccountBalance = value.toDouble();
        Link linkToPerson = linkTo(methodOn(GetCashAccountBalanceController.class).getCashAccountBalance(personId, accountId)).withRel("balance");
        CashAccountBalanceOutDTO response = new CashAccountBalanceOutDTO(cashAccountBalance);
        response.add(linkToPerson);
        return new ResponseEntity<>(response, HttpStatus.OK);
    } catch (IllegalArgumentException | NullPointerException | InvalidDateException | InvalidPersonNameException | InvalidVATException | InvalidEmailException | ElementNotFoundException | InvalidRelationTypeException | InvalidAccountOwner | IOException | AccountNotCreatedException | InstantiationException | ClassNotFoundException | NoSuchMethodException | InvocationTargetException | IllegalAccessException exception) {
        BusinessErrorMessage msg = new BusinessErrorMessage(exception.getMessage(), BusinessErrorMessage.GROUP_DESCRIPTION_NOT_FOUND);
        // httpstatus -> ver qual melhor value.
        return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
    }
}


}