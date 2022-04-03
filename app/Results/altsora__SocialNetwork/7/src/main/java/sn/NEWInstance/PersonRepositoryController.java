package sn.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class PersonRepositoryController {

 private PersonRepository personrepository;


@GetMapping
("/findById")
public Object findById(@RequestParam(name = "Object") Object Object){
  return personrepository.findById(Object);
}


@GetMapping
("/existsById")
public Object existsById(@RequestParam(name = "Object") Object Object){
  return personrepository.existsById(Object);
}


@GetMapping
("/findByEmail")
public Optional<Person> findByEmail(@RequestParam(name = "email") String email){
  return personrepository.findByEmail(email);
}


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return personrepository.save(Object);
}


@GetMapping
("/saveAndFlush")
public Object saveAndFlush(@RequestParam(name = "Object") Object Object){
  return personrepository.saveAndFlush(Object);
}


@GetMapping
("/deleteById")
public Object deleteById(@RequestParam(name = "Object") Object Object){
  return personrepository.deleteById(Object);
}


@GetMapping
("/getTotalCountUsers")
public int getTotalCountUsers(){
  return personrepository.getTotalCountUsers();
}


@GetMapping
("/findAllById")
public Object findAllById(@RequestParam(name = "Object") Object Object){
  return personrepository.findAllById(Object);
}


@GetMapping
("/findFriends")
public List<Person> findFriends(@RequestParam(name = "id") long id,@RequestParam(name = "offset") int offset,@RequestParam(name = "itemPerPage") int itemPerPage,@RequestParam(name = "name") String name){
  return personrepository.findFriends(id,offset,itemPerPage,name);
}


@GetMapping
("/findRequests")
public List<Person> findRequests(@RequestParam(name = "id") long id,@RequestParam(name = "offset") int offset,@RequestParam(name = "itemPerPage") int itemPerPage,@RequestParam(name = "name") String name){
  return personrepository.findRequests(id,offset,itemPerPage,name);
}


@GetMapping
("/findRecommendedFriends")
public List<Person> findRecommendedFriends(@RequestParam(name = "id") long id,@RequestParam(name = "city") String city,@RequestParam(name = "offset") Integer offset,@RequestParam(name = "itemPerPage") int itemPerPage){
  return personrepository.findRecommendedFriends(id,city,offset,itemPerPage);
}


}