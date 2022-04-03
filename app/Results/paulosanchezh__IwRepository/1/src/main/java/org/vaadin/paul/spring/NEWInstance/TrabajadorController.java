package org.vaadin.paul.spring.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class TrabajadorController {

 private Trabajador trabajador;


@GetMapping
("/getNombre")
public String getNombre(){
  return trabajador.getNombre();
}


@GetMapping
("/getId")
public int getId(){
  return trabajador.getId();
}


@GetMapping
("/getUser")
public User getUser(){
  return trabajador.getUser();
}


@GetMapping
("/getHoraInicio")
public LocalTime getHoraInicio(){
  return trabajador.getHoraInicio();
}


@GetMapping
("/getHoraFinal")
public LocalTime getHoraFinal(){
  return trabajador.getHoraFinal();
}


@GetMapping
("/compareTo")
public Object compareTo(@RequestParam(name = "Object") Object Object){
  return trabajador.compareTo(Object);
}


}