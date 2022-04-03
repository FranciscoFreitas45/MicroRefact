package org.vaadin.paul.spring.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class EspecialidadRepositoryController {

 private EspecialidadRepository especialidadrepository;


@GetMapping
("/findAll")
public List<Especialidad> findAll(){
  return especialidadrepository.findAll();
}


@GetMapping
("/countByEspecialidad")
public int countByEspecialidad(@RequestParam(name = "id") int id){
  return especialidadrepository.countByEspecialidad(id);
}


@GetMapping
("/especialidadesQueNoTengaEseCentro")
public List<Especialidad> especialidadesQueNoTengaEseCentro(@RequestParam(name = "id") int id){
  return especialidadrepository.especialidadesQueNoTengaEseCentro(id);
}


}