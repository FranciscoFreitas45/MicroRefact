import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
@AllArgsConstructor
@RestController
@CrossOrigin
public class InscripcionGrupoController {

 private InscripcionGrupoService inscripciongruposervice;


@GetMapping
("/Grupo/{id}/Inscripcion/getInscripciones")
public List<Inscripcion> getInscripciones(@PathVariable(name="id") int pk_id_grupo){
inscripciongruposervice.getInscripciones(pk_id_grupo);
}


@PutMapping
("/Grupo/{id}/Inscripcion/setInscripciones")
public void setInscripciones(@PathVariable(name="id") int pk_id_grupo,@RequestBody List<Inscripcion> inscripciones){
inscripciongruposervice.setInscripciones(pk_id_grupo,inscripciones);
}


}