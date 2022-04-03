package hei2017.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class UserDAOController {

 private UserDAO userdao;


@GetMapping
("/findByUserTasksId")
public Set<User> findByUserTasksId(@RequestParam(name = "id") Long id){
  return userdao.findByUserTasksId(id);
}


@GetMapping
("/findAll")
public Object findAll(@RequestParam(name = "Object") Object Object){
  return userdao.findAll(Object);
}


@GetMapping
("/findOne")
public Object findOne(@RequestParam(name = "Object") Object Object){
  return userdao.findOne(Object);
}


@GetMapping
("/findOneById")
public User findOneById(@RequestParam(name = "id") Long id){
  return userdao.findOneById(id);
}


@GetMapping
("/findOneByNomAndPrenom")
public User findOneByNomAndPrenom(@RequestParam(name = "nom") String nom,@RequestParam(name = "prenom") String prenom){
  return userdao.findOneByNomAndPrenom(nom,prenom);
}


@GetMapping
("/findOneByPseudo")
public User findOneByPseudo(@RequestParam(name = "pseudo") String pseudo){
  return userdao.findOneByPseudo(pseudo);
}


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return userdao.save(Object);
}


@GetMapping
("/count")
public long count(){
  return userdao.count();
}


@GetMapping
("/delete")
public Object delete(@RequestParam(name = "Object") Object Object){
  return userdao.delete(Object);
}


@GetMapping
("/exists")
public Object exists(@RequestParam(name = "Object") Object Object){
  return userdao.exists(Object);
}


@GetMapping
("/findOneByEmail")
public User findOneByEmail(@RequestParam(name = "email") String email){
  return userdao.findOneByEmail(email);
}


}