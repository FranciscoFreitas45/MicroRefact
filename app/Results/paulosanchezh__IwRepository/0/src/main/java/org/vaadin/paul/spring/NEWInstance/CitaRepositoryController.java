package org.vaadin.paul.spring.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class CitaRepositoryController {

 private CitaRepository citarepository;


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return citarepository.save(Object);
}


@GetMapping
("/findByFechaAndSanitarioAndConfirmada")
public List<Cita> findByFechaAndSanitarioAndConfirmada(@RequestParam(name = "fechaCita") LocalDate fechaCita,@RequestParam(name = "sanitario") Sanitario sanitario,@RequestParam(name = "confirmada") boolean confirmada){
  return citarepository.findByFechaAndSanitarioAndConfirmada(fechaCita,sanitario,confirmada);
}


}