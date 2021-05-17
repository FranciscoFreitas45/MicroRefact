import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
@AllArgsConstructor
@RestController
@CrossOrigin
public class InscripcionProfesorController {

 private InscripcionProfesorService inscripcionprofesorservice;


@GetMapping
("/Profesor/{id}/Inscripcion/getInscripciones")
public List<Inscripcion> getInscripciones(@PathVariable(name="id") int pk_id_profesor){
inscripcionprofesorservice.getInscripciones(pk_id_profesor);
}


@PutMapping
("/Profesor/{id}/Inscripcion/setInscripciones")
public void setInscripciones(@PathVariable(name="id") int pk_id_profesor,@RequestBody List<Inscripcion> inscripciones){
inscripcionprofesorservice.setInscripciones(pk_id_profesor,inscripciones);
}


}