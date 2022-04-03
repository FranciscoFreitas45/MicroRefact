package switchtwentytwenty.project.interfaceadaptor.implcontroller.member;
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
import switchtwentytwenty.project.applicationservice.appservice.iappservice.IFamilyAndMemberService;
import switchtwentytwenty.project.domain.constant.Constants;
import switchtwentytwenty.project.dto.outdto.PersonOutDTO;
import switchtwentytwenty.project.dto.toservicedto.PersonDTO;
import switchtwentytwenty.project.dto.indto.PersonInDTO;
import switchtwentytwenty.project.dto.toservicedto.PersonDTOMapper;
import switchtwentytwenty.project.exception;
import switchtwentytwenty.project.interfaceadaptor.icontroller.member.IAddFamilyMemberController;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
@RestController
@AllArgsConstructor
@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:3001", "http://localhost:3002" }, maxAge = 3600)
public class AddFamilyMemberController implements IAddFamilyMemberController{

@Autowired
 private  IFamilyAndMemberService service;


@PostMapping(Constants.URI_USERS)
public ResponseEntity<Object> addFamilyMember(PersonInDTO info){
    PersonDTO personDTO = PersonDTOMapper.mapToDTO(info);
    try {
        PersonOutDTO result = service.addFamilyMember(personDTO);
        Link linkToViewProfile = linkTo(methodOn(ViewProfileController.class).getUserProfile(result.getMainEmail())).withRel("view profile");
        Link linkToPersonRelations = WebMvcLinkBuilder.linkTo(methodOn(ViewFamilyRelationsFromAPersonController.class).getFamilyRelationByPersonID(result.getMainEmail())).withRel("view my family relations");
        Link selfLink = linkTo(methodOn(AddFamilyMemberController.class).addFamilyMember(info)).withSelfRel();
        result.add(selfLink);
        result.add(linkToViewProfile);
        result.add(linkToPersonRelations);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    } catch (IllegalArgumentException | InvalidDateException | InvalidVATException | InvalidEmailException | PersonAlreadyInSystemException | ElementNotFoundException | InvalidPersonNameException | BusinessErrorMessage exception) {
        String errorMessage = "Error: " + exception.getMessage();
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }
}


}