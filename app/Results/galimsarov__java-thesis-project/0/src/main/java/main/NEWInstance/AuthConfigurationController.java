package main.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class AuthConfigurationController {

 private AuthConfiguration authconfiguration;


@GetMapping
("/getAuths")
public Map<String,Integer> getAuths(){
  return authconfiguration.getAuths();
}


@GetMapping
("/get")
public Object get(@RequestParam(name = "Object") Object Object){
  return authconfiguration.get(Object);
}


}