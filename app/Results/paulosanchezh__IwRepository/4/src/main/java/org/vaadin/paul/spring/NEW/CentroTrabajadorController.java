package org.vaadin.paul.spring.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.paul.spring.entities.Centro;
@RestController
@CrossOrigin
public class CentroTrabajadorController {

@Autowired
 private CentroTrabajadorService centrotrabajadorservice;


@GetMapping
("/Trabajador/{id}/Centro/getCentro")
public Centro getCentro(@PathVariable(name="id") int id){
return centrotrabajadorservice.getCentro(id);
}


@PutMapping
("/Trabajador/{id}/Centro/setCentro")
public void setCentro(@PathVariable(name="id") int id,@RequestBody Centro centro){
centrotrabajadorservice.setCentro(id,centro);
}


}