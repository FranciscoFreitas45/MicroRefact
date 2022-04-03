package switchtwentytwenty.project.interfaceadaptor.implcontroller.family;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.IAuthorizationService;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.IFamilyService;
import switchtwentytwenty.project.dto.outdto.FamilyProfileOutDTO;
import switchtwentytwenty.project.exception.BusinessErrorMessage;
import switchtwentytwenty.project.exception.ElementNotFoundException;
import switchtwentytwenty.project.exception.InvalidEmailException;
import switchtwentytwenty.project.exception.InvalidRelationTypeException;
import switchtwentytwenty.project.interfaceadaptor.icontroller.family.IGetFamilyProfileController;
import switchtwentytwenty.project.interfaceadaptor.implcontroller.member.AddFamilyMemberController;
import switchtwentytwenty.project.interfaceadaptor.implcontroller.member.GetListOfFamilyMembersController;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import switchtwentytwenty.project.Interface.IAuthorizationService;
@RestController
@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:3001", "http://localhost:3002" }, maxAge = 3600)
public class GetFamilyProfileController implements IGetFamilyProfileController{

@Autowired
 private  IFamilyService familyService;

@Autowired
 private  IAuthorizationService authorizationService;


@GetMapping("/families/{familyID}")
public ResponseEntity<Object> getFamilyProfile(HttpServletRequest request,String familyID){
    try {
        FamilyProfileOutDTO familyProfileDTO = familyService.getFamilyProfile(familyID);
        Link view_members = WebMvcLinkBuilder.linkTo(methodOn(GetListOfFamilyMembersController.class).getListOfFamilyMembers(familyID)).withRel("members");
        familyProfileDTO.add(view_members);
        if (authorizationService.getRole(request.getUserPrincipal().toString()).equals("ROLE_ADMIN")) {
            Link new_member = WebMvcLinkBuilder.linkTo(methodOn(AddFamilyMemberController.class).addFamilyMember(null)).withRel("new_member");
            familyProfileDTO.add(new_member);
        }
        return new ResponseEntity<>(familyProfileDTO, HttpStatus.OK);
    } catch (IOException | ElementNotFoundException | InvalidEmailException | InvalidRelationTypeException e) {
        BusinessErrorMessage msg = new BusinessErrorMessage(e.getMessage(), BusinessErrorMessage.GROUP_DESCRIPTION_NOT_FOUND);
        return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
    }
}


}