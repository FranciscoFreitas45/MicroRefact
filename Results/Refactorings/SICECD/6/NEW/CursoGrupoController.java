import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
@AllArgsConstructor
@RestController
@CrossOrigin
public class CursoGrupoController {

 private CursoGrupoService cursogruposervice;


@GetMapping
("/Grupo/{id}/Curso/getFk_id_curso")
public Curso getFk_id_curso(@PathVariable(name="id") int int){
cursogruposervice.getFk_id_curso(int);
}


@PutMapping
("/Grupo/{id}/Curso/setFk_id_curso")
public void setFk_id_curso(@PathVariable(name="id") int int,@RequestBody Curso curso){
cursogruposervice.setFk_id_curso(int,curso);
}


}