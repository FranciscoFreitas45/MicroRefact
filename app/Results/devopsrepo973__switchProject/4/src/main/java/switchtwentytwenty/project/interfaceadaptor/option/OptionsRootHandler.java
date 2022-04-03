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
import switchtwentytwenty.project.applicationservice.appservice.iappservice.IAuthorizationService;
import switchtwentytwenty.project.dto.outdto.OptionsOutDTO;
import switchtwentytwenty.project.exception.InvalidEmailException;
import switchtwentytwenty.project.exception.UserEmailNotFoundException;
import switchtwentytwenty.project.interfaceadaptor.implcontroller.family.GetFamilyProfileController;
import switchtwentytwenty.project.interfaceadaptor.implcontroller.member.ViewProfileController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
@RestController
@AllArgsConstructor
@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:3001", "http://localhost:3002" }, maxAge = 3600)
public class OptionsRootHandler {

@Autowired
 private IAuthorizationService authorizationService;


@RequestMapping(value = "/", method = RequestMethod.OPTIONS)
public ResponseEntity<Object> options(HttpServletRequest request,HttpServletResponse response){
    // String jwt = request.getHeader("Authorization");
    OptionsOutDTO optionsOutDTO = new OptionsOutDTO();
    List<String> links = new ArrayList<>();
    String principal = request.getUserPrincipal().toString();
    String otherOtherPart = authorizationService.getRole(principal);
    switch(otherOtherPart) {
        case "ROLE_SYSTEM_MANAGER":
            Link families = linkTo(methodOn(FamilyOptions.class).options(request, response)).withRel("families");
            optionsOutDTO.add(families);
            Link categories = linkTo(methodOn(CategoryOptions.class).options(request, response)).withRel("categories");
            optionsOutDTO.add(categories);
            links.add("families");
            links.add("categories");
            break;
        case "ROLE_USER":
        case "ROLE_ADMIN":
            try {
                String familyID = authorizationService.getFamilyID(request.getUserPrincipal().getName());
                String personID = authorizationService.getPersonIDOfUser(request.getUserPrincipal().getName()).toString();
                Link family = linkTo(methodOn(GetFamilyProfileController.class).getFamilyProfile(request, familyID)).withRel("family");
                optionsOutDTO.add(family);
                Link user_profile = linkTo(methodOn(ViewProfileController.class).getUserProfile(personID)).withRel("user_profile");
                optionsOutDTO.add(user_profile);
                Link account = linkTo(methodOn(AccountOptions.class).options(request)).withRel("account");
                optionsOutDTO.add(account);
                break;
            } catch (UserEmailNotFoundException | InvalidEmailException e) {
                e.printStackTrace();
                return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
            }
    }
    optionsOutDTO.setAllow(links);
    return new ResponseEntity<>(optionsOutDTO, HttpStatus.OK);
}


}