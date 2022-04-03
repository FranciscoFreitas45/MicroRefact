package switchtwentytwenty.project.interfaceadaptor.implcontroller.account;
 import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.IAccountService;
import switchtwentytwenty.project.dto.indto.PersonalCashAccountInDTO;
import switchtwentytwenty.project.dto.outdto.PersonalCashAccountOutDTO;
import switchtwentytwenty.project.exception;
import switchtwentytwenty.project.interfaceadaptor.icontroller.account.ICreatePersonalCashAccountController;
import java.io.IOException;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
@RestController
@AllArgsConstructor
@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:3001", "http://localhost:3002" }, maxAge = 3600)
public class CreatePersonalCashAccountController implements ICreatePersonalCashAccountController{

@Autowired
 private  IAccountService accountService;


@PostMapping(value = "/users/{personId}/personalCashAccount")
public ResponseEntity<Object> createPersonalCashAccount(String personId,PersonalCashAccountInDTO info){
    try {
        PersonalCashAccountOutDTO result = accountService.createPersonalCashAccount(personId, info.getInitialAmount(), info.getDesignation());
        Link linkToAccount = linkTo(methodOn(CreatePersonalCashAccountController.class).createPersonalCashAccount(personId, info)).withRel("create cash account");
        result.add(linkToAccount);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    } catch (IllegalArgumentException | ElementNotFoundException | IOException | AccountNotCreatedException | InvalidEmailException | InvalidDateException | InvalidVATException | InvalidPersonNameException exception) {
        String errorMessage = "Error: " + exception.getMessage();
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }
}


}