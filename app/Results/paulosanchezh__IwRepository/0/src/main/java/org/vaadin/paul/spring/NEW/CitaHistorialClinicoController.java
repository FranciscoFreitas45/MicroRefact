package org.vaadin.paul.spring.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.paul.spring.entities.Cita;
@RestController
@CrossOrigin
public class CitaHistorialClinicoController {

@Autowired
 private CitaHistorialClinicoService citahistorialclinicoservice;


@GetMapping
("/HistorialClinico/{id}/Cita/getCitas")
public List<Cita> getCitas(@PathVariable(name="id") int id){
return citahistorialclinicoservice.getCitas(id);
}


}