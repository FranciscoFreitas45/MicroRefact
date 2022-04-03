package switchtwentytwenty.project.interfaceadaptor.implcontroller.member;
 import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import switchtwentytwenty.project.exception.BusinessErrorMessage;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.IPersonService;
import switchtwentytwenty.project.dto.outdto.UserProfileOutDTO;
import switchtwentytwenty.project.exception;
import switchtwentytwenty.project.interfaceadaptor.icontroller.member.IViewProfileController;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
@RestController
@AllArgsConstructor
@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:3001", "http://localhost:3002" }, maxAge = 3600)
public class ViewProfileController implements IViewProfileController{

@Autowired
 private  IPersonService personService;


@GetMapping("/users/{id}")
public ResponseEntity<Object> getUserProfile(String personId){
    try {
        UserProfileOutDTO profile = personService.getUserProfile(personId);
        Link linkToUsers = linkTo(methodOn(ViewProfileController.class).getUserProfile(personId)).withRel("view profile");
        Link linkToRelations = WebMvcLinkBuilder.linkTo(methodOn(ViewFamilyRelationsFromAPersonController.class).getFamilyRelationByPersonID(personId)).withRel("view family relations");
        profile.add(linkToUsers);
        profile.add(linkToRelations);
        return new ResponseEntity<>(profile, HttpStatus.OK);
    } catch (InvalidEmailException | InvalidDateException | InvalidPersonNameException | InvalidVATException | ElementNotFoundException exception) {
        BusinessErrorMessage msg = new BusinessErrorMessage(exception.getMessage(), BusinessErrorMessage.GROUP_DESCRIPTION_NOT_FOUND);
        return new ResponseEntity<>(msg, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}


}