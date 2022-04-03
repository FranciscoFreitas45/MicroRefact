package edu.nju.careerbridge.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class UserBasicMessageRepositoryController {

 private UserBasicMessageRepository userbasicmessagerepository;


@PutMapping
("/deleteByPhone")
public void deleteByPhone(@RequestParam(name = "phone") String phone){
userbasicmessagerepository.deleteByPhone(phone);
}


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return userbasicmessagerepository.save(Object);
}


@GetMapping
("/findByPhone")
public UserBasicMessage findByPhone(@RequestParam(name = "phone") String phone){
  return userbasicmessagerepository.findByPhone(phone);
}


}