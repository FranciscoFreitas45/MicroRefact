package switchtwentytwenty.project.interfaceadaptor.implcontroller.account;
 import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.IAccountService;
import switchtwentytwenty.project.dto.indto.CreateAccountInDTO;
import switchtwentytwenty.project.dto.outdto.AccountOutDTO;
import switchtwentytwenty.project.exception;
import switchtwentytwenty.project.interfaceadaptor.icontroller.account.IAddBankAccountController;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
@RestController
@AllArgsConstructor
@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:3001", "http://localhost:3002" }, maxAge = 3600)
public class AddBankAccountController implements IAddBankAccountController{

@Autowired
 private  IAccountService accountService;


@PostMapping(value = "/accounts/bank")
public ResponseEntity<Object> addBankAccount(CreateAccountInDTO info){
    try {
        AccountOutDTO result = accountService.addBankAccount(info.getDesignation(), info.getHolderID(), info.getAccountType());
        Link linkToViewBalance = WebMvcLinkBuilder.linkTo(methodOn(CheckAccountBalanceController.class).checkAccountBalance(info.getHolderID(), result.getAccountID())).withRel("view account balance");
        result.add(linkToViewBalance);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    } catch (InvalidEmailException | ClassNotFoundException | ElementNotFoundException | InstantiationException | IllegalArgumentException | NullPointerException | AccountNotCreatedException | InvalidDateException | InvalidVATException | InvalidPersonNameException | NoSuchMethodException | InvocationTargetException | IllegalAccessException | IOException exception) {
        String errorMessage = "Error: " + exception.getMessage();
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }
}


}