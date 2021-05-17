import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
@AllArgsConstructor
@RestController
@CrossOrigin
public class GrupoInscripcionController {

 private GrupoInscripcionService grupoinscripcionservice;


@PutMapping
("/Inscripcion/{id}/Grupo/setFk_id_grupo")
public void setFk_id_grupo(@PathVariable(name="id") int int,@RequestBody Grupo fk_id_grupo){
grupoinscripcionservice.setFk_id_grupo(int,fk_id_grupo);
}


@GetMapping
("/Inscripcion/{id}/Grupo/getFk_id_grupo")
public Grupo getFk_id_grupo(@PathVariable(name="id") int int){
grupoinscripcionservice.getFk_id_grupo(int);
}


}