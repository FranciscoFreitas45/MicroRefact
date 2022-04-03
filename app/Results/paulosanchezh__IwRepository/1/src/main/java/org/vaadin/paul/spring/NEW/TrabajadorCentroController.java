package org.vaadin.paul.spring.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.paul.spring.entities.Trabajador;
@RestController
@CrossOrigin
public class TrabajadorCentroController {

@Autowired
 private TrabajadorCentroService trabajadorcentroservice;


@GetMapping
("/Centro/{id}/Trabajador/getTrabajadores")
public List<Trabajador> getTrabajadores(@PathVariable(name="id") int id){
return trabajadorcentroservice.getTrabajadores(id);
}


@PutMapping
("/Centro/{id}/Trabajador/setTrabajadores")
public void setTrabajadores(@PathVariable(name="id") int id,@RequestBody List<Trabajador> trabajadores){
trabajadorcentroservice.setTrabajadores(id,trabajadores);
}


}