package pl.szymanski.sharelibrary.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class BaseUserResponseController {

 private BaseUserResponse baseuserresponse;


@GetMapping
("/of")
public BaseUserResponse of(@RequestParam(name = "user") User user){
  return baseuserresponse.of(user);
}


}