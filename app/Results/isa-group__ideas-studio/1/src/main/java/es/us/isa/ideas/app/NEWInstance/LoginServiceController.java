package es.us.isa.ideas.app.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class LoginServiceController {

 private LoginService loginservice;


@GetMapping
("/getPrincipal")
public UserAccount getPrincipal(){
  return loginservice.getPrincipal();
}


}