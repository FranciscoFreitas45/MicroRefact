package org.vaadin.paul.spring.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class TrabajadorRepositoryController {

 private TrabajadorRepository trabajadorrepository;


@GetMapping
("/findByUser")
public Trabajador findByUser(@RequestParam(name = "usuario") User usuario){
  return trabajadorrepository.findByUser(usuario);
}


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return trabajadorrepository.save(Object);
}


@GetMapping
("/findByTrabajadoresNulos")
public List<Trabajador> findByTrabajadoresNulos(){
  return trabajadorrepository.findByTrabajadoresNulos();
}


}