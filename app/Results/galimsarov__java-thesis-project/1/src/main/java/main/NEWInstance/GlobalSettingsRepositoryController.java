package main.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class GlobalSettingsRepositoryController {

 private GlobalSettingsRepository globalsettingsrepository;


@GetMapping
("/multiUser")
public String multiUser(){
  return globalsettingsrepository.multiUser();
}


@GetMapping
("/postPremoderation")
public String postPremoderation(){
  return globalsettingsrepository.postPremoderation();
}


@GetMapping
("/equals")
public Object equals(@RequestParam(name = "Object") Object Object){
  return globalsettingsrepository.equals(Object);
}


}