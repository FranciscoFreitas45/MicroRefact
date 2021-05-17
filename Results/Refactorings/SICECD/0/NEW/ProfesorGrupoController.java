import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
@AllArgsConstructor
@RestController
@CrossOrigin
public class ProfesorGrupoController {

 private ProfesorGrupoService profesorgruposervice;


@GetMapping
("/Grupo/{id}/Profesor/getFk_id_profesor")
public Profesor getFk_id_profesor(@PathVariable(name="id") int int){
profesorgruposervice.getFk_id_profesor(int);
}


@PutMapping
("/Grupo/{id}/Profesor/setFk_id_profesor")
public void setFk_id_profesor(@PathVariable(name="id") int int,@RequestBody Profesor fk_id_profesor){
profesorgruposervice.setFk_id_profesor(int,fk_id_profesor);
}


}