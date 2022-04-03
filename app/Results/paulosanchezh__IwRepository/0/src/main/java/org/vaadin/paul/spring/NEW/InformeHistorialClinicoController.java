package org.vaadin.paul.spring.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.paul.spring.entities.Informe;
@RestController
@CrossOrigin
public class InformeHistorialClinicoController {

@Autowired
 private InformeHistorialClinicoService informehistorialclinicoservice;


}