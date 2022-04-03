package switchtwentytwenty.project.interfaceadaptor.option;
 import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import switchtwentytwenty.project.applicationservice.appservice.implappservice.AuthorizationService;
import switchtwentytwenty.project.dto.outdto.OptionsOutDTO;
import switchtwentytwenty.project.exception.InvalidEmailException;
import switchtwentytwenty.project.exception.UserEmailNotFoundException;
import switchtwentytwenty.project.interfaceadaptor.implcontroller.account.CreateFamilyCashAccountController;
import switchtwentytwenty.project.interfaceadaptor.implcontroller.account.CreatePersonalCashAccountController;
import switchtwentytwenty.project.interfaceadaptor.implcontroller.transaction.GetFamilyLedgerMovementsController;
import switchtwentytwenty.project.interfaceadaptor.implcontroller.transaction.GetPersonLedgerMovementsController;
import javax.servlet.http.HttpServletRequest;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
@RestController
@AllArgsConstructor
@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:3001", "http://localhost:3002" }, maxAge = 3600)
public class AccountOptions {

@Autowired
 private AuthorizationService authorizationService;


@RequestMapping(value = "/accounts", method = RequestMethod.OPTIONS)
public ResponseEntity<Object> options(HttpServletRequest request){
    OptionsOutDTO optionsOutDTO = new OptionsOutDTO();
    String principal = request.getUserPrincipal().toString();
    String role = authorizationService.getRole(principal);
    try {
        String personID = authorizationService.getPersonIDOfUser(request.getUserPrincipal().getName()).toString();
        switch(role) {
            case "ROLE_SYSTEM_MANAGER":
                break;
            case "ROLE_ADMIN":
                try {
                    String familyID = authorizationService.getFamilyID(request.getUserPrincipal().getName());
                    Link family_cash_account = linkTo(methodOn(CreateFamilyCashAccountController.class).createFamilyCashAccount(familyID, null)).withRel("family_cash_account");
                    optionsOutDTO.add(family_cash_account);
                    Link person_cash_account = linkTo(methodOn(CreatePersonalCashAccountController.class).createPersonalCashAccount(personID, null)).withRel("person_cash_account");
                    optionsOutDTO.add(person_cash_account);
                    Link person_ledger = linkTo(methodOn(GetPersonLedgerMovementsController.class).getListOfPersonLedgerMovements(personID)).withRel("person_ledger");
                    optionsOutDTO.add(person_ledger);
                    Link family_ledger = linkTo(methodOn(GetFamilyLedgerMovementsController.class).getListOfFamilyLedgerMovements(familyID)).withRel("family_ledger");
                    optionsOutDTO.add(family_ledger);
                } catch (UserEmailNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            case "ROLE_USER":
                Link person_cash_account = linkTo(methodOn(CreatePersonalCashAccountController.class).createPersonalCashAccount(personID, null)).withRel("person_cash_account");
                optionsOutDTO.add(person_cash_account);
                Link person_ledger = linkTo(methodOn(GetPersonLedgerMovementsController.class).getListOfPersonLedgerMovements(personID)).withRel("person_ledger");
                optionsOutDTO.add(person_ledger);
                break;
        }
        return new ResponseEntity<>(optionsOutDTO, HttpStatus.OK);
    } catch (InvalidEmailException | UserEmailNotFoundException e) {
        e.printStackTrace();
        return new ResponseEntity<>(optionsOutDTO, HttpStatus.BAD_REQUEST);
    }
}


}