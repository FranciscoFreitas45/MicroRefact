package org.sdrc.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class StateManagerController {

 private StateManager statemanager;


@GetMapping
("/getError")
public List<Error> getError(){
  return statemanager.getError();
}


@GetMapping
("/isEmpty")
public Object isEmpty(@RequestParam(name = "Object") Object Object){
  return statemanager.isEmpty(Object);
}


@PutMapping
("/setError")
public void setError(@RequestParam(name = "errModels") List<Error> errModels){
statemanager.setError(errModels);
}


@GetMapping
("/getValue")
public Object getValue(@RequestParam(name = "key") String key){
  return statemanager.getValue(key);
}


}