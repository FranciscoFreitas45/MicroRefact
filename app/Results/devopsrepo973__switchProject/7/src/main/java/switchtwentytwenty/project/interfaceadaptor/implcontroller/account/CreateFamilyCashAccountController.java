package switchtwentytwenty.project.interfaceadaptor.implcontroller.account;
 import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.IAccountService;
import switchtwentytwenty.project.dto.indto.FamilyCashAccountInDTO;
import switchtwentytwenty.project.dto.outdto.FamilyCashAccountOutDTO;
import switchtwentytwenty.project.exception;
import switchtwentytwenty.project.interfaceadaptor.icontroller.account.ICreateFamilyCashAccountController;
import java.io.IOException;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
@RestController
@AllArgsConstructor
@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:3001", "http://localhost:3002" }, maxAge = 3600)
public class CreateFamilyCashAccountController implements ICreateFamilyCashAccountController{

@Autowired
 private  IAccountService accountService;


@PostMapping(value = "/families/{familyID}/familyCashAccount")
public ResponseEntity<Object> createFamilyCashAccount(String familyID,FamilyCashAccountInDTO info){
    try {
        FamilyCashAccountOutDTO result = accountService.createFamilyCashAccount(familyID, info.getFamilyAdministratorID(), info.getInitialAmount(), info.getDesignation());
        Link linkToAccount = linkTo(methodOn(CreateFamilyCashAccountController.class).createFamilyCashAccount(familyID, info)).withRel("create cash account");
        result.add(linkToAccount);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    } catch (IllegalArgumentException | ElementNotFoundException | IOException | AccountNotCreatedException | InvalidEmailException | InvalidRelationTypeException | InvalidDateException | InvalidVATException | InvalidPersonNameException exception) {
        String errorMessage = "Error: " + exception.getMessage();
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }
}


}