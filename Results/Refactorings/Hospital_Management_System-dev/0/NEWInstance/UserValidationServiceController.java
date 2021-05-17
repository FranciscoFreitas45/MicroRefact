import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class UserValidationServiceController {

 private UserValidationService uservalidationservice;


@GetMapping
("/setUser")
public UserValidationService setUser(@RequestParam(name = "user") User user){
  return uservalidationservice.setUser(user);
}


@GetMapping
("/validLicenseNumber")
public UserValidationService validLicenseNumber(){
  return uservalidationservice.validLicenseNumber();
}


@GetMapping
("/validSpeciality")
public UserValidationService validSpeciality(@RequestParam(name = "speciality") String speciality){
  return uservalidationservice.validSpeciality(speciality);
}


@GetMapping
("/isValid")
public boolean isValid(){
  return uservalidationservice.isValid();
}


@GetMapping
("/getErrorModelMap")
public ModelMap getErrorModelMap(){
  return uservalidationservice.getErrorModelMap();
}


@GetMapping
("/clear")
public UserValidationService clear(){
  return uservalidationservice.clear();
}


@GetMapping
("/validName")
public UserValidationService validName(){
  return uservalidationservice.validName();
}


@GetMapping
("/validPassword")
public UserValidationService validPassword(){
  return uservalidationservice.validPassword();
}


@GetMapping
("/samePassword")
public UserValidationService samePassword(@RequestParam(name = "passwordRetyped") String passwordRetyped){
  return uservalidationservice.samePassword(passwordRetyped);
}


}