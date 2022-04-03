package com.cocay.sicecd.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ProfesorRepController {

 private ProfesorRep profesorrep;


@GetMapping
("/loadAllProfesor")
public List<Profesor> loadAllProfesor(){
  return profesorrep.loadAllProfesor();
}


@GetMapping
("/findAll")
public List<Profesor> findAll(){
  return profesorrep.findAll();
}


@GetMapping
("/findByName")
public List<Profesor> findByName(@RequestParam(name = "nombre") String nombre,@RequestParam(name = "apellido_paterno") String apellido_paterno){
  return profesorrep.findByName(nombre,apellido_paterno);
}


@GetMapping
("/findByRfc")
public Profesor findByRfc(@RequestParam(name = "rfc") String rfc){
  return profesorrep.findByRfc(rfc);
}


@GetMapping
("/findByCorreo")
public Profesor findByCorreo(@RequestParam(name = "correo") String correo){
  return profesorrep.findByCorreo(correo);
}


@GetMapping
("/findByCurp")
public Profesor findByCurp(@RequestParam(name = "curp") String curp){
  return profesorrep.findByCurp(curp);
}


@GetMapping
("/findByRFC")
public Profesor findByRFC(@RequestParam(name = "rfc") String rfc){
  return profesorrep.findByRFC(rfc);
}


@PutMapping
("/updateProfesorByRFC")
public void updateProfesorByRFC(@RequestParam(name = "nombre") String nombre,@RequestParam(name = "apellido_paterno") String apellido_paterno,@RequestParam(name = "apellido_materno") String apellido_materno,@RequestParam(name = "correo") String correo,@RequestParam(name = "clave_plantel") String clave_plantel,@RequestParam(name = "ciudad_localidad") String ciudad_localidad,@RequestParam(name = "rfc") String rfc){
profesorrep.updateProfesorByRFC(nombre,apellido_paterno,apellido_materno,correo,clave_plantel,ciudad_localidad,rfc);
}


@PutMapping
("/saveT")
public void saveT(@RequestParam(name = "firstname") String firstname,@RequestParam(name = "apellido_paterno") String apellido_paterno,@RequestParam(name = "apellido_materno") String apellido_materno,@RequestParam(name = "curp") String curp,@RequestParam(name = "rfc") String rfc,@RequestParam(name = "email") String email,@RequestParam(name = "institution") String institution,@RequestParam(name = "city") String city){
profesorrep.saveT(firstname,apellido_paterno,apellido_materno,curp,rfc,email,institution,city);
}


@GetMapping
("/findByCompleteNameList")
public List<Profesor> findByCompleteNameList(@RequestParam(name = "nombre") String nombre,@RequestParam(name = "apellido_paterno") String apellido_paterno,@RequestParam(name = "apellido_materno") String apellido_materno){
  return profesorrep.findByCompleteNameList(nombre,apellido_paterno,apellido_materno);
}


}