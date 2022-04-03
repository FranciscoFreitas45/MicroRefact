package switchtwentytwenty.project.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class IAuthorizationServiceController {

 private IAuthorizationService iauthorizationservice;


@GetMapping
("/accessAccountAuthorization")
public boolean accessAccountAuthorization(@RequestParam(name = "username") String username,@RequestParam(name = "accountId") String accountId){
  return iauthorizationservice.accessAccountAuthorization(username,accountId);
}


@GetMapping
("/getPersonIDOfUser")
public Email getPersonIDOfUser(@RequestParam(name = "username") String username){
  return iauthorizationservice.getPersonIDOfUser(username);
}


@GetMapping
("/getRole")
public String getRole(@RequestParam(name = "role") String role){
  return iauthorizationservice.getRole(role);
}


@PutMapping
("/registerUser")
public void registerUser(@RequestParam(name = "signUpRequest") SignupDTO signUpRequest){
iauthorizationservice.registerUser(signUpRequest);
}


}