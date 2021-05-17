import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
@AllArgsConstructor
@RestController
@CrossOrigin
public class ProfesorInscripcionController {

 private ProfesorInscripcionService profesorinscripcionservice;


@GetMapping
("/Inscripcion/{id}/Profesor/getFk_id_profesor")
public Profesor getFk_id_profesor(@PathVariable(name="id") int int){
profesorinscripcionservice.getFk_id_profesor(int);
}


@PutMapping
("/Inscripcion/{id}/Profesor/setFk_id_profesor")
public void setFk_id_profesor(@PathVariable(name="id") int int,@RequestBody Profesor fk_id_profesor){
profesorinscripcionservice.setFk_id_profesor(int,fk_id_profesor);
}


}