package org.vaadin.paul.spring.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.paul.spring.entities.Trabajador;
@RestController
@CrossOrigin
public class TrabajadorSanitarioController {

@Autowired
 private TrabajadorSanitarioService trabajadorsanitarioservice;


@PutMapping
("/Sanitario/{id}/Trabajador/setTrabajador")
public void setTrabajador(@PathVariable(name="id") int id,@RequestBody Trabajador trabajador){
trabajadorsanitarioservice.setTrabajador(id,trabajador);
}


@GetMapping
("/Sanitario/{id}/Trabajador/getTrabajador")
public Trabajador getTrabajador(@PathVariable(name="id") int id){
return trabajadorsanitarioservice.getTrabajador(id);
}


}