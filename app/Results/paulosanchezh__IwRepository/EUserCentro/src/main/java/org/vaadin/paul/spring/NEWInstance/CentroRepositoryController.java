package org.vaadin.paul.spring.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class CentroRepositoryController {

 private CentroRepository centrorepository;


@GetMapping
("/findAll")
public Object findAll(@RequestParam(name = "Object") Object Object){
  return centrorepository.findAll(Object);
}


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return centrorepository.save(Object);
}


@GetMapping
("/findByName")
public Centro findByName(@RequestParam(name = "nombre") String nombre,@RequestParam(name = "id") int id){
  return centrorepository.findByName(nombre,id);
}


@GetMapping
("/delete")
public Object delete(@RequestParam(name = "Object") Object Object){
  return centrorepository.delete(Object);
}


@GetMapping
("/findBynombre")
public Centro findBynombre(@RequestParam(name = "nombre") String nombre){
  return centrorepository.findBynombre(nombre);
}


@GetMapping
("/findByLocalidad")
public List<Centro> findByLocalidad(@RequestParam(name = "Localidad") Localidad Localidad){
  return centrorepository.findByLocalidad(Localidad);
}


}