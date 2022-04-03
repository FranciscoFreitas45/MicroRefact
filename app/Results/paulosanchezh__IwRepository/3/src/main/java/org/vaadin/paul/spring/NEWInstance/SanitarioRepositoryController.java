package org.vaadin.paul.spring.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SanitarioRepositoryController {

 private SanitarioRepository sanitariorepository;


@GetMapping
("/findByTrabajador")
public Sanitario findByTrabajador(@RequestParam(name = "trabajador") Trabajador trabajador){
  return sanitariorepository.findByTrabajador(trabajador);
}


@GetMapping
("/findByTrabajadorId")
public Sanitario findByTrabajadorId(@RequestParam(name = "id") int id){
  return sanitariorepository.findByTrabajadorId(id);
}


}