package org.vaadin.paul.spring.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.paul.spring.entities.User;
@RestController
@CrossOrigin
public class UserCitaController {

@Autowired
 private UserCitaService usercitaservice;


@GetMapping
("/Cita/{id}/User/getPaciente")
public User getPaciente(@PathVariable(name="id") int id){
return usercitaservice.getPaciente(id);
}


@PutMapping
("/Cita/{id}/User/setPaciente")
public void setPaciente(@PathVariable(name="id") int id,@RequestBody User user){
usercitaservice.setPaciente(id,user);
}


}