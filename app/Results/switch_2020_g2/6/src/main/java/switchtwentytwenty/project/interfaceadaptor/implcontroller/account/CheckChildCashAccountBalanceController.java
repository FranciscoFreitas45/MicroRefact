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
import switchtwentytwenty.project.interfaceadaptor.icontroller.account.ICheckChildCashAccountBalanceController;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
@RestController
@AllArgsConstructor
@RequestMapping(value = "/api")
@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:3001", "http://localhost:3002" }, maxAge = 3600)
public class CheckChildCashAccountBalanceController implements ICheckChildCashAccountBalanceController{

@Autowired
 private  IAccountService accountService;


@GetMapping(value = "/balance/{parentId}/{childId}/{childAccountId}")
public ResponseEntity<Object> checkChildCashAccountBalance(String parentID,String childID,String cashAccountId){
    try {
        MoneyValue value = accountService.checkChildCashAccountBalance(parentID, childID, cashAccountId);
        double childCashAccountBalance = value.toDouble();
        Link linkToParent = linkTo(CheckChildCashAccountBalanceController.class).slash("users").slash("accounts").withRel(// mudar
        "balance");
        CashAccountBalanceOutDTO response = new CashAccountBalanceOutDTO(childCashAccountBalance);
        response.add(linkToParent);
        return new ResponseEntity<>(response, HttpStatus.OK);
    } catch (IllegalArgumentException | NullPointerException | InvalidDateException | InvalidPersonNameException | InvalidVATException | InvalidEmailException | ElementNotFoundException | InvalidRelationTypeException | IOException | AccountNotCreatedException | InstantiationException | ClassNotFoundException | NoSuchMethodException | InvocationTargetException | IllegalAccessException exception) {
        BusinessErrorMessage msg = new BusinessErrorMessage(exception.getMessage(), BusinessErrorMessage.GROUP_DESCRIPTION_NOT_FOUND);
        // httpstatus -> ver qual melhor value.
        return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
    }
}


}