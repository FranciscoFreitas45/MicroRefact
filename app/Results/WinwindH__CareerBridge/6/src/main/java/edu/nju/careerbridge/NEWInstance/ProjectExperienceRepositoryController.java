package edu.nju.careerbridge.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ProjectExperienceRepositoryController {

 private ProjectExperienceRepository projectexperiencerepository;


@PutMapping
("/deleteByPhone")
public void deleteByPhone(@RequestParam(name = "phone") String phone){
projectexperiencerepository.deleteByPhone(phone);
}


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return projectexperiencerepository.save(Object);
}


@GetMapping
("/findByPhone")
public List<ProjectExperience> findByPhone(@RequestParam(name = "phone") String phone){
  return projectexperiencerepository.findByPhone(phone);
}


}