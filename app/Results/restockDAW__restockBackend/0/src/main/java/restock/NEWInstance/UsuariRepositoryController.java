package restock.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class UsuariRepositoryController {

 private UsuariRepository usuarirepository;


@GetMapping
("/findByUserAndOrganitzacioId")
public Usuari findByUserAndOrganitzacioId(@RequestParam(name = "user") String user,@RequestParam(name = "organitzacioId") Integer organitzacioId){
  return usuarirepository.findByUserAndOrganitzacioId(user,organitzacioId);
}


@GetMapping
("/findById")
public Usuari findById(@RequestParam(name = "usuariId") Integer usuariId){
  return usuarirepository.findById(usuariId);
}


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return usuarirepository.save(Object);
}


}