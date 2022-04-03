package NEWInstance;
 import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class LoginServiceController {

 private LoginService loginservice;


@GetMapping
("/findBySchoolcodeAndPassword")
public MyUser findBySchoolcodeAndPassword(@RequestParam(name = "schoolcode") String schoolcode,@RequestParam(name = "password") String password){
  return loginservice.findBySchoolcodeAndPassword(schoolcode,password);
}


@GetMapping
("/getUserById")
public MyUser getUserById(@RequestParam(name = "id") Long id){
  return loginservice.getUserById(id);
}


}