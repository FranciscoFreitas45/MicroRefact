package org.vaadin.paul.spring.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.paul.spring.entities.User;
@RestController
@CrossOrigin
public class UserHistorialClinicoController {

@Autowired
 private UserHistorialClinicoService userhistorialclinicoservice;


}