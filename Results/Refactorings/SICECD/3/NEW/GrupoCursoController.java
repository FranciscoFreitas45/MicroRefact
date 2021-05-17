import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
@AllArgsConstructor
@RestController
@CrossOrigin
public class GrupoCursoController {

 private GrupoCursoService grupocursoservice;


@PutMapping
("/Curso/{id}/Grupo/setGrupos")
public void setGrupos(@PathVariable(name="id") int pk_id_curso,@RequestBody List<Grupo> grupos){
grupocursoservice.setGrupos(pk_id_curso,grupos);
}


@PutMapping
("/Curso/{id}/Grupo/setFk_id_grupo")
public void setFk_id_grupo(@PathVariable(name="id") int pk_id_curso,@RequestBody Grupo fk_id_grupo){
grupocursoservice.setFk_id_grupo(pk_id_curso,fk_id_grupo);
}


@GetMapping
("/Curso/{id}/Grupo/getGrupos")
public List<Grupo> getGrupos(@PathVariable(name="id") int pk_id_curso){
grupocursoservice.getGrupos(pk_id_curso);
}


@GetMapping
("/Curso/{id}/Grupo/getFk_id_grupo")
public Grupo getFk_id_grupo(@PathVariable(name="id") int pk_id_curso){
grupocursoservice.getFk_id_grupo(pk_id_curso);
}


}