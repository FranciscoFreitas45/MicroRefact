package com.cocay.sicecd.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.cocay.sicecd.model.Grupo;
@RestController
@CrossOrigin
public class GrupoCursoController {

@Autowired
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
return grupocursoservice.getGrupos(pk_id_curso);
}


@GetMapping
("/Curso/{id}/Grupo/getFk_id_grupo")
public Grupo getFk_id_grupo(@PathVariable(name="id") int pk_id_curso){
return grupocursoservice.getFk_id_grupo(pk_id_curso);
}


}