package org.vaadin.paul.spring.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.paul.spring.entities.Sanitario;
@RestController
@CrossOrigin
public class SanitarioCitaController {

@Autowired
 private SanitarioCitaService sanitariocitaservice;


@GetMapping
("/Cita/{id}/Sanitario/getSanitario")
public Sanitario getSanitario(@PathVariable(name="id") int id){
return sanitariocitaservice.getSanitario(id);
}


@PutMapping
("/Cita/{id}/Sanitario/setSanitario")
public void setSanitario(@PathVariable(name="id") int id,@RequestBody Sanitario sanitario){
sanitariocitaservice.setSanitario(id,sanitario);
}


}