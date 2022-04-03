package org.vaadin.paul.spring.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.paul.spring.entities.User;
@RestController
@CrossOrigin
public class UserTrabajadorController {

@Autowired
 private UserTrabajadorService usertrabajadorservice;


@GetMapping
("/Trabajador/{id}/User/getUser")
public User getUser(@PathVariable(name="id") int id){
return usertrabajadorservice.getUser(id);
}


}