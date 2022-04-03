package edu.nju.careerbridge.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SkillRepositoryController {

 private SkillRepository skillrepository;


@PutMapping
("/deleteByPhone")
public void deleteByPhone(@RequestParam(name = "phone") String phone){
skillrepository.deleteByPhone(phone);
}


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return skillrepository.save(Object);
}


@GetMapping
("/findByPhone")
public List<Skill> findByPhone(@RequestParam(name = "phone") String phone){
  return skillrepository.findByPhone(phone);
}


}