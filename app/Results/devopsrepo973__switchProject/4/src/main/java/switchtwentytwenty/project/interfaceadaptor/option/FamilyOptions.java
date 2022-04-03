package switchtwentytwenty.project.interfaceadaptor.option;
 import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import switchtwentytwenty.project.applicationservice.appservice.implappservice.AuthorizationService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@RestController
@AllArgsConstructor
@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:3001", "http://localhost:3002" }, maxAge = 3600)
public class FamilyOptions {

@Autowired
 private AuthorizationService authorizationService;


@RequestMapping(value = "/families", method = RequestMethod.OPTIONS)
public ResponseEntity<Object> options(HttpServletRequest request,HttpServletResponse response){
    String principal = request.getUserPrincipal().toString();
    String role = authorizationService.getRole(principal);
    switch(role) {
        case "ROLE_SYSTEM_MANAGER":
            response.setHeader("Allow", "POST,GET,OPTIONS");
            break;
        case "ROLE_ADMIN":
        case "ROLE_USER":
            response.setHeader("Allow", "OPTIONS");
            break;
    }
    return new ResponseEntity<>(HttpStatus.OK);
}


}