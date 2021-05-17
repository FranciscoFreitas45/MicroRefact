import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class RegistrationServiceController {

 private RegistrationService registrationservice;


@PutMapping
("/encryptPassword")
public void encryptPassword(@RequestParam(name = "user") User user){
registrationservice.encryptPassword(user);
}


}