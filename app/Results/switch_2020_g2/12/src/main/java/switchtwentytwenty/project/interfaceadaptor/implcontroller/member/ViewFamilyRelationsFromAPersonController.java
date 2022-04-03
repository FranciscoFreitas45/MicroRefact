package switchtwentytwenty.project.interfaceadaptor.implcontroller.member;
 import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import switchtwentytwenty.project.exception.BusinessErrorMessage;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.IFamilyAndMemberService;
import switchtwentytwenty.project.dto.outdto.ViewRelationOutDTO;
import switchtwentytwenty.project.exception;
import switchtwentytwenty.project.interfaceadaptor.icontroller.member.IViewFamilyRelationsFromAPerson;
import java.io.IOException;
import java.text.ParseException;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
@RestController
@AllArgsConstructor
@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:3001", "http://localhost:3002" }, maxAge = 3600)
public class ViewFamilyRelationsFromAPersonController implements IViewFamilyRelationsFromAPerson{

@Autowired
 private  IFamilyAndMemberService familyAndMemberService;


@GetMapping("/families/relations/{id}")
public ResponseEntity<Object> getFamilyRelationByPersonID(String personID){
    try {
        ViewRelationOutDTO familyRelationsFromAPerson = familyAndMemberService.getFamilyRelationByPersonID(personID);
        Link linkToRelations = linkTo(methodOn(ViewFamilyRelationsFromAPersonController.class).getFamilyRelationByPersonID(personID)).withRel("view family relations");
        Link linkToViewProfile = linkTo(methodOn(ViewProfileController.class).getUserProfile(personID)).withRel("view profile");
        familyRelationsFromAPerson.add(linkToRelations);
        familyRelationsFromAPerson.add(linkToViewProfile);
        return new ResponseEntity<>(familyRelationsFromAPerson, HttpStatus.OK);
    } catch (IllegalArgumentException | AccountNotCreatedException | InstantiationException | InvalidMovementTypeException | NullPointerException | InvalidEmailException | ParseException | ElementNotFoundException | InvalidDateException | InvalidVATException | InvalidPersonNameException | IOException | InvalidRelationTypeException exception) {
        BusinessErrorMessage msg = new BusinessErrorMessage(exception.getMessage(), BusinessErrorMessage.GROUP_DESCRIPTION_NOT_FOUND);
        return new ResponseEntity<>(msg, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}


}