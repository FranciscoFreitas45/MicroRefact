package com.cocay.sicecd.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.cocay.sicecd.model.Curso;
@RestController
@CrossOrigin
public class CursoGrupoController {

@Autowired
 private CursoGrupoService cursogruposervice;


@GetMapping
("/Grupo/{id}/Curso/getFk_id_curso")
public Curso getFk_id_curso(@PathVariable(name="id") int pk_id_curso){
return cursogruposervice.getFk_id_curso(pk_id_curso);
}


@PutMapping
("/Grupo/{id}/Curso/setFk_id_curso")
public void setFk_id_curso(@PathVariable(name="id") int pk_id_curso,@RequestBody Curso curso){
cursogruposervice.setFk_id_curso(pk_id_curso,curso);
}


}