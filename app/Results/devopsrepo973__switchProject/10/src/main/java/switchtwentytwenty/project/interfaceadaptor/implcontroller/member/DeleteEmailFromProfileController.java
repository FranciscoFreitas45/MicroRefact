package switchtwentytwenty.project.interfaceadaptor.implcontroller.member;
 import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.IPersonService;
import switchtwentytwenty.project.dto.indto.DeleteEmailInDto;
import switchtwentytwenty.project.dto.outdto.DeleteEmailOutDTO;
import switchtwentytwenty.project.exception;
import switchtwentytwenty.project.interfaceadaptor.icontroller.member.IDeleteEmailFromProfileController;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
@RestController
@AllArgsConstructor
@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:3001", "http://localhost:3002" }, maxAge = 3600)
public class DeleteEmailFromProfileController implements IDeleteEmailFromProfileController{

@Autowired
 private  IPersonService personService;


@DeleteMapping(value = "/users/{personId}/email")
public ResponseEntity<Object> deleteEmailFromProfile(String personId,DeleteEmailInDto info){
    try {
        personService.deleteEmailFromProfile(personId, info.getEmailToDelete());
        DeleteEmailOutDTO result = new DeleteEmailOutDTO(personId);
        Link linkToProfile = WebMvcLinkBuilder.linkTo(methodOn(ViewProfileController.class).getUserProfile(personId)).withRel("View User Profile");
        result.add(linkToProfile);
        return new ResponseEntity<>(result, HttpStatus.OK);
    } catch (InvalidEmailException | InvalidDateException | InvalidVATException | InvalidPersonNameException | ElementNotFoundException | IllegalArgumentException exception) {
        BusinessErrorMessage msg = new BusinessErrorMessage(exception.getMessage(), BusinessErrorMessage.GROUP_DESCRIPTION_NOT_FOUND);
        return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
    }
}


}