package com.cocay.sicecd.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.cocay.sicecd.model.Inscripcion;
@RestController
@CrossOrigin
public class InscripcionProfesorController {

@Autowired
 private InscripcionProfesorService inscripcionprofesorservice;


@GetMapping
("/Profesor/{id}/Inscripcion/getInscripciones")
public List<Inscripcion> getInscripciones(@PathVariable(name="id") int pk_id_profesor){
return inscripcionprofesorservice.getInscripciones(pk_id_profesor);
}


@PutMapping
("/Profesor/{id}/Inscripcion/setInscripciones")
public void setInscripciones(@PathVariable(name="id") int pk_id_profesor,@RequestBody List<Inscripcion> inscripciones){
inscripcionprofesorservice.setInscripciones(pk_id_profesor,inscripciones);
}


}