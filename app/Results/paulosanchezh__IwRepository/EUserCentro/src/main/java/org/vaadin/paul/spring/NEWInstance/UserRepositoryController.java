package org.vaadin.paul.spring.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class UserRepositoryController {

 private UserRepository userrepository;


@GetMapping
("/findByid")
public User findByid(@RequestParam(name = "i") int i){
  return userrepository.findByid(i);
}


@GetMapping
("/findByusername")
public User findByusername(@RequestParam(name = "username") String username){
  return userrepository.findByusername(username);
}


@GetMapping
("/findAll")
public List<User> findAll(){
  return userrepository.findAll();
}


@GetMapping
("/delete")
public Object delete(@RequestParam(name = "Object") Object Object){
  return userrepository.delete(Object);
}


@GetMapping
("/findByDniEdit")
public User findByDniEdit(@RequestParam(name = "dni") String dni,@RequestParam(name = "id") int id){
  return userrepository.findByDniEdit(dni,id);
}


@GetMapping
("/findByUsernameEdit")
public User findByUsernameEdit(@RequestParam(name = "username") String username,@RequestParam(name = "id") int id){
  return userrepository.findByUsernameEdit(username,id);
}


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return userrepository.save(Object);
}


@GetMapping
("/findBydni")
public User findBydni(@RequestParam(name = "dni") String dni){
  return userrepository.findBydni(dni);
}


}