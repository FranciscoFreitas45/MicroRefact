package switchtwentytwenty.project.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class AuthorizationServiceController {

 private AuthorizationService authorizationservice;


@GetMapping
("/accessFamilyCashAccountAuthorization")
public boolean accessFamilyCashAccountAuthorization(@RequestParam(name = "role") String role){
  return authorizationservice.accessFamilyCashAccountAuthorization(role);
}


@GetMapping
("/getPersonIDOfUser")
public Email getPersonIDOfUser(@RequestParam(name = "username") String username){
  return authorizationservice.getPersonIDOfUser(username);
}


}