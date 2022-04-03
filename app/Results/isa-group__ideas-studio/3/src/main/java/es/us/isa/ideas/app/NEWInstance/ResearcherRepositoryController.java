package es.us.isa.ideas.app.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ResearcherRepositoryController {

 private ResearcherRepository researcherrepository;


@GetMapping
("/findByUserAccountId")
public Researcher findByUserAccountId(@RequestParam(name = "id") int id){
  return researcherrepository.findByUserAccountId(id);
}


@GetMapping
("/findByUsername")
public Researcher findByUsername(@RequestParam(name = "username") String username){
  return researcherrepository.findByUsername(username);
}


@GetMapping
("/findByEmail")
public Researcher findByEmail(@RequestParam(name = "email") String email){
  return researcherrepository.findByEmail(email);
}


@GetMapping
("/findAll")
public Object findAll(@RequestParam(name = "Object") Object Object){
  return researcherrepository.findAll(Object);
}


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return researcherrepository.save(Object);
}


@GetMapping
("/delete")
public Object delete(@RequestParam(name = "Object") Object Object){
  return researcherrepository.delete(Object);
}


}