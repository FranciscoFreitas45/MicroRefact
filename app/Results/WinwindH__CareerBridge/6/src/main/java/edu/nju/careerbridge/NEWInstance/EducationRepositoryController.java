package edu.nju.careerbridge.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class EducationRepositoryController {

 private EducationRepository educationrepository;


@PutMapping
("/deleteByPhone")
public void deleteByPhone(@RequestParam(name = "phone") String phone){
educationrepository.deleteByPhone(phone);
}


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return educationrepository.save(Object);
}


@GetMapping
("/findByPhone")
public Education findByPhone(@RequestParam(name = "phone") String phone){
  return educationrepository.findByPhone(phone);
}


}